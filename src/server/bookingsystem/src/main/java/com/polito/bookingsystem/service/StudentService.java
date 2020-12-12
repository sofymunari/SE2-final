package com.polito.bookingsystem.service;

import java.util.List;
import java.util.Date;

import org.springframework.stereotype.Service;
import com.polito.bookingsystem.dto.StudentDto;

@Service
public interface StudentService {
	
	String login(String email, String password);
	StudentDto getStudent(String email);
	public void sendEmail(StudentDto studentDto, String subject, String text);
	public List<StudentDto> getContactedStudents(StudentDto studentDto, Date date);

}
