package com.polito.bookingsystem.converter;
import com.polito.bookingsystem.dto.CourseDto;
import com.polito.bookingsystem.entity.Course;

public class CourseConverter {
	private CourseConverter() {
		
	}
	
	public static Course toEntity(CourseDto courseDto) {
		if( courseDto == null)
			return null;
		Course course = new Course();
		course.setCourseId(courseDto.getCourseId());
		course.setDescriptions(courseDto.getDescriptions());
		course.setName(courseDto.getName());
		return course;
	}
	
	public static CourseDto toDto(Course course) {
		if( course == null)
			return null;
		CourseDto courseDto = new CourseDto();
		courseDto.setCourseId(course.getCourseId());
		courseDto.setDescriptions(course.getDescriptions());
		courseDto.setName(course.getName());
		return courseDto;
	}
}
