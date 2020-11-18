package com.polito.bookingsystem.service;

import org.springframework.stereotype.Service;

import com.polito.bookingsystem.dto.ProfessorDto;
import com.polito.bookingsystem.dto.StudentDto;

@Service
public interface StudentService {
	public void sendEmail(StudentDto studentDto, String subject, String text);

}
