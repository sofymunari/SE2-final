package com.polito.bookingsystem.entity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Course {
	
	@Column
	//@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	private Integer courseId;
	
	@Column
	private String name;
	
	@Column
	private String code;
	
	@Column
	private Integer year;
	
	@Column
	private Integer semester;
	
	public Course() {
		
	}
	
	public Course(Integer courseId, String name, String code, Integer year, Integer semester) {
		super();
		this.courseId = courseId;
		this.name = name;
		this.code = code;
		this.semester = semester;
		this.year = year;
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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Integer getSemester() {
		return semester;
	}

	public void setSemester(Integer semester) {
		this.semester = semester;
	}
	
}
