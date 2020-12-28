package com.polito.bookingsystem.service;
import java.util.List;
import com.polito.bookingsystem.dto.LectureDto;
import com.polito.bookingsystem.entity.Room;
import com.polito.bookingsystem.utils.Schedule;

public interface LectureService {
	List<LectureDto> getListLectures(String email);
	List<LectureDto> getProfessorLectures(String email);
	boolean deleteLecture(Integer lectureId);
	LectureDto getLectureById(Integer id);
	void save(LectureDto lectureDto);
	List<LectureDto> getListAllLectures();
	void addLectures(String fileName);
	public List<Schedule> getScheduleCourses(String codeCourse);
	List<Room> getRooms();
	public Boolean modifySchedule(String day, Integer duration,String timeStart, Integer roomId, String codeCourse, Integer scheduleId);
}
