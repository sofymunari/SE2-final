package com.polito.bookingsystem.repository;

import java.util.Date;

import com.polito.bookingsystem.entity.Course;
import com.polito.bookingsystem.entity.Student;

public interface BookingEntry {
	int getBookingId();
	String getBookingInfo();
	String getStudentName();
	String getStudentSurname();
	String getStudentEmail();
	int getLectureId();
	Date getLectureDate();
	int getLectureNumber();
	Course getCourse();
}
