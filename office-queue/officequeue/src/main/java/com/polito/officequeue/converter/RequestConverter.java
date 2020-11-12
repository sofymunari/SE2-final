package com.polito.officequeue.converter;

import com.polito.officequeue.dto.RequestDto;
import com.polito.officequeue.entity.Request;



public class RequestConverter {
	
	public static Request toEntity(RequestDto requestDto) {
		Request request;
		request = new Request(requestDto.getRequestId(),requestDto.getTagName(),requestDto.getServiceTime());
		return request;
	}
	
	public static RequestDto toDto(Request request) {
		RequestDto reuqestDto; 
		reuqestDto = new RequestDto(request.getRequestId(), request.getTagName(), request.getServiceTime());
		return reuqestDto;
	}
}
