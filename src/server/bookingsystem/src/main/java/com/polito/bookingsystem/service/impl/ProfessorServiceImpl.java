package com.polito.bookingsystem.service.impl;


import com.polito.bookingsystem.dto.ProfessorDto;
import com.polito.bookingsystem.service.ProfessorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.polito.bookingsystem.repository.ProfessorRepository;
import com.polito.bookingsystem.repository.StudentRepository;
import com.polito.bookingsystem.entity.Professor;

@Service
public class ProfessorServiceImpl implements ProfessorService {

    @Autowired
    private JavaMailSender javaMailSender;

	@Autowired
    ProfessorRepository professorRepository;
	
	
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
}
