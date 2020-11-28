package com.polito.bookingsystem.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.polito.bookingsystem.converter.StudentConverter;
import com.polito.bookingsystem.dto.StudentDto;
import com.polito.bookingsystem.entity.Student;
import com.polito.bookingsystem.repository.StudentRepository;
import com.polito.bookingsystem.service.StudentService;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@Service
public class StudentServiceImpl implements StudentService {
	@Autowired
	private JavaMailSender javaMailSender;

    @Autowired
    StudentRepository studentRepository;
	
    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository)
    {
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
