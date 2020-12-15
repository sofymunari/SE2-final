package com.polito.bookingsystem.service;

import java.util.List;

import com.polito.bookingsystem.dto.CourseDto;
import com.polito.bookingsystem.entity.Course;

public interface CourseService {
	void addCourses(String fileName);
	List<Course> getCourses();
	void setCourseToRemote(String courseName);
	
}
