package com.polito.bookingsystem.service.impl;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.polito.bookingsystem.entity.Course;
import com.polito.bookingsystem.entity.Professor;
import com.polito.bookingsystem.repository.BookingRepository;
import com.polito.bookingsystem.repository.CourseRepository;
import com.polito.bookingsystem.repository.LectureRepository;
import com.polito.bookingsystem.repository.ProfessorRepository;
import com.polito.bookingsystem.service.CourseService;

@Service
public class CourseServiceImpl implements CourseService{

	@Autowired
    private ProfessorRepository professorRepository;
	
	@Autowired
    private CourseRepository courseRepository;
	
	
    @Autowired
    public CourseServiceImpl(ProfessorRepository professorRepository, CourseRepository courseRepository)
    {
    	this.professorRepository = professorRepository;
    	this.courseRepository = courseRepository;
    }
    
    
	@Override
	public void addCourses(String fileName) {
		try {
			 BufferedReader reader = new BufferedReader(new FileReader(fileName));
			 String currentLine = reader.readLine(); //read first line
			 while((currentLine = reader.readLine()) != null){
				  String[] fields = currentLine.split(",");
				  Course course = courseRepository.findByCode(fields[0]);
				  if(course == null) {
					  Course newCourse = new Course();
					  Integer courseId = courseRepository.findAll().stream()
					           .mapToInt(p -> p.getCourseId())
					           .max()
					           .orElse(0);
			          newCourse.setCourseId(courseId+1);
					  newCourse.setCode(fields[0]);
					  newCourse.setName(fields[3]);
					  newCourse.setYear(Integer.parseInt(fields[1]));
					  newCourse.setSemester(Integer.parseInt(fields[2]));
					  Professor professor = professorRepository.findByCode(fields[4]);
					  if(professor != null) {
						  courseRepository.save(newCourse);
						  professor.getCourses().add(newCourse);
						  professorRepository.save(professor);
					  }
				  }
			 }
			 reader.close();
		}catch(IOException e) {
			System.err.println(e.getMessage());
		}		
	}
}
