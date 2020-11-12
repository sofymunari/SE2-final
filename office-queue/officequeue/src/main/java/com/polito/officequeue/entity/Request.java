package com.polito.officequeue.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Request {
	
	@Column
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	private Integer requestId;
	
    @Column
    private String tagName;
    
    @Column
    private Integer serviceTime;
    
    public Request() {
    	
    }
    
	public Request(Integer requestId, String tagName, Integer serviceTime) {
		super();
		this.requestId = requestId;
		this.tagName = tagName;
		this.serviceTime = serviceTime;
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
