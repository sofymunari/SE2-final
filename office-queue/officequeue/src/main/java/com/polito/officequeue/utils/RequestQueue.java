package com.polito.officequeue.utils;


public class RequestQueue {
	
	
	private Integer requestId;
	
	
	private String tagName;
	
	
	private Integer lengthQueue;
	
	public RequestQueue() {
		
	}
	
	public void setRequestId(Integer requestId) {
		   this.requestId = requestId;
    }
    
    public Integer getRequestId() {
	       return this.requestId;
    }
    
    public void setTagName(String tagName) {
		   this.tagName = tagName;
	}
    
	public String getTagName() {
		  return this.tagName;
	}
	 
	public void setLengthQueue(Integer lengthQueue) {
		   this.lengthQueue = lengthQueue;
	}
	  
	public Integer getLengthQueue() {
		  return this.lengthQueue;
    }
}
