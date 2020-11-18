package com.polito.bookingsystem.service;

import com.polito.bookingsystem.dto.ProfessorDto;

public interface ProfessorService {
	
	String login(String email, String password);
	
	public ProfessorDto getProfessor(String email);
}
