package com.polito.bookingsystem.service;

import org.springframework.stereotype.Service;
import com.polito.bookingsystem.dto.StudentDto;

@Service
public interface StudentService {
	
	String login(String email, String password);
	StudentDto getStudent(String email);
	public void sendEmail(StudentDto studentDto, String subject, String text);
	void addStudents(String fileName);

}
