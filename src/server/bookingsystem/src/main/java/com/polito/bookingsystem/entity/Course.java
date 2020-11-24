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
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	private Integer courseId;
	
	@Column
	private String name;
	
	@Column
	private String descriptions;
	
	public Course() {
		
	}
	
	public Course(Integer courseId, String name, String descriptions) {
		super();
		this.courseId = courseId;
		this.name = name;
		this.descriptions = descriptions;
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
}
