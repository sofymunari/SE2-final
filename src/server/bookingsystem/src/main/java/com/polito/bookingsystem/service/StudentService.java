package com.polito.bookingsystem.service;
import com.polito.bookingsystem.dto.StudentDto;

public interface StudentService {
	String login(String email, String password);
	StudentDto getStudent(String email);
	public void sendEmail(StudentDto studentDto, String subject, String text);

}
