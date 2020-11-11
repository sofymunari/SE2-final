package com.polito.bookingsystem.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.polito.bookingsystem.dto.LectureDto;
import com.polito.bookingsystem.dto.StudentDto;
import com.polito.bookingsystem.utils.BookingInfo;

@Entity
public class Booking {
	@Column
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	private Integer bookingId;
	
	@ManyToOne
    @JoinColumn(name ="userId")
	private Student student;
	
	@ManyToOne
    @JoinColumn(name ="lectureId")
	private Lecture lecture;
	
	@Column
	private BookingInfo bookingInfo;
	
	public Booking() {
	}
	
	public Booking(Integer bookingId, Student student, Lecture lecture, BookingInfo bookingInfo) {
		super();
		this.bookingId = bookingId;
		this.student = student;
		this.lecture = lecture;
		this.bookingInfo = bookingInfo;
	}

	public Integer getBookingId() {
		return bookingId;
	}

	public void setBookingId(Integer bookingId) {
		this.bookingId = bookingId;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Lecture getLecture() {
		return lecture;
	}

	public void setLecture(Lecture lecture) {
		this.lecture = lecture;
	}

	public BookingInfo getBookingInfo() {
		return bookingInfo;
	}

	public void setBookingInfo(BookingInfo bookingInfo) {
		this.bookingInfo = bookingInfo;
	}
}
