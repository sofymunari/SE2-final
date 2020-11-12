package com.polito.officequeue.converter;

import com.polito.officequeue.dto.CounterDto;
import com.polito.officequeue.dto.RequestDto;
import com.polito.officequeue.entity.Counter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.polito.officequeue.dto.TicketDto;
import com.polito.officequeue.entity.Request;
import com.polito.officequeue.entity.Ticket;

public class CounterConverter {
	
	public static Counter toEntity(CounterDto counterDto) {
		Counter counter = null;
		List<Request> requests = new ArrayList<>();
		
		if(counterDto != null){
			for(RequestDto requestDto : counterDto.getRequestDtos()) {
				requests.add(RequestConverter.toEntity(requestDto));
			}

			counter = new Counter(counterDto.getCounterId(),requests);
		}
		
		return counter;
	}

	public static CounterDto toDto(Counter counter) {
		CounterDto counterDto;
		List<RequestDto> requestDtos = new ArrayList<>();
		
		for(Request request : counter.getRequests()) {
			requestDtos.add(RequestConverter.toDto(request));
		}
		
		counterDto = new CounterDto(counter.getCounterId(), requestDtos);
		return counterDto;
	}
}
