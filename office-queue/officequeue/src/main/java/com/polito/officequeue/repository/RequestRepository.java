package com.polito.officequeue.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.polito.officequeue.entity.Counter;
import com.polito.officequeue.entity.Request;


public interface RequestRepository extends JpaRepository<Request, Integer>{

	List<Request> findByTagName(String tagName);

	 //@Query("SELECT requestId, tagName, serviceTime, counters, tickets \r\n" + 
	 //		"FROM request, counter.requests cr\r\n" + 
	 //		"WHERE request.requestId = cr.requestId\r\n" + 
	 //		"AND counter_request.counter_id = ?1")
	 
	 
	 
	 /*@Query("SELECT MIN(serviceTime)\r\n" +  
	 		"FROM request, counter.requests cr\r\n" + 
	 		"AND request.requestId = cr.requestId\r\n" + 
	 		"AND counter_request.counter_id =?1")
	 Integer findMinServiceTime(Counter counter_id);*/
	 
	 /*@Query("SELECT requestId, tagName, serviceTime, counters, tickets \r\n" + 
	 		"FROM request, counter.requests cr\r\n" + 
	 		"WHERE request.requestId = counter_request.request_id\r\n" + 
	 		"AND counter_request.counter_id = ?1\r\n" + 
	 		"AND request.serviceTime = ?2")
	  List<Request> findRequestByServiceTimeANDCounter(Integer counter_id, Integer service_time);*/
	
}
