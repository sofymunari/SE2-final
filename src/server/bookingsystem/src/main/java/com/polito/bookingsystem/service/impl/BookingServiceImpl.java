package com.polito.bookingsystem.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import com.polito.bookingsystem.entity.Booking;
import com.polito.bookingsystem.repository.BookingEntry;
import com.polito.bookingsystem.repository.BookingRepository;
import com.polito.bookingsystem.service.BookingService;


@Service
public class BookingServiceImpl implements BookingService{
	
	@Autowired
	BookingRepository bookingRepository;

	@Override
	public List<BookingEntry> getBooking(int id) {
		return bookingRepository.findByProfessor(id);
	}
}
