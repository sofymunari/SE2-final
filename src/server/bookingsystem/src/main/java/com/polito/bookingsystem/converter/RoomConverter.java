package com.polito.bookingsystem.converter;

import com.polito.bookingsystem.dto.RoomDto;
import com.polito.bookingsystem.entity.Room;

public class RoomConverter {
	public static Room toEntity(RoomDto roomDto) {
		Room room = new Room(roomDto.getRoomId(), roomDto.getName(), roomDto.getNumberOfSeat());
		return room;
	}
	
	public static RoomDto toDto(Room room) {
		RoomDto roomDto = new RoomDto(room.getRoomId(), room.getName(), room.getNumberOfSeat());
		return roomDto;
	}
    
}
