package com.polito.bookingsystem.converter;
import com.polito.bookingsystem.dto.RoomDto;
import com.polito.bookingsystem.entity.Room;

public class RoomConverter {
	private RoomConverter() {
		
	}
	public static Room toEntity(RoomDto roomDto) {
		if( roomDto == null)
			return null;
		Room room = new Room();
		room.setRoomId(roomDto.getRoomId());
		room.setNumberOfSeat(roomDto.getNumberOfSeat());
		room.setName(roomDto.getName());
		return room;
	}
	
	public static RoomDto toDto(Room room) {
		if( room == null)
			return null;
		RoomDto roomDto = new RoomDto();
		roomDto.setRoomId(room.getRoomId());
		roomDto.setNumberOfSeat(room.getNumberOfSeat());
		roomDto.setName(room.getName());
		return roomDto;
	}
    
}
