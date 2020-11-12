package com.polito.officequeue.entity;

import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.JoinColumn;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
public class Counter {
	
	@Column
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	private Integer counterId;
	
	@ManyToMany
	@JoinTable(name = "counter_request",
	    joinColumns = @JoinColumn(name = "counter_id"),
	    inverseJoinColumns = @JoinColumn(name = "request_id")
	)
	private List<Request> requests = new ArrayList<>();
	
    public Counter(Integer counterId, List<Request> requests2) {
		super();
		this.counterId = counterId;
		this.requests = requests2;
	}
    
    public Counter() {

	}

	public Integer getCounterId() {
		return counterId;
	}

	public void setCounterId(Integer counterId) {
		this.counterId = counterId;
	}

	public List<Request> getRequests() {
		return requests;
	}

	public void setRequests(List<Request> requests) {
		this.requests = requests;
	}

	public Set<Ticket> getTickets() {
		return tickets;
	}

	public void setTickets(Set<Ticket> tickets) {
		this.tickets = tickets;
	}

	@OneToMany(mappedBy="counter", cascade = CascadeType.ALL)
	private Set<Ticket> tickets = new HashSet<>();

	public void addRequest(Request request) {
        requests.add(request);
    }
 
    public void removeRequest(Request request) {
    	requests.remove(request);
    }
    
}
