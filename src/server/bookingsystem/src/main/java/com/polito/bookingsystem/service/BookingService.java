package com.polito.bookingsystem.service;

import com.polito.bookingsystem.repository.BookingEntry;

import java.util.List;

public interface BookingService {
	List<BookingEntry> getBooking(int id);
}
