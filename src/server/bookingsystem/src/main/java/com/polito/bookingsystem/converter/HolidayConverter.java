package com.polito.bookingsystem.converter;

import com.polito.bookingsystem.entity.Holiday;
import com.polito.bookingsystem.dto.HolidayDto;

public class HolidayConverter {

	public static Holiday toEntity(HolidayDto holidayDto) {
		if(holidayDto == null)
			return null;
		Holiday holiday = new Holiday();
		holiday.setHolidayId(holidayDto.getHolidayId());
		holiday.setDate(holidayDto.getDate());
		return holiday;
	}
	
	public static HolidayDto toDto(Holiday holiday) {
		if( holiday == null)
			return null;
		HolidayDto holidayDto = new HolidayDto();
		holidayDto.setHolidayId(holiday.getHolidayId());
		holidayDto.setDate(holiday.getDate());
		return holidayDto;
	}
	
	
}
