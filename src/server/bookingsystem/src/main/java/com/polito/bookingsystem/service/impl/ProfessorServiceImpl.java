package com.polito.bookingsystem.service.impl;


import com.polito.bookingsystem.dto.ProfessorDto;
import com.polito.bookingsystem.service.ProfessorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class ProfessorServiceImpl implements ProfessorService {
	
	@Autowired
    private JavaMailSender javaMailSender;
	
	@Override
	public void sendEmail(ProfessorDto professorDto, String subject, String text) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(professorDto.getEmail());

        msg.setSubject(subject);
        msg.setText(text);

        javaMailSender.send(msg);
	}
}
