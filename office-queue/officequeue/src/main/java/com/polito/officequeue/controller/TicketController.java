package com.polito.officequeue.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.polito.officequeue.converter.TicketConverter;
import com.polito.officequeue.dto.TicketDto;
import com.polito.officequeue.repository.TicketRepository;
import com.polito.officequeue.service.TicketService;
import com.polito.officequeue.entity.Ticket;

@RestController
public class TicketController {

	@Autowired
	TicketService ticketService;

	//this is not used right now because ServedTicket function is called from nextTicket function(see line 34)
	@RequestMapping(value="/serveTicket/{counter_id}/{ticket_number}", method = RequestMethod.PUT)
	public void serveTicket(@PathVariable Integer counter_id, @PathVariable Integer ticket_number) {
		System.out.println("here i am" + counter_id);
		//Date date = new Date();
		//String dateString = new SimpleDateFormat("yyy-MM-dd").format(date);
		ticketService.ServedTicket(ticket_number, counter_id);
	}
	
	@RequestMapping(value="/nextTicket/{counter_id}", method = RequestMethod.PUT)
	public Integer nextTicket(@PathVariable Integer counter_id) {
		TicketDto nextTicket = ticketService.findNextTicketToServe(counter_id);
		if(nextTicket == null)
			return -1;
		ticketService.ServedTicket(nextTicket.getTicketNumber(), counter_id);
		return nextTicket.getTicketNumber();
	}
	
	@RequestMapping(value="/saveTicket/{tagName}", method = RequestMethod.PUT)
	public TicketDto saveTicket(@PathVariable String tagName) {
		return ticketService.saveTicket(tagName);
	}

	@RequestMapping(value="/queueLength/{tagName}", method = RequestMethod.GET)
	public int getQueueLength(@PathVariable String tagName) {
		return ticketService.getQueueLength(tagName);
	}




	/*
	@RequestMapping(value="/path1/{my_message}", method = RequestMethod.PUT)
	public String testFunction(@PathVariable String my_message) {
		return "my_message is: "+my_message;
	}
	
	@RequestMapping(value="/path2/getExample", method = RequestMethod.GET)
	public Ticket testFunction() {
		Ticket ticket = new Ticket();
		ticket.setDate(new Date());
		ticket.setTicketId(1235);
		ticket.setTicketNumber(8);
		return ticket;
	}
	*/
	
	
}
