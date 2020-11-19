package com.polito.bookingsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import org.springframework.stereotype.Repository;
import com.polito.bookingsystem.entity.Booking;
import com.polito.bookingsystem.entity.Lecture;
import com.polito.bookingsystem.utils.BookingEntry;

@Repository
public interface BookingRepository extends JpaRepository<Booking,Integer>{

	Booking findByBookingId(Integer bookingId);
	List<Booking> findByLecture(Lecture lecture);
	
	
	@Query("SELECT B.bookingId as bookingId, B.bookingInfo as bookingInfo, S.name as studentName, S.surname as studentSurname, S.email as studentEmail, L.lectureId as lectureId, L.date as lectureDate, L.numberOfLesson as lectureNumber, L.course as course " +
			"FROM Booking B JOIN B.lecture L JOIN L.professor P JOIN B.student S WHERE P.email = ?1")
	public List<BookingEntry> findByProfessor(String email);

}
