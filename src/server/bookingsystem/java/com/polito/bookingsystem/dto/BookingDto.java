package com.polito.bookingsystem.dto;
import com.polito.bookingsystem.utils.BookingInfo;

public class BookingDto {
	private Integer bookingId;
	private StudentDto studentDto;
	private LectureDto lectureDto;
	private BookingInfo bookingInfo;
	public BookingDto() {
	}
	public BookingDto(Integer bookingId, StudentDto studentDto, LectureDto lectureDto, BookingInfo bookingInfo) {
		super();
		this.bookingId = bookingId;
		this.studentDto = studentDto;
		this.lectureDto = lectureDto;
		this.bookingInfo = bookingInfo;
	}
	public Integer getBookingId() {
		return bookingId;
	}
	public void setBookingId(Integer bookingId) {
		this.bookingId = bookingId;
	}
	public StudentDto getStudentDto() {
		return studentDto;
	}
	public void setStudentDto(StudentDto studentDto) {
		this.studentDto = studentDto;
	}
	public LectureDto getLectureDto() {
		return lectureDto;
	}
	public void setLectureDto(LectureDto lectureDto) {
		this.lectureDto = lectureDto;
	}
	public BookingInfo getBookingInfo() {
		return bookingInfo;
	}
	public void setBookingInfo(BookingInfo bookingInfo) {
		this.bookingInfo = bookingInfo;
	}
}
