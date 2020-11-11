package com.polito.bookingsystem.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Class {
	@Column
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	private Integer classId;
	
	@Column
	private String room;
	
	@Column
	private Integer numberOfSeat;
	
	public Class() {}
	
	public Class(Integer classId, String room, Integer numberOfSeat) {
		super();
		this.classId = classId;
		this.room = room;
		this.numberOfSeat = numberOfSeat;
	}

	public Integer getClassId() {
		return classId;
	}

	public void setClassId(Integer classId) {
		this.classId = classId;
	}

	public String getRoom() {
		return room;
	}

	public void setRoom(String room) {
		this.room = room;
	}

	public Integer getNumberOfSeat() {
		return numberOfSeat;
	}

	public void setNumberOfSeat(Integer numberOfSeat) {
		this.numberOfSeat = numberOfSeat;
	}
    
}
