package com.polito.bookingsystem.converter;

import com.polito.bookingsystem.dto.LectureDto;
import com.polito.bookingsystem.entity.Lecture;

public class LectureConverter {

    public static Lecture toEntity(LectureDto lectureDto) {
		if( lectureDto == null)
			return null;
    	Lecture lecture = new Lecture();
		lecture.setCourse(CourseConverter.toEntity(lectureDto.getCourseDto()));
		lecture.setDate(lectureDto.getDate());
		lecture.setDuration(lectureDto.getDuration());
		lecture.setLectureId(lectureDto.getLectureId());
		lecture.setNumberOfLesson(lectureDto.getNumberOfLesson());
		lecture.setProfessor(ProfessorConverter.toEntity(lectureDto.getProfessorDto()));
		lecture.setProgramDetails(lectureDto.getProgramDetails());
		lecture.setRemotly(lectureDto.getRemotly());
		lecture.setRoom(RoomConverter.toEntity(lectureDto.getRoomDto()));
		return lecture;
	}
	
	public static LectureDto toDto(Lecture lecture) {
		if( lecture == null)
			return null;
		LectureDto lectureDto = new LectureDto();
		lectureDto.setCourseDto(CourseConverter.toDto(lecture.getCourse()));
		lectureDto.setDate(lecture.getDate());
		lectureDto.setDuration(lecture.getDuration());
		lectureDto.setLectureId(lecture.getLectureId());
		lectureDto.setNumberOfLesson(lecture.getNumberOfLesson());
		lectureDto.setProfessorDto(ProfessorConverter.toDto(lecture.getProfessor()));
		lectureDto.setProgramDetails(lecture.getProgramDetails());
		lectureDto.setRemotly(lecture.getRemotly());
		lectureDto.setRoomDto(RoomConverter.toDto(lecture.getRoom()));
		return lectureDto;
	}
}