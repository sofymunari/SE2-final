package com.polito.bookingsystem.service;

import java.util.List;
import com.polito.bookingsystem.dto.BookingDto;
import com.polito.bookingsystem.utils.BookingEntry;

public interface BookingService {
	
	List<BookingDto> getListBooking(String email);

	BookingDto addBooking(Integer lectureId, String email);

	Boolean deleteBooking(Integer bookingId);
	
	List<BookingEntry> getBooking(String email);

}

