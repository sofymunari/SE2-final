package com.polito.officequeue.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import java.util.Date;
import java.util.HashSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.polito.officequeue.converter.RequestConverter;
import com.polito.officequeue.converter.TicketConverter;
import com.polito.officequeue.dto.TicketDto;
import com.polito.officequeue.entity.Counter;
import com.polito.officequeue.entity.Request;
import com.polito.officequeue.entity.Ticket;
import com.polito.officequeue.repository.CounterRepository;
import com.polito.officequeue.repository.RequestRepository;
import com.polito.officequeue.repository.TicketRepository;
import com.polito.officequeue.utils.RequestQueue;
import com.polito.officequeue.service.TicketService;

@Service
public class TicketServiceImpl implements TicketService{
	
	@Autowired
    private TicketRepository ticketRepository;
	
	@Autowired
    private CounterRepository counterRepository;
	
	@Autowired
    private RequestRepository requestRepository;

	
	@Autowired
	public TicketServiceImpl(TicketRepository ticketRepository, RequestRepository requestRepository, CounterRepository counterRepository)
	{
		this.ticketRepository = ticketRepository;
		this.counterRepository = counterRepository;
		this.requestRepository = requestRepository;
	}
	
	
	//serve current ticket number on counter (the worker select button to serve ticket and after choose the next one) 
		@Override
		public void ServedTicket(Integer ticket_number, Integer counter_id) {
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			Date todayDate = new Date();
			String today = sdf.format(todayDate);
	        
			Ticket ticket = this.ticketRepository
					        .findByTicketNumber(ticket_number)
					        .stream()
					        .filter(t -> sdf.format(t.getDate()).equals(today))
					        .collect(Collectors.toList()).get(0);
			Counter counter = this.counterRepository.findById(counter_id).get();
			if(counter != null) {
				ticket.setCounter(counter);
				this.ticketRepository.save(ticket);
			}
		}

		@Override
		public TicketDto findNextTicketToServe(Integer counter) {
			try {
				//find every request that counter can serve
				List<Request> requests =  this.counterRepository.findAll()
						                  .stream()
						                  .filter(x -> x.getCounterId() == counter)
						                  .collect(Collectors.toList())
						                  .get(0)
						                  .getRequests();
				if(requests.size() == 0)
					return null;
				//find the number of ticket that not served yet for each request today
				List<RequestQueue> requestQueue = new ArrayList<RequestQueue>();
				
				Integer min_time = Integer.MAX_VALUE, pos_min_time = 0, i = 0;
				for(Request r : requests) {
					//group by request number ticket not served
					RequestQueue reqQ = new RequestQueue();
					int count = this.ticketRepository.countByRequestAndCounter(r, null);
					reqQ.setLengthQueue(count);
					reqQ.setRequestId(r.getRequestId());
					reqQ.setTagName(r.getTagName());
					requestQueue.add(reqQ);
					System.out.println("2:"+reqQ.getTagName());
					//search min service_time for each request
					if(min_time < r.getServiceTime())
					{
						pos_min_time = i;
						min_time = r.getServiceTime();
					}
					i++;
				}
				//search the min and max length of queue that counter can serve
				int pos_max_length=0;
				Integer max_length = Integer.MIN_VALUE, min_length = Integer.MAX_VALUE;
				for(i=0;i<requestQueue.size();i++) {
					int value = requestQueue.get(i).getLengthQueue();
					if(value > max_length) {
						max_length = value;
						pos_max_length = i;
					}
					if(value < min_length) {
						min_length = value;
					}
				}
				Request request_to_serve;
				if(max_length == 0)
					return null;
				if(max_length != min_length) {
					//choose the request queue with max length
					request_to_serve = requests.get(pos_max_length);
				}
				else {
					request_to_serve = requests.get(pos_min_time);
				}
				
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
				Date todayDate = new Date();
				String today = sdf.format(todayDate);
				
				//choose the first ticket to serve 
				return TicketConverter.toDto(this.ticketRepository
					  .findByRequestAndCounterOrderByTicketNumberAsc(request_to_serve, null)
					  .stream()
					  .filter(t -> sdf.format(t.getDate()).equals(today))
					  .collect(Collectors.toList()).get(0));
			}catch(Exception e){
				return null;
			}
		}


	
	@Override
	public TicketDto saveTicket (String tagName) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		Date todayDate = new Date();
		String today = sdf.format(todayDate);
		
		List<Ticket> ticketList = ticketRepository.findAll();
		
		//get the max ticketNumber from the ticket already in the  
		//database (considering only the tickets of today)
		Integer index = ticketList.stream()		
				.filter(t -> sdf.format(t.getDate()).equals(today))
				.sorted((t1,t2) -> t1.getTicketNumber() - t2.getTicketNumber())
				.mapToInt(t -> t.getTicketNumber())
				.max()
				.orElse(0);
		
		index++;
				
		//retrieving from the BD the request related to the tagName
		System.out.println(tagName);
		Request request = requestRepository.findByTagName(tagName).get(0);
		
		Ticket ticket = new Ticket(null,todayDate, index, null, RequestConverter.toDto(request));
				
		ticketRepository.save(ticket);
		
		return TicketConverter.toDto(ticket);
		
	}


	@Override
	public int getQueueLength(String tagName){
		Request request = requestRepository.findByTagName(tagName).get(0);

		return ticketRepository.countByRequest(request);

	}
}
