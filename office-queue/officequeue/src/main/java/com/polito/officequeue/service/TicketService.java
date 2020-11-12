package com.polito.officequeue.service;


import org.springframework.stereotype.Service;

import com.polito.officequeue.dto.TicketDto;

@Service
public interface TicketService {
	
	public void ServedTicket(Integer ticket_number, Integer counter_id);
    public TicketDto findNextTicketToServe(Integer counter);
    public TicketDto saveTicket(String tagName);
    public int getQueueLength(String tagName);
}
