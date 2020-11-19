package com.polito.bookingsystem.converter;

import com.polito.bookingsystem.dto.CourseDto;
import com.polito.bookingsystem.entity.Course;

public class CourseConverter {
	
	public static Course toEntity(CourseDto courseDto) {
		if( courseDto == null)
			return null;
		Course course = new Course(courseDto.getCourseId(), courseDto.getName(), courseDto.getDescriptions());
		return course;
	}
	
	public static CourseDto toDto(Course course) {
		if( course == null)
			return null;
		CourseDto courseDto = new CourseDto(course.getCourseId(), course.getName(), course.getDescriptions());
		return courseDto;
	}
	
}
