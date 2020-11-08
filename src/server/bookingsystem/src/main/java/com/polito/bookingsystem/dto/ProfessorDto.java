package com.polito.bookingsystem.dto;

import java.util.ArrayList;
import java.util.List;


public class ProfessorDto {
	private String professorId; //PXXXXXX
	private String name;
	private String surname;
	private String address;
	private String email;
	private String password;
	private List<CourseDto> courses = new ArrayList<>();
	
	public ProfessorDto() {
	}
	
	public ProfessorDto(String professorId, String name, String surname, String address, String email, String password,
			List<CourseDto> courses) {
		super();
		this.professorId = professorId;
		this.name = name;
		this.surname = surname;
		this.address = address;
		this.email = email;
		this.password = password;
		this.courses = courses;
	}

	public String getProfessorId() {
		return professorId;
	}

	public void setProfessorId(String professorId) {
		this.professorId = professorId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<CourseDto> getCourses() {
		return courses;
	}

	public void setCourses(List<CourseDto> courses) {
		this.courses = courses;
	}

	@Override
	public String toString() {
		return "ProfessorDto [professorId=" + professorId + ", name=" + name + ", surname=" + surname + ", address="
				+ address + ", email=" + email + ", password=" + password + ", courses=" + courses + "]";
	}
	
	
}
