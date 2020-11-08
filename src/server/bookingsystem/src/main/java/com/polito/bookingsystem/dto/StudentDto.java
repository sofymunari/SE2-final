package com.polito.bookingsystem.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;



public class StudentDto {
	private String studentId;//SXXXXXX
	private String name;
	private String surname;
	private String address;
	private String email;
	private Date dateOfBirth;
	private String password;
	private List<CourseDto> courses = new ArrayList<>();
	
	public StudentDto() {
	}
	
	public StudentDto(String studentId, String name, String surname, String address, String email, Date dateOfBirth,
			String password, List<CourseDto> courses) {
		super();
		this.studentId = studentId;
		this.name = name;
		this.surname = surname;
		this.address = address;
		this.email = email;
		this.dateOfBirth = dateOfBirth;
		this.password = password;
		this.courses = courses;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
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

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
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
		return "StudentDto [studentId=" + studentId + ", name=" + name + ", surname=" + surname + ", address=" + address
				+ ", email=" + email + ", dateOfBirth=" + dateOfBirth + ", password=" + password + ", courses="
				+ courses + "]";
	}
}
