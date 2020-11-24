package com.polito.bookingsystem.dto;

public class RoomDto {
	private Integer roomId;
	private String name;
	private Integer numberOfSeat;
	
	public RoomDto() {}
	
	public RoomDto(Integer roomId, String name, Integer numberOfSeat) {
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
