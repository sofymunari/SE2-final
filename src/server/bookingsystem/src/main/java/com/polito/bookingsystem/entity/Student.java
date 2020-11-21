package com.polito.bookingsystem.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table
public class Student extends User{
	@Column
	private Date dateOfBirth;
	
	@Column
	private String matricola;
	
	@ManyToMany
	@JoinTable(name = "course_student",
	    joinColumns = @JoinColumn(name = "user_id"),
	    inverseJoinColumns = @JoinColumn(name = "course_id")
	)
	private List<Course> courses = new ArrayList<>();
	
	public Student() {
		super();
	}
	
	public Student(Integer userId, String name, String surname, String address, String email, String password, Date dateOfBirth, List<Course> courses, String matricola) {
		super(userId, name, surname, address, email, password);
		this.courses = courses;
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

	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}
	
	
}
