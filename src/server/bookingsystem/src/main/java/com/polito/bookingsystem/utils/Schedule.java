package com.polito.bookingsystem.utils;

import com.polito.bookingsystem.entity.Course;
import com.polito.bookingsystem.entity.Room;

public class Schedule {
	Integer Id;
	String Day;
	Integer Duration;
	String timeStart;
	Room room;
	Course course;
	
	public Schedule() {
		
	}

	public Schedule(Integer id, String day, Integer duration, String timeStart, Room room, Course course) {
		Id = id;
		Day = day;
		Duration = duration;
		this.timeStart = timeStart;
		this.room = room;
		this.course = course;
	}

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public String getDay() {
		return Day;
	}

	public void setDay(String day) {
		Day = day;
	}

	public Integer getDuration() {
		return Duration;
	}

	public void setDuration(Integer duration) {
		Duration = duration;
	}

	public String getTimeStart() {
		return timeStart;
	}

	public void setTimeStart(String timeStart) {
		this.timeStart = timeStart;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}    
}