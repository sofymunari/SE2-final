package com.polito.bookingsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.polito.bookingsystem.dto.BookingDto;
import com.polito.bookingsystem.service.BookingService;

@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*", maxAge= 3000)
@RestController
public abstract class BookingController {
	@Autowired
	BookingService bookingService;
	
	@GetMapping(value="studentbookings/{email}")
	public List<BookingDto> getStudentBookings(@PathVariable String email) {
        return bookingService.getListBooking(email);
	}
	
	@PostMapping(value = "addBooking/", headers="Content-Type=application/json")
    @ResponseBody
	public BookingDto addBooking(@RequestBody Integer lectureId, String email) {
         return bookingService.addBooking(lectureId, email);
         
	}
	
	@DeleteMapping(value="deletebooking/{bookingId}")
	public Boolean getStudentBookings(@PathVariable Integer bookingId) {
        return bookingService.deleteBooking(bookingId);
	}
}
