package com.polito.bookingsystem.converter;

import com.polito.bookingsystem.dto.RoomDto;
import com.polito.bookingsystem.entity.Room;

public class RoomConverter {
	public static Room toEntity(RoomDto roomDto) {
		if( roomDto == null)
			return null;
		Room room = new Room();
		room.setClassId(roomDto.getRoomId());
		room.setRoom(roomDto.getName());
		room.setNumberOfSeat(roomDto.getNumberOfSeat());
		return room;
	}
	
	public static RoomDto toDto(Room room) {
		if( room == null)
			return null;
		RoomDto roomDto = new RoomDto();
		roomDto.setRoomId(room.getRoomId());
		roomDto.setName(room.getName());
		roomDto.setNumberOfSeat(room.getNumberOfSeat());
		return roomDto;
	}
    
}
