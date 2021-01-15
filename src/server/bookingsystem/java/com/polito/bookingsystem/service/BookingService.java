package com.polito.bookingsystem.service;
import java.util.List;
import com.polito.bookingsystem.dto.LectureDto;
import com.polito.bookingsystem.dto.BookingDto;
import com.polito.bookingsystem.utils.BookingEntry;

public interface BookingService {
	List<BookingDto> getListBooking(String email);
	List<BookingDto> getListAllBookings();
	BookingDto addBooking(Integer lectureId, String email);
	Boolean deleteBooking(Integer bookingId);
	List<BookingEntry> getBooking(String email);
	List<BookingDto> getBookingsByLecture(LectureDto lectureDto);
	void save(BookingDto bookingDto);
	BookingDto getByLectureAndStudent(Integer lectureId, String email);
    Boolean deleteBookingOfficer(Integer id);
}

