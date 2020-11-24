package com.polito.bookingsystem.dto;
import java.util.ArrayList;
import java.util.List;


public class ProfessorDto extends UserDto{
	private List<CourseDto> coursesDto = new ArrayList<>();
	public ProfessorDto() {
		super();
	}
	public ProfessorDto(Integer userId, String name, String surname, String address, String email, String password,  List<CourseDto> coursesDto) {
		super(userId, name, surname, address, email, password);
		this.coursesDto = coursesDto;
	}
	public List<CourseDto> getCoursesDto() {
		return coursesDto;
	}
	public void setCoursesDto(List<CourseDto> coursesDto) {
		this.coursesDto = coursesDto;
	}
}
