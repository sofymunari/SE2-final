package com.polito.bookingsystem.service.impl;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Calendar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import com.polito.bookingsystem.converter.StudentConverter;
import com.polito.bookingsystem.dto.BookingDto;
import com.polito.bookingsystem.dto.StudentDto;
import com.polito.bookingsystem.entity.Course;
import com.polito.bookingsystem.entity.Student;
import com.polito.bookingsystem.repository.CourseRepository;
import com.polito.bookingsystem.repository.StudentRepository;
import com.polito.bookingsystem.service.BookingService;
import com.polito.bookingsystem.service.StudentService;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@Service
public class StudentServiceImpl implements StudentService {
	private BookingService bookingService;
	@Autowired
	JavaMailSender javaMailSender;

    @Autowired
    StudentRepository studentRepository;
    
    @Autowired
    CourseRepository courseRepository;
	
    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository, CourseRepository courseRepository,  JavaMailSender javaMailSender,@Lazy BookingService bookingService){
    	this.javaMailSender = javaMailSender;
    	this.studentRepository = studentRepository;
    	this.courseRepository = courseRepository;
		this.bookingService = bookingService;
    }

    
    @Override
	public String login(String email, String password) {
		Student student = studentRepository.findByEmail(email);

        if(student != null && student.getPassword().equals(password)) return email;
        
		return null;
	}
	
	
	@Override
	public StudentDto getStudent(String email) {
		Student student = studentRepository.findByEmail(email);
		if(student != null)
		   return StudentConverter.toDto(student);
		return null;
	}

	@Override
	public void sendEmail(StudentDto studentDto, String subject, String text) {
		
		if(studentDto != null) {
			
			try {
				SimpleMailMessage msg = new SimpleMailMessage();
				msg.setTo(studentDto.getEmail());
				
				msg.setSubject(subject);
				msg.setText(text);
				
				javaMailSender.send(msg);
			}catch (Exception e) {
				System.err.print(e.getMessage());
			}
		}
	}


	@Override
	public List<StudentDto> getContactedStudents(StudentDto studentDto, Date date) {
		List<StudentDto> contactedStudents = new ArrayList<>();
		if(studentDto != null) {			
			List<BookingDto> bookingDtos =  bookingService.getListBooking(studentDto.getEmail());
			for (BookingDto bookingDto : bookingDtos) {
				if ((bookingDto.getLectureDto().getDate()).compareTo(date) >= 0) {
					List<BookingDto> bookingDtosForContactedStudents = bookingService.getBookingsByLecture(bookingDto.getLectureDto());
					for (BookingDto bookingDto2 : bookingDtosForContactedStudents) {
						if(bookingDto2.getStudentDto().getEmail().equals(studentDto.getEmail())) {
							continue;
						}
						boolean contains = false;
						for (StudentDto contactedStudent : contactedStudents) {
							if(contactedStudent.getEmail().equals(bookingDto2.getStudentDto().getEmail())) {
								contains = true;
								break;
							}
						}
						if(contains == true) {
							continue;
						}
						contactedStudents.add(bookingDto2.getStudentDto());
					}
				}
			}
		}
		return contactedStudents;
	}	

	public void addStudents(String fileName)  {
		try (BufferedReader reader = new BufferedReader(new FileReader(fileName))){
			 String currentLine = reader.readLine(); //read first line
			 while((currentLine = reader.readLine()) != null){
				  String[] fields = currentLine.split(",");
				  Student student = studentRepository.findByMatricola(fields[0]);
				  if(student == null) {
					  Student newStudent = new Student();
					  Integer userId = studentRepository.findAll().stream()
					           .mapToInt(Student::getUserId)
					           .max()
					           .orElse(0);
					  
			          newStudent.setUserId(userId+1);
					  newStudent.setMatricola(fields[0]);
					  newStudent.setName(fields[1]);
					  newStudent.setSurname(fields[2]);
					  newStudent.setAddress(fields[3]);
					  newStudent.setEmail(fields[4]);
					  String[] dateStudent = fields[5].split("-");
					  Calendar calendar = Calendar.getInstance();
					  calendar.set(Integer.parseInt(dateStudent[0]), Integer.parseInt(dateStudent[1]), Integer.parseInt(dateStudent[2]));
					  newStudent.setDateOfBirth(calendar.getTime());
					  newStudent.setPassword("password");
					  String subject = "Account created!";
					  String text = "Dear Student "+ newStudent.getName() + " " + newStudent.getSurname() +","
								+ "your account is created correctly. Use this password to access at your home page: " + newStudent.getPassword() + "\n"
								+ "\n"
								+ "Best Regards,\n"
								+ "Politecnico";
					  try {
						//commantare durante la demo
						//sendEmail(StudentConverter.toDto(newStudent), subject, text);
					  }catch(Exception e) {}
					  studentRepository.save(newStudent);
				  }
			 }
			 reader.close();
		}catch(IOException e) {
			System.err.println(e.getMessage());
		}
		
	}
	
	@Override
	public void addClasses(String fileName) {
		try (BufferedReader reader = new BufferedReader(new FileReader(fileName))){
			 String currentLine = reader.readLine(); //read first line
			 while((currentLine = reader.readLine()) != null){
				  String[] fields = currentLine.split(",");
				  Student student = studentRepository.findByMatricola(fields[1]);
				  Course course = courseRepository.findByCode(fields[0]);
				  if(student != null && course != null) {
					  student.getCourses().add(course);
					  studentRepository.save(student);
				  }
			 }
			 reader.close();
		}catch(IOException e) {
			System.err.println(e.getMessage());
		}
	}

    
}
