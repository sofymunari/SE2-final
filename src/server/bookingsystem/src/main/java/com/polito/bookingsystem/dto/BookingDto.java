package com.polito.bookingsystem.dto;

import com.polito.bookingsystem.utils.BookingInfo;


public class BookingDto {
	private Integer bookingId;
	private String studentId;
	private String lectureId;
	private BookingInfo bookingInfo;
	
	public BookingDto() {
	}
	
	public BookingDto(Integer bookingId, String studentId, String lectureId, BookingInfo bookingInfo) {
		super();
		this.bookingId = bookingId;
		this.studentId = studentId;
		this.lectureId = lectureId;
		this.bookingInfo = bookingInfo;
	}

	public Integer getBookingId() {
		return bookingId;
	}

	public void setBookingId(Integer bookingId) {
		this.bookingId = bookingId;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public String getLectureId() {
		return lectureId;
	}

	public void setLectureId(String lectureId) {
		this.lectureId = lectureId;
	}

	public BookingInfo getBookingInfo() {
		return bookingInfo;
	}

	public void setBookingInfo(BookingInfo bookingInfo) {
		this.bookingInfo = bookingInfo;
	}

	@Override
	public String toString() {
		return "BookingDto [bookingId=" + bookingId + ", studentId=" + studentId + ", lectureId=" + lectureId
				+ ", bookingInfo=" + bookingInfo + "]";
	}
}
