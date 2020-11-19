package com.polito.bookingsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.polito.bookingsystem.dto.BookingDto;
import com.polito.bookingsystem.dto.StudentDto;
import com.polito.bookingsystem.service.BookingService;
import com.polito.bookingsystem.service.StudentService;

@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*", maxAge= 3000)
@RestController
public abstract class BookingController {
	@Autowired
	BookingService bookingService;
	
	@RequestMapping(value="studentbookings/{email}", method = RequestMethod.GET)
	public List<BookingDto> getStudentBookings(@PathVariable String email) {
        return bookingService.getListBooking(email);
	}
	
	@RequestMapping(value = "addBooking/", headers="Content-Type=application/json", method = RequestMethod.POST)
    @ResponseBody
	public BookingDto addBooking(@RequestBody Integer lectureId, String email) {
         return bookingService.addBooking(lectureId, email);
	}
	
	@RequestMapping(value="deletebooking/{bookingId}", method = RequestMethod.DELETE)
	public Boolean getStudentBookings(@PathVariable Integer bookingId) {
        return bookingService.deleteBooking(bookingId);
	}
}
