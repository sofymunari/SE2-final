package com.polito.bookingsystem.entity;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table
public class Professor extends User{
	
	@ManyToMany
	@JoinTable(name = "course_professor",
	    joinColumns = @JoinColumn(name = "user_id"),
	    inverseJoinColumns = @JoinColumn(name = "course_id")
	)
	private List<Course> courses = new ArrayList<>();
	
	@Column
    private String code;
    
	public Professor() {
		super();
	}

	public Professor(Integer userId, String name, String surname, String address, String email, String password,  List<Course> courses, String code) {
		super(userId, name, surname, address, email, password);
		this.courses = courses;
		this.code = code;
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public List<Course> getCourses() {
		return courses;
	}
	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}
}
