package com.polito.bookingsystem.service.impl;
import java.util.ArrayList;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.polito.bookingsystem.converter.BookingConverter;
import com.polito.bookingsystem.converter.StudentConverter;
import com.polito.bookingsystem.converter.LectureConverter;
import com.polito.bookingsystem.dto.BookingDto;
import com.polito.bookingsystem.dto.LectureDto;
import com.polito.bookingsystem.entity.Booking;
import com.polito.bookingsystem.entity.Lecture;
import com.polito.bookingsystem.entity.Student;
import com.polito.bookingsystem.repository.BookingRepository;
import com.polito.bookingsystem.repository.LectureRepository;
import com.polito.bookingsystem.repository.StudentRepository;
import com.polito.bookingsystem.service.BookingService;
import com.polito.bookingsystem.service.NotificationProfessorService;
import com.polito.bookingsystem.service.StudentService;
import com.polito.bookingsystem.utils.BookingEntry;
import com.polito.bookingsystem.utils.BookingInfo;

@Service
public class BookingServiceImpl implements BookingService{

	@Autowired
	private BookingRepository bookingRepository;
	
	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private LectureRepository lectureRepository;
	
	@Autowired
	private StudentService studentService;
	
	@Autowired
	public BookingServiceImpl(BookingRepository bookingRepository, LectureRepository lectureRepository, StudentRepository studentRepository, StudentService studentService, NotificationProfessorService notificationProfessorService)
	{
		this.bookingRepository = bookingRepository;
		this.studentRepository = studentRepository;
		this.lectureRepository = lectureRepository;
		this.studentService = studentService;
	}
	
	@Override
	public List<BookingDto> getListBooking(String email){
		if(email == null) {
			return new ArrayList<>();
			
		}
		List<Booking> bookings = bookingRepository.findAll().stream()
				                                 .filter(b -> b.getStudent().getEmail().compareTo(email) == 0 && b.getBookingInfo()!= BookingInfo.CANCELED_BY_STUD && b.getBookingInfo()!= BookingInfo.CANCELED_BY_PROF)
				                                 .collect(Collectors.toList());
		List<BookingDto> bookingsDto = new ArrayList<>();
		for(Booking booking : bookings ) {
			bookingsDto.add(BookingConverter.toDto(booking));
		}
		return bookingsDto;
	}

	@Override
	public BookingDto addBooking(Integer lectureId, String email) {
		
		if(email == null || lectureId == null || lectureId < 0)
			return null;
		
		Lecture lecture = lectureRepository.findByLectureId(lectureId);
		if(lecture == null)
			return null;
		
		if(Boolean.TRUE.equals(lecture.getRemotly()))
			return null;
		
		Student student = studentRepository.findByEmail(email);
		if(student == null)
			return null;
		
		Integer numSeatAvaiable = lecture.getRoom().getNumberOfSeat();
		
		List<Booking> listBookingLectureAttended = bookingRepository.findAll().stream()
				.filter(b->b.getLecture().getLectureId().equals(lectureId) && b.getBookingInfo() == BookingInfo.BOOKED)
				.collect(Collectors.toList());
		
		Integer numBookingLecture = listBookingLectureAttended.size();
		
		Integer id = bookingRepository.findAll().stream()
				.mapToInt(Booking::getBookingId)
				.max()
				.orElse(0);
		
		String text = "";
		Booking booking = new Booking();
		booking.setBookingId((id+1));
		booking.setStudent(student);
		System.out.print(numBookingLecture + " " + numSeatAvaiable);
		if(numBookingLecture < numSeatAvaiable) {
			lecture.setBookedSeats(numBookingLecture + 1);
			lecture.setBookedSeats(numBookingLecture + 1);
			booking.setBookingInfo(BookingInfo.BOOKED);
			text = "Dear "+student.getName()+" "+student.getSurname()+",\n Your booking for lecture "+lecture.getCourse().getName()+" has been confirmed.\n\nBest Regards,\nPolito";
			studentService.sendEmail(StudentConverter.toDto(student), "Booking Confirmation", text);
		}
		else {
			lecture.setBookedSeats(numSeatAvaiable);
			booking.setBookingInfo(BookingInfo.WAITING);
			text = "Dear "+student.getName()+" "+student.getSurname()+",\n Your booking for lecture "+lecture.getCourse().getName()+" has been in waiting list. You recieve email if some seats will free.\n\nBest Regards,\nPolito";
			studentService.sendEmail(StudentConverter.toDto(student), "Waiting confermation", text);
		}
		booking.setLecture(lecture);
		lectureRepository.save(lecture);
		bookingRepository.save(booking);
		return BookingConverter.toDto(booking);		             
	}

	@Override
	public Boolean deleteBooking(Integer bookingId) {
		Booking booking= bookingRepository.findByBookingId(bookingId);
		if(booking == null) {
			return false;
		}
		booking.setBookingInfo(BookingInfo.CANCELED_BY_STUD);
		
		Lecture lecture = booking.getLecture();
		List<Booking> listBookingLectureWaiting = bookingRepository.findAll().stream()
		.filter(b->b.getLecture().getLectureId().equals(lecture.getLectureId()) && b.getBookingInfo() == BookingInfo.WAITING)
		.collect(Collectors.toList());
		
		if(listBookingLectureWaiting.size() > 0) {
			Booking bookingWaiting = listBookingLectureWaiting.get(0);
			bookingWaiting.setBookingInfo(BookingInfo.BOOKED);
			bookingRepository.save(bookingWaiting);
			Student student = bookingWaiting.getStudent();
			String text = "Dear "+student.getName()+" "+student.getSurname()+",\n Your booking for lecture "+lecture.getCourse().getName()+" is confirmed. Now you are removed to waiting list and add in booking list.\n\nBest Regards,\nPolito";
			studentService.sendEmail(StudentConverter.toDto(student), "Booking confermation (Removed to waiting list)", text);
		}else {
			lecture.setBookedSeats(lecture.getBookedSeats() - 1);
			booking.setLecture(lecture);
			lectureRepository.save(lecture);
		}
		bookingRepository.save(booking);
		return true;
	}
	
	@Override
	public List<BookingEntry> getBooking(String email) {
		return bookingRepository.findByProfessor(email);
	}

	@Override
	public List<BookingDto> getListAllBookings() {
        List<Booking> allBookings = bookingRepository.findAll();
        List<BookingDto> allBookingsDto = new ArrayList<>();
        for(Booking booking:allBookings) {
        	allBookingsDto.add(BookingConverter.toDto(booking));
        }
		return allBookingsDto;
	}
	
	@Override
	public List<BookingDto> getBookingsByLecture(LectureDto lectureDto) {
		if(lectureDto == null)
			return new ArrayList<>();
		Lecture lecture = LectureConverter.toEntity(lectureDto);
		return BookingConverter.toDto(bookingRepository.findByLecture(lecture));
	}

	@Override
	public void save(BookingDto bookingDto) {
		if(bookingDto != null)
			bookingRepository.save(BookingConverter.toEntity(bookingDto));
	}

}
