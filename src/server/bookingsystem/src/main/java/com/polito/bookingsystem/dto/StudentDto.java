package com.polito.bookingsystem.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class StudentDto extends UserDto{
	private Date dateOfBirth;
	private List<CourseDto> courses = new ArrayList<>();
	
	public StudentDto() {
		super();
	}
	
	public StudentDto(Integer userId, String name, String surname, String address, String email, String password, Date dateOfBirth, List<CourseDto> courses) {
		super(userId, name, surname, address, email, password);
		this.courses = courses;
		this.dateOfBirth = dateOfBirth;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public List<CourseDto> getCourses() {
		return courses;
	}

	public void setCourses(List<CourseDto> courses) {
		this.courses = courses;
	}

	
	
}
