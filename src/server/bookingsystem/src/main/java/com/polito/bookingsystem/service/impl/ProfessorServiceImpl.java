package com.polito.bookingsystem.service.impl;

import com.polito.bookingsystem.converter.ProfessorConverter;
import com.polito.bookingsystem.dto.ProfessorDto;
import com.polito.bookingsystem.service.ProfessorService;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    ProfessorRepository professorRepository;
	
	@Autowired
    LectureRepository lectureRepository;
	
	@Autowired
    BookingRepository bookingRepository;
	
	
    @Autowired
    public ProfessorServiceImpl(ProfessorRepository professorRepository)
    {
    	this.professorRepository = professorRepository;
    }
    
    
	@Override
	public String login(String email, String password) {
		Professor professor = professorRepository.findByEmail(email);

        if(professor != null && professor.getPassword().equals(password)) return email;
        
		return null;
    }
    
    @Override
	public void sendEmail(ProfessorDto professorDto, String subject, String text) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(professorDto.getEmail());

        msg.setSubject(subject);
        msg.setText(text);

        javaMailSender.send(msg);
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
				//System.out.println(professorDto.getEmail() + "\n" + subject + "\n" + text+"\n");
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

}
