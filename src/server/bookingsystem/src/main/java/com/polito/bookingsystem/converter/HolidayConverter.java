package com.polito.bookingsystem.converter;

import com.polito.bookingsystem.entity.Holiday;
import com.polito.bookingsystem.dto.HolidayDto;

public class HolidayConverter {

	public static Holiday toEntity(HolidayDto holidayDto) {
		if(holidayDto == null)
			return null;
		Holiday holiday = new Holiday(holidayDto.getHolidayId(), holidayDto.getDate());
		return holiday;
	}
	
	public static HolidayDto toDto(Holiday holiday) {
		if( holiday == null)
			return null;
		HolidayDto holidayDto = new HolidayDto(holiday.getHolidayId(), holiday.getDate());
		return holidayDto;
	}
	
	
}
