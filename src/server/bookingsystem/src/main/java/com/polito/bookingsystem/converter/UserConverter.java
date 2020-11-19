package com.polito.bookingsystem.converter;

import com.polito.bookingsystem.dto.UserDto;
import com.polito.bookingsystem.entity.User;

public class UserConverter {
	
	public static User toEntity(UserDto userDto) {
		if( userDto == null)
			return null;
		User user = new User();
		user.setUserId(userDto.getUserId());
		user.setAddress(userDto.getAddress());
		user.setEmail(userDto.getEmail());
		user.setName(userDto.getName());
		user.setPassword(userDto.getPassword());
		user.setSurname(userDto.getSurname());
		return user;
	}
	
    public static UserDto toDto(User user) {
		if( user == null)
			return null;
    	UserDto userDto = new UserDto();
    	userDto.setUserId(user.getUserId());
    	userDto.setAddress(user.getAddress());
    	userDto.setEmail(user.getEmail());
    	userDto.setName(user.getName());
    	userDto.setPassword(user.getPassword());
    	userDto.setSurname(user.getSurname());
		return userDto;
	}
	
	
}
