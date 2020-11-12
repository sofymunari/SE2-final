package com.polito.officequeue.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.polito.officequeue.entity.Counter;
import com.polito.officequeue.entity.Request;
import com.polito.officequeue.entity.Ticket;
import com.polito.officequeue.utils.RequestQueue;

public interface TicketRepository extends JpaRepository<Ticket, Integer>{
	/*@Query("SELECT ticketId, ticketNumber, request, counter, date \r\n" + 
			"FROM ticket\r\n" + 
			"WHERE ticket.counter.counterId = ?1\r\n" + 
			"AND ticket.counter.counterId IS NOT NULL\r\n" + 
			"AND date = CURRENT_DATE()")*/
	
	List<Ticket> findByCounter(Counter counter);
	
	/*@Query("SELECT t\r\n" + 
			"FROM ticket t\r\n" + 
			"WHERE t.date = ?2\r\n" + 
			"AND t.ticket_number=?1\r\n"
			)*/
     List<Ticket> findByTicketNumber(Integer ticketNumber);
	
	//List<Ticket> findByticketNumberAndCounter(Counter c);
	
	/*@Query("SELECT t\r\n" + 
			"FROM ticket t\r\n" + 
			"WHERE t.date = ?1\r\n" + 
			"AND t.request = ?2 " +
			"AND Counter"
			"ORDER BY t.ticketNumber")*/
	public List<Ticket> findByRequestAndCounterOrderByTicketNumberAsc(Request request, Counter counter);
	
	
	/*@Query("SELECT requestId, tagName, COUNT(*)\r\n" + 
			"FROM ticket, request \r\n" + 
			"WHERE ticket.request.requestId = request.requestId\r\n" + 
			"WHERE date = CURRENT_DATE()\r\n" + 
			"AND ticket.counter.counterId IS NULL\r\n" + 
			"GROUP BY request.requestId, request.tagName")*/
	Integer countByRequestAndCounter(Request request, Counter counter);


	Integer countByRequest(Request request);
	
	
	/*
	//statistics
	@Query("SELECT request.requestId, tagName, COUNT(*)\r\n" + 
			"FROM ticket, request\r\n" + 
			"WHERE ticket.request.requestId = request.requestId\r\n" + 
			"WHERE date = ?1\r\n" + 
			"AND ticket.counter.counterId IS NOT NULL\r\n" + 
			"GROUP BY request.requestId, request.tagName")
	List<RequestQueue> findTicketServedByDay(String date);
	
	@Query("SELECT requestId, tagName, COUNT(*)\r\n" + 
			"FROM ticket,request\r\n" + 
			"WHERE ticket.request.requestId = request.requestId\r\n" + 
			"WHERE MONTH(date) = ?1 " + 
			"AND ticket.request.counterId IS NOT NULL\r\n" + 
			"GROUP BY request.requestId, request.tagName")
	List<RequestQueue> findTicketServedByMonth(Integer Month);
	
	@Query("SELECT requestId, tagName, COUNT(*)\r\n" + 
			"FROM ticket,request\r\n" + 
			"WHERE ticket.request.requestId = request.requestId\r\n" + 
			"WHERE YEAR(date) = ?1 " + 
			"AND ticket.counter.counterId IS NOT NULL\r\n" + 
			"GROUP BY request.requestId, request.tagName")
	List<RequestQueue> findTicketServedByYear(Integer year);
*/
}
