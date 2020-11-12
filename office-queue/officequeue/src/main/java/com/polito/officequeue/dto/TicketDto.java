package com.polito.officequeue.dto;

import java.util.Date;

import com.polito.officequeue.entity.Counter;
import com.polito.officequeue.entity.Request;

public class TicketDto {
	
	Integer ticketId;	
	Date date;		
	Integer ticketNumber;		
    CounterDto counterDto;	
    RequestDto requestDto;
    
	public TicketDto(Integer ticketId, Date date, Integer ticketNumber, CounterDto counterDto, RequestDto requestDto) {
		super();
		this.ticketId = ticketId;
		this.date = date;
		this.ticketNumber = ticketNumber;
		this.counterDto = counterDto;
		this.requestDto = requestDto;
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
