package com.polito.bookingsystem.service;

import org.springframework.stereotype.Service;

import com.polito.bookingsystem.dto.ProfessorDto;

@Service
public interface ProfessorService {
	
	public void sendEmail(ProfessorDto professorDto, String subject, String text);
}
