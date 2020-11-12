package com.polito.officequeue.dto;

public class RequestDto {
	
	Integer requestId;
 	String tagName;
    Integer serviceTime;
    
	public RequestDto(Integer requestId, String tagName, Integer serviceTime) {
		super();
		this.requestId = requestId;
		this.tagName = tagName;
		this.serviceTime = serviceTime;
	}
	public RequestDto() {
		
	}
	
	
	public Integer getRequestId() {
		return requestId;
	}
	public void setRequestId(Integer requestId) {
		this.requestId = requestId;
	}
	public String getTagName() {
		return tagName;
	}
	public void setTagName(String tagName) {
		this.tagName = tagName;
	}
	public Integer getServiceTime() {
		return serviceTime;
	}
	public void setServiceTime(Integer serviceTime) {
		this.serviceTime = serviceTime;
	}
}