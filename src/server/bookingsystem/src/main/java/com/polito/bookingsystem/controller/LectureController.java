package com.polito.bookingsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.polito.bookingsystem.dto.BookingDto;
import com.polito.bookingsystem.dto.LectureDto;
import com.polito.bookingsystem.dto.StudentDto;
import com.polito.bookingsystem.service.BookingService;
import com.polito.bookingsystem.service.LectureService;
import com.polito.bookingsystem.service.StudentService;
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*", maxAge= 3000)
@RestController
public abstract class LectureController {
	@Autowired
	LectureService lectureService;
	
	
	@RequestMapping(value="studentlectures/{email}", method = RequestMethod.GET)
	public List<LectureDto> getStudentLectures(@PathVariable String email) {
        return lectureService.getListLectures(email);
	}
}
