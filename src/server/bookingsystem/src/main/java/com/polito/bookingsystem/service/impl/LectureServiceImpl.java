package com.polito.bookingsystem.service.impl;

import com.polito.bookingsystem.converter.LectureConverter;
import com.polito.bookingsystem.dto.LectureDto;
import com.polito.bookingsystem.entity.Course;
import com.polito.bookingsystem.entity.Lecture;
import com.polito.bookingsystem.entity.Student;
import com.polito.bookingsystem.repository.LectureRepository;
import com.polito.bookingsystem.repository.StudentRepository;
import com.polito.bookingsystem.service.LectureService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LectureServiceImpl implements LectureService {
	
	@Autowired
	private LectureRepository lectureRepository;
	
	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	public LectureServiceImpl(LectureRepository lectureRepository, StudentRepository studentRepository)
	{
		this.lectureRepository = lectureRepository;
		this.studentRepository = studentRepository;
	}


	@Override
	public List<LectureDto> getListLectures(String email) {
		Student student = studentRepository.findByEmail(email);
		if(student == null)
			return null;
		List<Course> courses = student.getCourses();
		if(courses.size()==0)
			return null;
		List<Lecture> allLectures = lectureRepository.findAll();
		List<LectureDto> studentLectures = new ArrayList<LectureDto>();
		
		for(Course course : courses) {
		   List<Lecture> courseLectures = allLectures.stream()
				                    .filter(l -> course.getCourseId().equals(l.getCourse().getCourseId()))
				                    .collect(Collectors.toList());
	       for(Lecture lecture : courseLectures) {
	    	   studentLectures.add(LectureConverter.toDto(lecture));
	       }
		}
		return studentLectures;
	}
}
