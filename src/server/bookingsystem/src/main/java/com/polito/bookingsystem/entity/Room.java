package com.polito.bookingsystem.entity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Room {
	@Column
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	private Integer roomId;
	
	@Column
	private String name;
	
	@Column
	private Integer numberOfSeat;
	
	public Room() {}
	public Room(Integer roomId, String name, Integer numberOfSeat) {
		super();
		this.roomId = roomId;
		this.name = name;
		this.numberOfSeat = numberOfSeat;
	}
	public Integer getRoomId() {
		return roomId;
	}
	public void setRoomId(Integer roomId) {
		this.roomId = roomId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getNumberOfSeat() {
		return numberOfSeat;
	}
	public void setNumberOfSeat(Integer numberOfSeat) {
		this.numberOfSeat = numberOfSeat;
	}
}
