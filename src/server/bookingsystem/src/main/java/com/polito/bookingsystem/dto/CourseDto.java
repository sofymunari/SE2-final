package com.polito.bookingsystem.dto;

public class CourseDto {
	private Integer courseId;
	private String name;
	private String descriptions;
	public CourseDto() {
	}
	public CourseDto(Integer courseId, String name, String descriptions) {
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
