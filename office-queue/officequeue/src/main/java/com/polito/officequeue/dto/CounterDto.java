package com.polito.officequeue.dto;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


import com.polito.officequeue.entity.Request;



public class CounterDto {
	Integer counterId;
	List<RequestDto> requestDtos = new ArrayList<>();
	

	
	public CounterDto() {
		
	}
	public CounterDto(Integer counterId, List<RequestDto> requestDtos) {
		super();
		this.counterId = counterId;
		this.requestDtos = requestDtos;
	}
	

	public Integer getCounterId() {
		return counterId;
	}

	public void setCounterId(Integer counterId) {
		this.counterId = counterId;
	}

	public List<RequestDto> getRequestDtos() {
		return requestDtos;
	}

	public void setRequestDtos(List<RequestDto> requestDtos) {
		this.requestDtos = requestDtos;
	}
	
    public void addRequestDto(RequestDto requestDto) {
        requestDtos.add(requestDto);
    }
 
    public void removeTag(RequestDto requestDto) {
    	requestDtos.remove(requestDto);
    }
	
}
