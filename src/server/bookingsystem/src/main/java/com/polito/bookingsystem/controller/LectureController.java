package com.polito.bookingsystem.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.polito.bookingsystem.dto.LectureDto;
import com.polito.bookingsystem.service.LectureService;

@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*", maxAge= 3000)
@RestController
public abstract class LectureController {
	@Autowired
	LectureService lectureService;
	
	@GetMapping(value="studentlectures/{email}")
	public List<LectureDto> getStudentLectures(@PathVariable String email) {
        return lectureService.getListLectures(email);
	}
	
	@GetMapping(value="professor/deletelecture/{lectureId}")
	public Boolean deleteLecture(@PathVariable Integer lectureId) {
        return lectureService.deleteLecture(lectureId);
	}
}
