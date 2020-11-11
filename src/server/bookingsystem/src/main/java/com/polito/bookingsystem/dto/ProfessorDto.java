package com.polito.bookingsystem.dto;

import java.util.ArrayList;
import java.util.List;


public class ProfessorDto extends UserDto{
	private List<CourseDto> courses = new ArrayList<>();

	public ProfessorDto() {
		super();
	}

	public ProfessorDto(Integer userId, String name, String surname, String address, String email, String password,  List<CourseDto> courses) {
		super(userId, name, surname, address, email, password);
		this.courses = courses;
	}
	
	public List<CourseDto> getCourses() {
		return courses;
	}

	public void setCourses(List<CourseDto> courses) {
		this.courses = courses;
	}

	
}
