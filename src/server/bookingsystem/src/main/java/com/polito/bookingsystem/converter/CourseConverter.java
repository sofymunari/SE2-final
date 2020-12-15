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
		course.setName(courseDto.getName());
		course.setCode(courseDto.getCode());
		course.setYear(courseDto.getYear());
		course.setSemester(courseDto.getSemester());
		return course;
	}
	
	public static CourseDto toDto(Course course) {
		if( course == null)
			return null;
		CourseDto courseDto = new CourseDto();
		courseDto.setCourseId(course.getCourseId());
		courseDto.setName(course.getName());
		courseDto.setCode(course.getCode());
		courseDto.setYear(course.getYear());
		courseDto.setSemester(course.getSemester());
		return courseDto;
	}
}
