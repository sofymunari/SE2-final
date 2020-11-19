package com.polito.bookingsystem.service;

import org.springframework.stereotype.Service;

import com.polito.bookingsystem.dto.ProfessorDto;

@Service
public interface ProfessorService {
	
	String login(String email, String password);
	public void sendEmail(ProfessorDto professorDto, String subject, String text);
}
