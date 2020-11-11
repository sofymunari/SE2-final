package com.polito.bookingsystem.dto;


public class ClassDto {
	private Integer classId;
	private String room;
	private Integer numberOfSeat;
	
	public ClassDto() {}
	
	public ClassDto(Integer classId, String room, Integer numberOfSeat) {
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
