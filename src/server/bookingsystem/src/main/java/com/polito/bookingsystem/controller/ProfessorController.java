package com.polito.bookingsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.polito.bookingsystem.service.ProfessorService;
import com.polito.bookingsystem.service.BookingService;
import com.polito.bookingsystem.entity.Booking;
import com.polito.bookingsystem.repository.BookingEntry;

import java.util.List;


@RestController
public class ProfessorController {
	
	@Autowired
	ProfessorService professorService;
	
	@Autowired
	BookingService bookingService;
	
	
	@RequestMapping(value= "/booking/{id}", method= RequestMethod.GET)
	public List<BookingEntry> getBooking(@PathVariable int id) {
			return bookingService.getBooking(id);
	}
	

}