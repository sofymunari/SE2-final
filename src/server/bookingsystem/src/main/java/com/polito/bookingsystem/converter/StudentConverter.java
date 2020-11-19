package com.polito.bookingsystem.converter;

import java.util.ArrayList;
import java.util.List;

import com.polito.bookingsystem.dto.StudentDto;
import com.polito.bookingsystem.dto.CourseDto;
import com.polito.bookingsystem.entity.Course;
import com.polito.bookingsystem.entity.Student;


public class StudentConverter extends UserConverter{
	
	public static Student toEntity(StudentDto studentDto) {
		if( studentDto == null)
			return null;
		Student student = new Student();
		student.setAddress(studentDto.getAddress());
		student.setDateOfBirth(studentDto.getDateOfBirth());
		student.setEmail(studentDto.getEmail());
		student.setName(studentDto.getName());
		student.setPassword(studentDto.getPassword());
		student.setSurname(studentDto.getSurname());
		student.setUserId(studentDto.getUserId());
		List<Course> courses = new ArrayList<Course>();
		for(CourseDto course : studentDto.getCoursesDto()) {
			  courses.add(CourseConverter.toEntity(course));
		}
		student.setCourses(courses);
		return student;
	}
	
	public static StudentDto toDto(Student student) {
		if( student == null)
			return null;
		StudentDto studentDto = new StudentDto();
		studentDto.setAddress(student.getAddress());
		studentDto.setDateOfBirth(student.getDateOfBirth());
		studentDto.setEmail(student.getEmail());
		studentDto.setName(student.getName());
		studentDto.setPassword(student.getPassword());
		studentDto.setSurname(student.getSurname());
		studentDto.setUserId(student.getUserId());
		List<CourseDto> coursesDto = new ArrayList<CourseDto>();
		for(Course courseDto : student.getCourses()) {
			  coursesDto.add(CourseConverter.toDto(courseDto));
		}
		studentDto.setCoursesDto(coursesDto);
		return studentDto;
	}

	
	
}
