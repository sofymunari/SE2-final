package com.polito.bookingsystem.service.impl;

import com.polito.bookingsystem.dto.ProfessorDto;
import com.polito.bookingsystem.dto.StudentDto;
import com.polito.bookingsystem.service.StudentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {
	@Autowired
    private JavaMailSender javaMailSender;
	
	@Override
	public void sendEmail(StudentDto studentDto, String subject, String text) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(studentDto.getEmail());

        msg.setSubject(subject);
        msg.setText(text);

        javaMailSender.send(msg);
	}
}
