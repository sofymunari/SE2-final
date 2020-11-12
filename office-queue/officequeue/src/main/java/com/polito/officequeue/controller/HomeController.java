package com.polito.officequeue.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.polito.officequeue.entity.Ticket;
import com.polito.officequeue.repository.TicketRepository;
import com.polito.officequeue.entity.*;
import com.polito.officequeue.repository.*;
import com.polito.officequeue.converter.*;
@Controller
public class HomeController {

	@Autowired TicketRepository ticketRepository;
	@Autowired RequestRepository requestRepository;
	@Autowired CounterRepository counterRepository;
	
	@RequestMapping("/")
	public String home() {
		Request request1 = new Request(1, "Payment", 5); //service time in here is in minutes
		requestRepository.save(request1);
		Request request2 = new Request(2, "Shipping", 10);
		requestRepository.save(request2);
		Request request3 = new Request(3, "Withdrawal", 7);
		requestRepository.save(request3);
		Request request4 = new Request(4, "Subscribe", 2);
		requestRepository.save(request4);
		Request request5 = new Request(5, "Packaging", 15);
		requestRepository.save(request5);
		
		Counter counter1 = new Counter();
		Counter counter2 = new Counter();
		Counter counter3 = new Counter();
		

		
		
//		counter1.setCounterId(1);
//		counter2.setCounterId(2);
//		counter3.setCounterId(3);
		
		counter1.addRequest(request1);
		counter2.addRequest(request2);
		counter2.addRequest(request3);
		counter3.addRequest(request4);
		counter3.addRequest(request5);
		counter3.addRequest(request1);
		
		counterRepository.save(counter1);
		counterRepository.save(counter2);
		counterRepository.save(counter3);
		
		Ticket ticket = new Ticket(1, new Date(), 1, CounterConverter.toDto(counter1), RequestConverter.toDto(request1));
		//1, new Date(), 1, CounterConverter.toDto(counter1), RequestConverter.toDto(request1)
//		ticket.setDate(new Date());
//		ticket.setCounter(counter1);
//		ticket.setTicketNumber(1);
		
		ticketRepository.save(ticket);
		
		return "index";
	}
	
	@RequestMapping("/serveTicket")
	public String serveTicket() {
		return "serve_ticket";
	}
	
	@RequestMapping("/nextTicket")
	public String nextTicket() {
		return "counter";
	}
	
}
