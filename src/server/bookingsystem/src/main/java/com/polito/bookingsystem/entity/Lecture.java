package com.polito.bookingsystem.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table
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
	private Integer duration;
	
	@Column
	private Integer bookedSeats;
	
	@ManyToOne
    @JoinColumn(name ="roomId")
	private Room room;
    
	
	public Lecture() {
	}
	
	public Lecture(Integer lectureId, Integer numberOfLesson, Course course, Professor professor, Boolean remotly,
			Date date, Integer duration, String programDetails, Room room) {
		super();
		this.lectureId = lectureId;
		this.numberOfLesson = numberOfLesson;
		this.course = course;
		this.professor = professor;
		this.remotly = remotly;
		this.date = date;
		this.programDetails = programDetails;
		this.duration = duration;
		this.room = room;
		this.bookedSeats = 0;
	}
	
	public Integer getBookedSeats() {
		return bookedSeats;
	}
	
	public void setBookedSeats(Integer bookedSeats) {
		this.bookedSeats = bookedSeats;
	}

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
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

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}
	

}
