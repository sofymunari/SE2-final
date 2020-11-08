package com.polito.bookingsystem.dto;

import java.util.Date;

public class LectureDto {
	private String LectureId;//L...
	private Integer numberOfLesson;
	private String courseId;
	private String professorId;
	private Boolean remotly;
	private Date date;
	private String programDetails;
	private Integer numberOfSeat;
	
	public LectureDto() {
	}
	public LectureDto(String lectureId, Integer numberOfLesson, String courseId, String professorId, Boolean remotly,
			Date date, String programDetails, Integer numberOfSeat) {
		super();
		LectureId = lectureId;
		this.numberOfLesson = numberOfLesson;
		this.courseId = courseId;
		this.professorId = professorId;
		this.remotly = remotly;
		this.date = date;
		this.programDetails = programDetails;
		this.numberOfSeat = numberOfSeat;
	}
	public String getLectureId() {
		return LectureId;
	}
	public void setLectureId(String lectureId) {
		LectureId = lectureId;
	}
	public Integer getNumberOfLesson() {
		return numberOfLesson;
	}
	public void setNumberOfLesson(Integer numberOfLesson) {
		this.numberOfLesson = numberOfLesson;
	}
	public String getCourseId() {
		return courseId;
	}
	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}
	public String getProfessorId() {
		return professorId;
	}
	public void setProfessorId(String professorId) {
		this.professorId = professorId;
	}
	public Boolean getRemotly() {
		return remotly;
	}
	public void setRemotly(Boolean remotly) {
		this.remotly = remotly;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getProgramDetails() {
		return programDetails;
	}
	public void setProgramDetails(String programDetails) {
		this.programDetails = programDetails;
	}
	public Integer getNumberOfSeat() {
		return numberOfSeat;
	}
	public void setNumberOfSeat(Integer numberOfSeat) {
		this.numberOfSeat = numberOfSeat;
	}
	
	@Override
	public String toString() {
		return "LectureDto [LectureId=" + LectureId + ", numberOfLesson=" + numberOfLesson + ", courseId=" + courseId
				+ ", professorId=" + professorId + ", remotly=" + remotly + ", date=" + date + ", programDetails="
				+ programDetails + ", numberOfSeat=" + numberOfSeat + "]";
	}

}
