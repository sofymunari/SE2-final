package com.polito.bookingsystem.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Holiday {
	@Column
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	private Integer holidayId;
	
	@Column
	private Date date;

	public Holiday() {}
	
	public Holiday(Integer holidayId, Date date) {
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
