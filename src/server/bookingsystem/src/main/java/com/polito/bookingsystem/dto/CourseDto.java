package com.polito.bookingsystem.dto;
public class CourseDto {
	private Integer courseId;
	private String name;
	private String code;
	private Integer year;
	private Integer semester;
	
	public CourseDto() {
	}
	public CourseDto(Integer courseId, String name, String code, Integer year, Integer semester) {
		super();
		this.courseId = courseId;
		this.name = name;
		this.code = code;
		this.year = year;
		this.semester = semester;
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
