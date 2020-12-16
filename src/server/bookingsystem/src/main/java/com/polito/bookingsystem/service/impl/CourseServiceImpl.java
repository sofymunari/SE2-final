package com.polito.bookingsystem.service.impl;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.polito.bookingsystem.converter.CourseConverter;
import com.polito.bookingsystem.converter.LectureConverter;
import com.polito.bookingsystem.dto.CourseDto;
import com.polito.bookingsystem.dto.LectureDto;
import com.polito.bookingsystem.entity.Course;
import com.polito.bookingsystem.entity.Lecture;
import com.polito.bookingsystem.entity.Professor;
import com.polito.bookingsystem.repository.CourseRepository;
import com.polito.bookingsystem.repository.ProfessorRepository;
import com.polito.bookingsystem.repository.LectureRepository;
import com.polito.bookingsystem.service.CourseService;
import com.polito.bookingsystem.service.LectureService;

@Service
public class CourseServiceImpl implements CourseService{

	@Autowired
    private ProfessorRepository professorRepository;
	
	@Autowired
    private CourseRepository courseRepository;
	
	@Autowired
    private LectureService lectureService;
	
	@Autowired
	private LectureRepository lectureRepository;
	
    @Autowired
    public CourseServiceImpl(ProfessorRepository professorRepository, CourseRepository courseRepository, LectureRepository lectureRepository,LectureService lectureService)
    {
    	this.professorRepository = professorRepository;
    	this.courseRepository = courseRepository;
    	this.lectureRepository = lectureRepository;
    	this.lectureService = lectureService;

    }
    
    
	@Override
	public void addCourses(String fileName) {
		try (BufferedReader reader = new BufferedReader(new FileReader(fileName))){
			 String currentLine = reader.readLine(); //read first line
			 while((currentLine = reader.readLine()) != null){
				  String[] fields = currentLine.split(",");
				  
				  Course course = courseRepository.findByCode(fields[0]);
				  if(course == null) {
					  Course newCourse = new Course();
					  Integer courseId = courseRepository.findAll().stream()
					           .mapToInt(Course::getCourseId)
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
		}catch(IOException e) {
			System.err.println(e.getMessage());
		} 
	}
	
	@Override
	public List<Course> getCourses(){
		return courseRepository.findAll();
		
	}

  
	@Override
	public void setCourseToRemote(String courseName) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date dateobj = new Date();
		CourseDto courseDto = CourseConverter.toDto(courseRepository.findByName(courseName));
		List<LectureDto> lectureDtos = lectureService.getListAllLectures();
		if(courseDto != null) {
			for (LectureDto lectureDto : lectureDtos) { 
				if(lectureDto.getCourseDto().getName().equals(courseDto.getName())) {
					if(lectureDto.getRemotly()) {
						continue;
					}
					else if(lectureDto.getDeleted()) {
						continue;
					}
					else if((lectureDto.getDate()).compareTo(dateobj) <= 0) {
						continue;
					}
					lectureDto.setRemotly(true);
					Lecture lecture = LectureConverter.toEntity(lectureDto);
					lectureRepository.save(lecture);
					System.out.println(lectureDto.getCourseDto().getName());
				}
			}
		}
		
	}
}
