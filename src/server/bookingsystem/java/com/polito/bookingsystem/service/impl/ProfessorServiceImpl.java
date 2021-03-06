package com.polito.bookingsystem.service.impl;
import com.polito.bookingsystem.converter.ProfessorConverter;
import com.polito.bookingsystem.dto.BookingDto;
import com.polito.bookingsystem.dto.ProfessorDto;
import com.polito.bookingsystem.dto.StudentDto;
import com.polito.bookingsystem.service.BookingService;
import com.polito.bookingsystem.service.ProfessorService;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import com.polito.bookingsystem.repository.BookingRepository;
import com.polito.bookingsystem.repository.LectureRepository;
import com.polito.bookingsystem.repository.ProfessorRepository;
import com.polito.bookingsystem.entity.Lecture;
import com.polito.bookingsystem.entity.Professor;

@Service
public class ProfessorServiceImpl implements ProfessorService {

    @Autowired
    private JavaMailSender javaMailSender;

	@Autowired
    private ProfessorRepository professorRepository;
	
	@Autowired
    private LectureRepository lectureRepository;
	
	@Autowired
    private BookingRepository bookingRepository;
	
	@Autowired
    private BookingService bookingService;
	
	
    @Autowired
    public ProfessorServiceImpl(ProfessorRepository professorRepository, JavaMailSender javaMailSender, LectureRepository lectureRepository, BookingRepository bookingRepository,BookingService bookingService)
    {
    	this.professorRepository = professorRepository;
    	this.javaMailSender = javaMailSender;
    	this.bookingRepository = bookingRepository;
    	this.lectureRepository = lectureRepository;
    	this.bookingService = bookingService;
    }
    
    
	@Override
	public String login(String email, String password) {
		Professor professor = professorRepository.findByEmail(email);

        if(professor != null && professor.getPassword().equals(password)) return email;
        
		return null;
    }
    
    @Override
	public void sendEmail(ProfessorDto professorDto, String subject, String text) {
    	if(professorDto != null) {
    		
    		SimpleMailMessage msg = new SimpleMailMessage();
    		msg.setTo(professorDto.getEmail());
    		
    		msg.setSubject(subject);
    		msg.setText(text);
    		
    		javaMailSender.send(msg);
    	}
    }


	@Override
	public ProfessorDto getProfessor(String email) {
		if (email == null)
			return null;
		Professor professor= professorRepository.findByEmail(email);
		if(professor != null)
		   return ProfessorConverter.toDto(professor);
		return null;
	}


	@Override
	public void notifyProfessorsAboutNumberOfStudents() throws ParseException {
		List<Lecture> lectures = lectureRepository.findAll();

		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    Date date = new Date();
	    
		for (Lecture lecture : lectures) {
			if(dateFormat.format(lecture.getDate()).equals(getNextDate(dateFormat.format(date)))) {
				ProfessorDto professorDto = ProfessorConverter.toDto(lecture.getProfessor());
				String subject = "Lecture " + lecture.getLectureId();
				String text = "Dear Professor "+ professorDto.getName() + " " + professorDto.getSurname() +","
						+ "you have " + (bookingRepository.findByLecture(lecture)).size() + " student(s) for lecture "+lecture.getCourse().getName() + " tomorrow.\n"
						+ "\n"
						+ "Best Regards,\n"
						+ "Politecnico";
				sendEmail(professorDto, subject, text);
			}
		}
		
	}


	public String getNextDate(String curDate) throws ParseException {
		
		if(curDate == null)
			return null;
		
		final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		final Date date = format.parse(curDate);
		final Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_YEAR, 1);
		return format.format(calendar.getTime()); 
	}


	@Override
	public void addProfessors(String fileName) {
		try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
			 String currentLine = reader.readLine(); //read first line
			 while((currentLine = reader.readLine()) != null){
				  String[] fields = currentLine.split(",");
				  Professor professor = professorRepository.findByCode(fields[0]);
				  if(professor == null) {
					  Professor newProfessor = new Professor();
					  Integer userId = professorRepository.findAll().stream()
							           .mapToInt(Professor::getUserId)
							           .max()
							           .orElse(0);
					  newProfessor.setUserId(userId+1);
					  newProfessor.setCode(fields[0]);
					  newProfessor.setName(fields[1]);
					  newProfessor.setSurname(fields[2]);
					  newProfessor.setEmail(fields[3]);
					  newProfessor.setPassword("password");
					  newProfessor.setAddress("");
					  String subject = "Account created!";
					  String text = "Dear Professor "+ newProfessor.getName() + " " + newProfessor.getSurname() +","
								+ "your account is created correctly. Use this password to access at your home page: " + newProfessor.getPassword() + "\n"
								+ "\n"
								+ "Best Regards,\n"
								+ "Politecnico";
						try {
							//commantare durante la demo
							//sendEmail(ProfessorConverter.toDto(newProfessor), subject, text);
						  }catch(Exception e) {
							  System.err.println(e.getMessage());
						  }
						professorRepository.save(newProfessor);
				  }
				  
			 }
		}catch(IOException e) {
			System.err.println(e.getMessage());
		}
		
	}

	
	@Override
	public List<StudentDto> getContactedStudents(ProfessorDto professorDto, Date date) {
		List<StudentDto> contactedStudents = new ArrayList<>();
		if(professorDto != null) {			
			List<BookingDto> bookingDtos =  bookingService.getListAllBookings();
			for (BookingDto bookingDto : bookingDtos) {
				if ((bookingDto.getLectureDto().getDate()).compareTo(date) >= 0 && bookingDto.getLectureDto().getProfessorDto().getEmail().equals(professorDto.getEmail())) {
					
					boolean contains = false;
					for (StudentDto contactedStudent : contactedStudents) {
						if(contactedStudent.getEmail().equals(bookingDto.getStudentDto().getEmail())) {
							contains = true;
							break;
						}
					}
					if(contains) {
						continue;
					}
					contactedStudents.add(bookingDto.getStudentDto());
				}
			}
		}
		return contactedStudents;
	}	

}
