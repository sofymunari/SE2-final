package com.polito.bookingsystem.service;

import java.util.List;
import com.polito.bookingsystem.dto.LectureDto;

public interface LectureService {
	List<LectureDto> getListLectures(String email);
	List<LectureDto> getProfessorLectures(String email);
}
