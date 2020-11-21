package com.polito.bookingsystem.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class StudentDto extends UserDto{
	private Date dateOfBirth;
	private List<CourseDto> coursesDto = new ArrayList<>();
	private String matricola;
	
	public StudentDto() {
		super();
	}
	
	public StudentDto(Integer userId, String name, String surname, String address, String email, String password, Date dateOfBirth, List<CourseDto> coursesDto, String matricola) {
		super(userId, name, surname, address, email, password);
		this.coursesDto = coursesDto;
		this.dateOfBirth = dateOfBirth;
		this.matricola = matricola;
	}
    
	
	
	public String getMatricola() {
		return matricola;
	}

	public void setMatricola(String matricola) {
		this.matricola = matricola;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public List<CourseDto> getCoursesDto() {
		return coursesDto;
	}

	public void setCoursesDto(List<CourseDto> coursesDto) {
		this.coursesDto = coursesDto;
	}

	
	
}
