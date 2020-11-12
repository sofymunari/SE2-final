package com.polito.officequeue.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.polito.officequeue.converter.CounterConverter;
import com.polito.officequeue.converter.RequestConverter;
import com.polito.officequeue.dto.CounterDto;
import com.polito.officequeue.dto.RequestDto;

@Entity
public class Ticket {

	@Column
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	private Integer ticketId;

	@Column
	private Date date;
	
	@Column
	private Integer ticketNumber;
	
	@ManyToOne
    @JoinColumn(name ="counterId")
    private Counter counter;
	
	@ManyToOne
    @JoinColumn(name ="requestId")
    private Request request;

	public Ticket() {
		
	}
	
	
	public Ticket(Integer ticketId, Date date2, Integer ticketNumber, CounterDto counterDto, RequestDto requestDto) {
		super();
		this.ticketId = ticketId;
		this.date = date2;
		this.ticketNumber = ticketNumber;
		this.request = RequestConverter.toEntity(requestDto);
		this.counter = CounterConverter.toEntity(counterDto);
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

	public Counter getCounter() {
		return counter;
	}

	public void setCounter(Counter counter) {
		this.counter = counter;
	}

	public Request getRequest() {
		return request;
	}

	public void setRequest(Request request) {
		this.request = request;
	}
	
	

}
