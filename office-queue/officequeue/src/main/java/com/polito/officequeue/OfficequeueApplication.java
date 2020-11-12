package com.polito.officequeue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.polito.officequeue.repository.RequestRepository;
import com.polito.officequeue.repository.TicketRepository;
import com.polito.officequeue.service.TicketService;


@SpringBootApplication
public class OfficequeueApplication {

	@Autowired
	private TicketRepository ticket_repository;
	
	@Autowired
	private RequestRepository request_repository;
	
	public static void main(String[] args) {
		SpringApplication.run(OfficequeueApplication.class, args);
	}

}
