package com.polito.bookingsystem.utils;
import java.util.Date;
import com.polito.bookingsystem.entity.Course;

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
