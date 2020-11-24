package com.polito.bookingsystem.dto;
import java.util.Date;

public class HolidayDto {
	private Integer holidayId;
	private Date date;
	public HolidayDto() {}
	public HolidayDto(Integer holidayId, Date date) {
		super();
		this.holidayId = holidayId;
		this.date = date;
	}
	public Integer getHolidayId() {
		return holidayId;
	}
	public void setHolidayId(Integer holidayId) {
		this.holidayId = holidayId;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}	
}
