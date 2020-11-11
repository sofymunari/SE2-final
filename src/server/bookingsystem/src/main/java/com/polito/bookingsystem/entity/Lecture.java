package com.polito.bookingsystem.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.polito.bookingsystem.dto.CourseDto;
import com.polito.bookingsystem.dto.ProfessorDto;

@Entity
public class Lecture {
	
	@Column
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	private Integer lectureId;
	
	@Column
	private Integer numberOfLesson;
	
	@ManyToOne
    @JoinColumn(name ="courseId")
	private Course course;
	
	@ManyToOne
    @JoinColumn(name ="userId")
	private Professor professor;
	
	@Column
	private Boolean remotly;
	
	@Column
	private Date date;
	
	@Column
	private String programDetails;
	
	@Column
	private Integer numberOfSeat;
	
	@Column
	private String room;
	
	public Lecture() {
	}
	
	public Lecture(Integer lectureId, Integer numberOfLesson, Course course, Professor professor, Boolean remotly,
			Date date, String programDetails, Integer numberOfSeat, String room) {
		super();
		this.lectureId = lectureId;
		this.numberOfLesson = numberOfLesson;
		this.course = course;
		this.professor = professor;
		this.remotly = remotly;
		this.date = date;
		this.programDetails = programDetails;
		this.numberOfSeat = numberOfSeat;
		this.room = room;
	}
	public Integer getLectureId() {
		return this.lectureId;
	}
	public void setLectureId(Integer lectureId) {
		this.lectureId = lectureId;
	}
	public Integer getNumberOfLesson() {
		return numberOfLesson;
	}
	public void setNumberOfLesson(Integer numberOfLesson) {
		this.numberOfLesson = numberOfLesson;
	}
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	public Professor getProfessor() {
		return professor;
	}
	
	public void setProfessor(Professor professor) {
		this.professor = professor;
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
	


}
