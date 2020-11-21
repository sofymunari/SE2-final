package com.polito.bookingsystem.converter;


import com.polito.bookingsystem.dto.BookingDto;
import com.polito.bookingsystem.entity.Booking;

public class BookingConverter {
	
	public static Booking toEntity(BookingDto bookingDto) {
		if( bookingDto == null)
			return null;
		Booking booking = new Booking();
		booking.setBookingId(bookingDto.getBookingId());
		booking.setBookingInfo(bookingDto.getBookingInfo());
		booking.setLecture(LectureConverter.toEntity(bookingDto.getLectureDto()));
		booking.setStudent(StudentConverter.toEntity(bookingDto.getStudentDto()));
		return booking;
		
	}
	
    public static BookingDto toDto(Booking booking) {
		if( booking == null)
			return null;
		BookingDto bookingDto = new BookingDto();
		bookingDto.setBookingId(booking.getBookingId());
		bookingDto.setBookingInfo(booking.getBookingInfo());
		bookingDto.setLectureDto(LectureConverter.toDto(booking.getLecture()));
		bookingDto.setStudentDto(StudentConverter.toDto(booking.getStudent()));
		return bookingDto;
    }
}
