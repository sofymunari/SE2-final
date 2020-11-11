package com.polito.bookingsystem.dto;

import java.util.ArrayList;
import java.util.List;

public class CourseDto {
	private Integer courseId;
	private String name;
	private String descriptions;
	private List<ProfessorDto> professors = new ArrayList<>();
	private List<StudentDto> students = new ArrayList<>();
	
	public CourseDto() {
		
	}
	
	public CourseDto(Integer courseId, String name, String descriptions, List<ProfessorDto> professors,
			List<StudentDto> students) {
		super();
		this.courseId = courseId;
		this.name = name;
		this.descriptions = descriptions;
		this.professors = professors;
		this.students = students;
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

	public List<ProfessorDto> getProfessors() {
		return professors;
	}

	public void setProfessors(List<ProfessorDto> professors) {
		this.professors = professors;
	}

	public List<StudentDto> getStudents() {
		return students;
	}

	public void setStudents(List<StudentDto> students) {
		this.students = students;
	}
	
}
