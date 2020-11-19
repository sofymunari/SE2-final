package com.polito.bookingsystem.service.impl;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.polito.bookingsystem.converter.BookingConverter;
import com.polito.bookingsystem.converter.ProfessorConverter;
import com.polito.bookingsystem.converter.StudentConverter;
import com.polito.bookingsystem.dto.BookingDto;
import com.polito.bookingsystem.entity.Booking;
import com.polito.bookingsystem.entity.Lecture;
import com.polito.bookingsystem.entity.Professor;
import com.polito.bookingsystem.entity.Student;
import com.polito.bookingsystem.repository.BookingEntry;
import com.polito.bookingsystem.repository.BookingRepository;
import com.polito.bookingsystem.repository.LectureRepository;
import com.polito.bookingsystem.repository.StudentRepository;
import com.polito.bookingsystem.service.BookingService;
import com.polito.bookingsystem.service.NotificationProfessorService;
import com.polito.bookingsystem.service.StudentService;
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
	private NotificationProfessorService notificationProfessorService;
	
	
	@Autowired
	public BookingServiceImpl(BookingRepository bookingRepository, LectureRepository lectureRepository, StudentRepository studentRepository)
	{
		this.bookingRepository = bookingRepository;
		this.studentRepository = studentRepository;
		this.lectureRepository = lectureRepository;
	}
	
	@Override
	public List<BookingDto> getListBooking(String email){
		if(email == null) {
			List<BookingDto> bookingsDto = new ArrayList<BookingDto>();
			return bookingsDto;
			}
		List<Booking> bookings = bookingRepository.findAll().stream()
				                                 .filter(b -> b.getStudent().getEmail().compareTo(email) == 0 && b.getBookingInfo()!= BookingInfo.CANCELED_BY_STUD && b.getBookingInfo()!= BookingInfo.CANCELED_BY_PROF)
				                                 .collect(Collectors.toList());
		List<BookingDto> bookingsDto = new ArrayList<BookingDto>();
		for(Booking booking : bookings ) {
			bookingsDto.add(BookingConverter.toDto(booking));
		}
		return bookingsDto;
	}

	@Override
	public BookingDto addBooking(Integer lectureId, String email) {
		Lecture lecture = lectureRepository.findByLectureId(lectureId);
		if(lecture == null)
			return null;
		
		if(lecture.getRemotly())
			return null;
		
		Student student = studentRepository.findByEmail(email);
		if(student == null)
			return null;
		
		Integer numSeatAvaiable = lecture.getRoom().getNumberOfSeat();
		List<Booking> listBookingLectureAttended = bookingRepository.findAll().stream()
				.filter(b->b.getLecture().getLectureId()== lectureId && b.getBookingInfo() == BookingInfo.ATTENDED)
				.collect(Collectors.toList());
		
		Integer numBookingLecture = listBookingLectureAttended.size();
		Integer id = bookingRepository.findAll().stream()
				.mapToInt(b->b.getBookingId())
				.max()
				.orElse(0);
		
		Booking booking = new Booking();
		booking.setBookingId((id+1));
		booking.setLecture(lecture);
		booking.setStudent(student);
		if(numBookingLecture < numSeatAvaiable) {
			booking.setBookingInfo(BookingInfo.ATTENDED);
		}
		else {
			booking.setBookingInfo(BookingInfo.WAITING);
		}
		bookingRepository.save(booking);
		String text = "Dear "+student.getName()+" your booking for lecture "+lecture.getCourse().getName()+" has been confirmed";
		studentService.sendEmail(StudentConverter.toDto(student), "booking confirmation", text);
		
		Professor professor = lecture.getProfessor();
		String description = "You now have "+ (bookingRepository.findByLecture(lecture)).size() + " student(s) for lecture "+lecture.getCourse().getName();
		notificationProfessorService.sendProfessorNotification(ProfessorConverter.toDto(professor), description, "");
		
		return BookingConverter.toDto(booking);		             
	}

	@Override
	public Boolean deleteBooking(Integer bookingId) {
		Booking booking= bookingRepository.findByBookingId(bookingId);
		if(booking == null) {
			return false;
		}
		booking.setBookingInfo(BookingInfo.CANCELED_BY_STUD);
		bookingRepository.save(booking);
		return true;
	}
	
	@Override
	public List<BookingEntry> getBooking(String email) {
		return bookingRepository.findByProfessor(email);
	}

}
