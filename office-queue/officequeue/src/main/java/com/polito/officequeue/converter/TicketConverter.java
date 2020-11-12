package com.polito.officequeue.converter;

import java.util.Date;

import com.polito.officequeue.dto.CounterDto;
import com.polito.officequeue.dto.RequestDto;
import com.polito.officequeue.dto.TicketDto;
import com.polito.officequeue.entity.Request;
import com.polito.officequeue.entity.Ticket;

public class TicketConverter {
	private Integer ticketId;	
	private Date date;		
	private Integer ticketNumber;		
    private CounterDto counterDto;	
    private RequestDto requestDto;
    
	public static Ticket toEntity(TicketDto ticketDto) {
		
		if(ticketDto == null) {
			return null;
		}
		
		Ticket ticket;
		ticket = new Ticket(ticketDto.getTicketId(),ticketDto.getDate(), ticketDto.getTicketNumber(),ticketDto.getCounterDto(), ticketDto.getRequestDto());
		return ticket;
	}
	
	public static TicketDto toDto(Ticket ticket) {
		
		if(ticket == null) {
			return null;
		}
		
		TicketDto ticketDto;
		if(ticket.getCounter() != null)
			ticketDto = new TicketDto(ticket.getTicketId(), ticket.getDate(), ticket.getTicketNumber(), CounterConverter.toDto(ticket.getCounter()), RequestConverter.toDto(ticket.getRequest()));
		else
			ticketDto = new TicketDto(ticket.getTicketId(), ticket.getDate(), ticket.getTicketNumber(), null, RequestConverter.toDto(ticket.getRequest()));

		return ticketDto;
   }

	public Integer getTicketId() {
		return ticketId;
	}

	public void setTicketId(Integer ticketId) {
		this.ticketId = ticketId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Integer getTicketNumber() {
		return ticketNumber;
	}

	public void setTicketNumber(Integer ticketNumber) {
		this.ticketNumber = ticketNumber;
	}

	public CounterDto getCounterDto() {
		return counterDto;
	}

	public void setCounterDto(CounterDto counterDto) {
		this.counterDto = counterDto;
	}

	public RequestDto getRequestDto() {
		return requestDto;
	}

	public void setRequestDto(RequestDto requestDto) {
		this.requestDto = requestDto;
	}
	
   
}

