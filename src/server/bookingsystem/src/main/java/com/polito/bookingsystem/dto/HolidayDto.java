package com.polito.bookingsystem.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.polito.bookingsystem.dto.StudentDto;
import com.polito.bookingsystem.dto.LectureDto;
import com.polito.bookingsystem.utils.BookingInfo;

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
