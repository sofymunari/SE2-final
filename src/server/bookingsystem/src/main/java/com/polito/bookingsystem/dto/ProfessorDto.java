package com.polito.bookingsystem.dto;
import java.util.ArrayList;
import java.util.List;


public class ProfessorDto extends UserDto{
	private List<CourseDto> coursesDto = new ArrayList<>();
	private String code;
	
	public ProfessorDto() {
		super();
	}
	public ProfessorDto(Integer userId, String name, String surname, String address, String email, String password,  List<CourseDto> coursesDto, String code) {
		super(userId, name, surname, address, email, password);
		this.coursesDto = coursesDto;
		this.code = code;
	}
	public List<CourseDto> getCoursesDto() {
		return coursesDto;
	}
	public void setCoursesDto(List<CourseDto> coursesDto) {
		this.coursesDto = coursesDto;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
}
