package com.polito.bookingsystem.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table
public class Course {
	
	@Column
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	private Integer courseId;
	
	@Column
	private String name;
	
	@Column
	private String descriptions;
	
	/*@ManyToMany
	@JoinTable(name = "course_professor",
	    joinColumns = @JoinColumn(name = "course_id"),
	    inverseJoinColumns = @JoinColumn(name = "professor_id")
	)
	private List<Professor> professors = new ArrayList<>();
	
	@ManyToMany
	@JoinTable(name = "course_student",
	    joinColumns = @JoinColumn(name = "course_id"),
	    inverseJoinColumns = @JoinColumn(name = "user_id")
	)
	private List<Student> students = new ArrayList<>();*/
	
	public Course() {
		
	}
	
	public Course(Integer courseId, String name, String descriptions/*, List<Professor> professors,
			List<Student> students*/) {
		super();
		this.courseId = courseId;
		this.name = name;
		this.descriptions = descriptions;
		//this.professors = professors;
		//this.students = students;
	}

	public Integer getCourseId() {
		return courseId;
	}

	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescriptions() {
		return descriptions;
	}

	public void setDescriptions(String descriptions) {
		this.descriptions = descriptions;
	}

	/*public List<Professor> getProfessors() {
		return professors;
	}

	public void setProfessors(List<Professor> professors) {
		this.professors = professors;
	}

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}*/
	
}
