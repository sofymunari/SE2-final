package com.polito.bookingsystem.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.polito.bookingsystem.converter.StudentConverter;
import com.polito.bookingsystem.dto.BookingDto;
import com.polito.bookingsystem.dto.StudentDto;
import com.polito.bookingsystem.entity.Student;
import com.polito.bookingsystem.repository.StudentRepository;
import com.polito.bookingsystem.service.BookingService;
import com.polito.bookingsystem.service.StudentService;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@Service
public class StudentServiceImpl implements StudentService {
	@Autowired
	JavaMailSender javaMailSender;

    @Autowired
    StudentRepository studentRepository;
	
    @Autowired
    BookingService bookingService;
    
    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository,  JavaMailSender javaMailSender){
    	this.javaMailSender = javaMailSender;
    	this.studentRepository = studentRepository;
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
		List<StudentDto> contactedStudents = new ArrayList<StudentDto>();
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
    
}
