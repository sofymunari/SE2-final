package com.polito.bookingsystem.utils.serviceTests;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.junit4.SpringRunner;

import com.polito.bookingsystem.dto.BookingDto;
import com.polito.bookingsystem.dto.CourseDto;
import com.polito.bookingsystem.dto.LectureDto;
import com.polito.bookingsystem.dto.ProfessorDto;
import com.polito.bookingsystem.dto.RoomDto;
import com.polito.bookingsystem.dto.StudentDto;
import com.polito.bookingsystem.entity.Booking;
import com.polito.bookingsystem.entity.Course;
import com.polito.bookingsystem.entity.Lecture;
import com.polito.bookingsystem.entity.Professor;
import com.polito.bookingsystem.entity.Room;
import com.polito.bookingsystem.entity.Student;
import com.polito.bookingsystem.repository.BookingRepository;
import com.polito.bookingsystem.repository.CourseRepository;
import com.polito.bookingsystem.repository.LectureRepository;
import com.polito.bookingsystem.repository.ProfessorRepository;
import com.polito.bookingsystem.repository.StudentRepository;
import com.polito.bookingsystem.service.NotificationProfessorService;
import com.polito.bookingsystem.service.StudentService;
import com.polito.bookingsystem.service.impl.BookingServiceImpl;
import com.polito.bookingsystem.service.impl.LectureServiceImpl;
import com.polito.bookingsystem.service.impl.StudentServiceImpl;
import com.polito.bookingsystem.utils.BookingEntry;
import com.polito.bookingsystem.utils.BookingInfo;

@RunWith(SpringRunner.class)
@DataJpaTest
class BookingServiceTest {
	
	@Autowired
	private LectureRepository lectureRepository;
	@Autowired
	private StudentRepository studentRepository;
	@Autowired
	private BookingRepository bookingRepository;
	@Autowired
	private CourseRepository courseRepository;
	@Autowired
	private ProfessorRepository professorRepository;
	
	private  JavaMailSender javaMailSender;
	
	private StudentService studentService;
	private LectureServiceImpl lectureServiceImpl;
	private StudentServiceImpl studentServiceImpl;
	private BookingServiceImpl bookingServiceImpl;
	private NotificationProfessorService notificationProfessorService;
	
	@BeforeEach
	public void setUp() throws Exception {
		
		studentService = mock(StudentService.class);
		bookingRepository = mock(BookingRepository.class);
		studentRepository = mock(StudentRepository.class);
		courseRepository = mock(CourseRepository.class);
		lectureRepository = mock(LectureRepository.class);
		studentService = mock(StudentService.class);
		notificationProfessorService = mock(NotificationProfessorService.class);
		studentServiceImpl = new StudentServiceImpl(studentRepository, courseRepository, javaMailSender);
		lectureServiceImpl = new LectureServiceImpl(lectureRepository, studentRepository, bookingRepository, studentService, professorRepository);
		bookingServiceImpl = new BookingServiceImpl(bookingRepository, lectureRepository, studentRepository, studentService, notificationProfessorService);
	}
	
	
	@Test
	void testGetListBooking1() throws ParseException {
		//passing an invalid mail 
		
		Room room1 = new Room(1, "testName", 100);
		Date date = new SimpleDateFormat("dd/MM/yyyy").parse("01/01/0101");
		
		Course course1 = new Course(1, "testName1", "A", 1,1);
		Course course2 = new Course(2, "testName2", "B",1,1);
		Course course3 = new Course(3, "testName3", "c",1,1);
		List<Course> courses1 = new ArrayList<>();
		courses1.add(course1);
		courses1.add(course2);

		List<Course> courses2 = new ArrayList<>();
		courses2.add(course1);
		courses2.add(course2);
		courses2.add(course3);
		
		Student student1 = new Student(1, "testName", "testSurname", "testAddress", "test@email.com", "testPassword", date, courses1, "testMatricola");
		Professor professor1 = new Professor(1, "testName", "testSurname", "testAddress", "testProfessor@email.com", "testPassword",courses2,"d0");

		Lecture lecture1 = new Lecture(1, 10, course1, professor1, true, date, 90, "testDetails", room1);
		Lecture lecture2 = new Lecture(2, 10, course2, professor1, true, date, 90, "testDetails", room1);
		Lecture lecture3 = new Lecture(3, 10, course3, professor1, true, date, 90, "testDetails", room1);

		List<Lecture> lectures = new ArrayList<>();
		lectures.add(lecture1);
		lectures.add(lecture2);
		lectures.add(lecture3);

		BookingInfo bookingInfo = BookingInfo.WAITING;
		
		Booking booking1 = new Booking(1, student1, lecture1, bookingInfo);
		Booking booking2 = new Booking(2, student1, lecture2, bookingInfo);
		Booking booking3 = new Booking(3, student1, lecture3, bookingInfo);

		List<Booking> bookings = new ArrayList<>();
		bookings.add(booking1);
		bookings.add(booking2);
		bookings.add(booking3);
		
		when(bookingRepository.findAll()).thenReturn(bookings);

		assertTrue("Expected an empty list to be returned", bookingServiceImpl.getListBooking("wrong@email.com").isEmpty());
	}
	
	@Test
	void testGetListBooking2() throws ParseException {
		//passing a null mail 
		
		Room room1 = new Room(1, "testName", 100);
		Date date = new SimpleDateFormat("dd/MM/yyyy").parse("01/01/0101");
		
		Course course1 = new Course(1, "testName1", "A",1,1);
		Course course2 = new Course(2, "testName2", "B",1,1);
		Course course3 = new Course(3, "testName3", "c",1,1);
		List<Course> courses1 = new ArrayList<>();
		courses1.add(course1);
		courses1.add(course2);

		List<Course> courses2 = new ArrayList<>();
		courses2.add(course1);
		courses2.add(course2);
		courses2.add(course3);
		
		Student student1 = new Student(1, "testName", "testSurname", "testAddress", "test@email.com", "testPassword", date, courses1, "testMatricola");
		Professor professor1 = new Professor(1, "testName", "testSurname", "testAddress", "testProfessor@email.com", "testPassword",courses2, "d0");

		Lecture lecture1 = new Lecture(1, 10, course1, professor1, true, date, 90, "testDetails", room1);
		Lecture lecture2 = new Lecture(2, 10, course2, professor1, true, date, 90, "testDetails", room1);
		Lecture lecture3 = new Lecture(3, 10, course3, professor1, true, date, 90, "testDetails", room1);

		List<Lecture> lectures = new ArrayList<>();
		lectures.add(lecture1);
		lectures.add(lecture2);
		lectures.add(lecture3);

		BookingInfo bookingInfo = BookingInfo.WAITING;
		
		Booking booking1 = new Booking(1, student1, lecture1, bookingInfo);
		Booking booking2 = new Booking(2, student1, lecture2, bookingInfo);
		Booking booking3 = new Booking(3, student1, lecture3, bookingInfo);

		List<Booking> bookings = new ArrayList<>();
		bookings.add(booking1);
		bookings.add(booking2);
		bookings.add(booking3);
		
		when(bookingRepository.findAll()).thenReturn(bookings);
		assertTrue("Expected an empty list to be returned", bookingServiceImpl.getListBooking(null).isEmpty());
	
	}
	
	@Test
	void testGetListBooking3() throws ParseException {
		//adding 3 bookings: 3 waiting
		
		Room room1 = new Room(1, "testName", 100);
		Date date = new SimpleDateFormat("dd/MM/yyyy").parse("01/01/0101");
		
		Course course1 = new Course(1, "testName1", "A", 1,1 );
		Course course2 = new Course(2, "testName2", "B",1,1);
		Course course3 = new Course(3, "testName3", "C",1,1);
		List<Course> courses1 = new ArrayList<>();
		courses1.add(course1);
		courses1.add(course2);

		List<Course> courses2 = new ArrayList<>();
		courses2.add(course1);
		courses2.add(course2);
		courses2.add(course3);
		
		Student student1 = new Student(1, "testName", "testSurname", "testAddress", "test@email.com", "testPassword", date, courses1, "testMatricola");
		Professor professor1 = new Professor(1, "testName", "testSurname", "testAddress", "testProfessor@email.com", "testPassword",courses2,"d0");

		Lecture lecture1 = new Lecture(1, 10, course1, professor1, true, date, 90, "testDetails", room1);
		Lecture lecture2 = new Lecture(2, 10, course2, professor1, true, date, 90, "testDetails", room1);
		Lecture lecture3 = new Lecture(3, 10, course3, professor1, true, date, 90, "testDetails", room1);

		List<Lecture> lectures = new ArrayList<>();
		lectures.add(lecture1);
		lectures.add(lecture2);
		lectures.add(lecture3);

		BookingInfo bookingInfo = BookingInfo.WAITING;
		
		Booking booking1 = new Booking(1, student1, lecture1, bookingInfo);
		Booking booking2 = new Booking(2, student1, lecture2, bookingInfo);
		Booking booking3 = new Booking(3, student1, lecture3, bookingInfo);

		List<Booking> bookings = new ArrayList<>();
		bookings.add(booking1);
		bookings.add(booking2);
		bookings.add(booking3);
		
		when(bookingRepository.findAll()).thenReturn(bookings);
		
		assertTrue("The number of lecture is wrong", bookingServiceImpl.getListBooking("test@email.com").size() == 3);

	}
	
	@Test
	void testGetListBooking4() throws ParseException {
		//adding 3 bookings: 2 waiting, 1 deleted by prof
		
		Room room1 = new Room(1, "testName", 100);
		Date date = new SimpleDateFormat("dd/MM/yyyy").parse("01/01/0101");
		
		Course course1 = new Course(1, "testName1", "A",1,1);
		Course course2 = new Course(2, "testName2", "B",1,1);
		Course course3 = new Course(3, "testName3", "C",1,1);
		List<Course> courses1 = new ArrayList<>();
		courses1.add(course1);
		courses1.add(course2);

		List<Course> courses2 = new ArrayList<>();
		courses2.add(course1);
		courses2.add(course2);
		courses2.add(course3);
		
		Student student1 = new Student(1, "testName", "testSurname", "testAddress", "test@email.com", "testPassword", date, courses1, "testMatricola");
		Professor professor1 = new Professor(1, "testName", "testSurname", "testAddress", "testProfessor@email.com", "testPassword",courses2,"d0");

		Lecture lecture1 = new Lecture(1, 10, course1, professor1, true, date, 90, "testDetails", room1);
		Lecture lecture2 = new Lecture(2, 10, course2, professor1, true, date, 90, "testDetails", room1);
		Lecture lecture3 = new Lecture(3, 10, course3, professor1, true, date, 90, "testDetails", room1);

		List<Lecture> lectures = new ArrayList<>();
		lectures.add(lecture1);
		lectures.add(lecture2);
		lectures.add(lecture3);

		BookingInfo bookingWaiting = BookingInfo.WAITING;
		BookingInfo bookingCancelled = BookingInfo.CANCELED_BY_PROF;

		
		Booking booking1 = new Booking(1, student1, lecture1, bookingWaiting);
		Booking booking2 = new Booking(2, student1, lecture2, bookingCancelled);
		Booking booking3 = new Booking(3, student1, lecture3, bookingWaiting);

		List<Booking> bookings = new ArrayList<>();
		bookings.add(booking1);
		bookings.add(booking2);
		bookings.add(booking3);
		
		when(bookingRepository.findAll()).thenReturn(bookings);
		
		assertTrue("The number of lecture is wrong", bookingServiceImpl.getListBooking("test@email.com").size() == 2);

	}
	
	@Test
	void testGetListBooking5() throws ParseException {
		//adding 3 bookings: 2 waiting, 1 deleted by student
		
		Room room1 = new Room(1, "testName", 100);
		Date date = new SimpleDateFormat("dd/MM/yyyy").parse("01/01/0101");
		
		Course course1 = new Course(1, "testName1", "A",1,1);
		Course course2 = new Course(2, "testName2", "B",1,1);
		Course course3 = new Course(3, "testName3", "C",1,1);
		List<Course> courses1 = new ArrayList<>();
		courses1.add(course1);
		courses1.add(course2);

		List<Course> courses2 = new ArrayList<>();
		courses2.add(course1);
		courses2.add(course2);
		courses2.add(course3);
		
		Student student1 = new Student(1, "testName", "testSurname", "testAddress", "test@email.com", "testPassword", date, courses1, "testMatricola");
		Professor professor1 = new Professor(1, "testName", "testSurname", "testAddress", "testProfessor@email.com", "testPassword",courses2,"d0");

		Lecture lecture1 = new Lecture(1, 10, course1, professor1, true, date, 90, "testDetails", room1);
		Lecture lecture2 = new Lecture(2, 10, course2, professor1, true, date, 90, "testDetails", room1);
		Lecture lecture3 = new Lecture(3, 10, course3, professor1, true, date, 90, "testDetails", room1);

		List<Lecture> lectures = new ArrayList<>();
		lectures.add(lecture1);
		lectures.add(lecture2);
		lectures.add(lecture3);

		BookingInfo bookingWaiting = BookingInfo.WAITING;
		BookingInfo bookingCancelled = BookingInfo.CANCELED_BY_STUD;

		
		Booking booking1 = new Booking(1, student1, lecture1, bookingWaiting);
		Booking booking2 = new Booking(2, student1, lecture2, bookingCancelled);
		Booking booking3 = new Booking(3, student1, lecture3, bookingWaiting);

		List<Booking> bookings = new ArrayList<>();
		bookings.add(booking1);
		bookings.add(booking2);
		bookings.add(booking3);
		
		when(bookingRepository.findAll()).thenReturn(bookings);
		
		assertTrue("The number of lecture is wrong", bookingServiceImpl.getListBooking("test@email.com").size() == 2);

	}
	
	@Test
	void testDeleteBooking1() {
		//passing an invalid id	
		when(bookingRepository.findByBookingId(anyInt())).thenReturn(null);
		when(bookingRepository.save(anyObject())).thenReturn(null);

		assertFalse(bookingServiceImpl.deleteBooking(-1), "Expected a null value returned as booking");
	}
	
	@Test
	void testDeleteBooking2()  {
		//passing a null id 	
		
		when(bookingRepository.findByBookingId(anyInt())).thenReturn(null);
		when(bookingRepository.save(anyObject())).thenReturn(null);
		
		assertFalse(bookingServiceImpl.deleteBooking(null), "Expected false");
	}
	
	@Test
	void testDeleteBooking3() throws ParseException {
		//passing a valid id	
		
		Room room1 = new Room(1, "testName", 100);
		Date date = new SimpleDateFormat("dd/MM/yyyy").parse("01/01/0101");
		
		Course course1 = new Course(1, "testName1", "A",1,1);
		List<Course> courses1 = new ArrayList<>();
		courses1.add(course1);
		
		Student student1 = new Student(1, "testName", "testSurname", "testAddress", "test@email.com", "testPassword", date, courses1, "testMatricola");
		Professor professor1 = new Professor(1, "testName", "testSurname", "testAddress", "testProfessor@email.com", "testPassword",courses1,"d0");
		Lecture lecture1 = new Lecture(1, 10, course1, professor1, true, date, 90, "testDetails", room1);
		BookingInfo bookingInfo = BookingInfo.WAITING;
		Booking booking1 = new Booking(1, student1, lecture1, bookingInfo);
		
		when(bookingRepository.findByBookingId(anyInt())).thenReturn(booking1);
		when(bookingRepository.save(anyObject())).thenReturn(null);

		assertTrue( "Expected true", bookingServiceImpl.deleteBooking(null));
	}
	
	
	@Test
	void testAddBooking1(){
		//passing an invalid id 		
		when(lectureRepository.findByLectureId(anyInt())).thenReturn(null);

		assertNull("Expected a null value to be returned", bookingServiceImpl.addBooking(-1, "test@email.com"));
	}
	
	@Test
	void testAddBooking2(){
		//passing a null id 		
		
		when(lectureRepository.findByLectureId(anyInt())).thenReturn(null);

		assertNull("Expected a null value to be returned", bookingServiceImpl.addBooking(null, "test@email.com"));
	}
	
	@Test
	void testAddBooking3() throws ParseException {
		//passing an invalid lecture (remotly == true) 		
		
		Room room1 = new Room(1, "testName", 100);
		Date date = new SimpleDateFormat("dd/MM/yyyy").parse("01/01/0101");
		
		Course course1 = new Course(1, "testName1", "A",1,1);
		List<Course> courses1 = new ArrayList<>();
		courses1.add(course1);
		
		Professor professor1 = new Professor(1, "testName", "testSurname", "testAddress", "testProfessor@email.com", "testPassword",courses1,"d0");
		Lecture lecture1 = new Lecture(1, 10, course1, professor1, true, date, 90, "testDetails", room1);
		
		when(lectureRepository.findByLectureId(anyInt())).thenReturn(lecture1);

		assertNull( "Expected a null value to be returned", bookingServiceImpl.addBooking(1, "test@email.com"));
	}
	
	@Test
	void testAddBooking4() throws ParseException {
		//passing an invalid email 		
		
		Room room1 = new Room(1, "testName", 100);
		Date date = new SimpleDateFormat("dd/MM/yyyy").parse("01/01/0101");
		
		Course course1 = new Course(1, "testName1", "A",1,1);
		List<Course> courses1 = new ArrayList<>();
		courses1.add(course1);
		
		Professor professor1 = new Professor(1, "testName", "testSurname", "testAddress", "testProfessor@email.com", "testPassword",courses1,"d0");
		Lecture lecture1 = new Lecture(1, 10, course1, professor1, false, date, 90, "testDetails", room1);
		
		when(lectureRepository.findByLectureId(anyInt())).thenReturn(lecture1);
		when(studentRepository.findByEmail(anyString())).thenReturn(null);
		
		assertNull("Expected a null value returned as booking", bookingServiceImpl.addBooking(1, "wrong@email.com"));
	}
	
	@Test
	void testAddBooking5() throws ParseException {
		//passing a null email 		
		
		Room room1 = new Room(1, "testName", 100);
		Date date = new SimpleDateFormat("dd/MM/yyyy").parse("01/01/0101");
		
		Course course1 = new Course(1, "testName1", "A",1,1);
		List<Course> courses1 = new ArrayList<>();
		courses1.add(course1);
		
		Professor professor1 = new Professor(1, "testName", "testSurname", "testAddress", "testProfessor@email.com", "testPassword",courses1,"d0");
		Lecture lecture1 = new Lecture(1, 10, course1, professor1, false, date, 90, "testDetails", room1);
		
		when(lectureRepository.findByLectureId(anyInt())).thenReturn(lecture1);
		when(studentRepository.findByEmail(anyString())).thenReturn(null);
		
		assertNull( "Expected a null value to be returned as booking", bookingServiceImpl.addBooking(1, null));
	}

	
	@Test
	void testAddBooking6() throws ParseException {
		//passing a valid id and email, result should be waiting (room number of seats =3 )
		
		Room room1 = new Room(1, "testName", 3);
		Date date = new SimpleDateFormat("dd/MM/yyyy").parse("01/01/0101");
		
		Course course1 = new Course(1, "testName1", "A",1,1);
		List<Course> courses1 = new ArrayList<>();
		courses1.add(course1);
		
		Student student1 = new Student(1, "testName1", "testSurname1", "testAddress1", "test1@email.com", "testPassword1", date, courses1, "testMatricola1");
		Student student2 = new Student(2, "testName2", "testSurname2", "testAddress2", "test2@email.com", "testPassword2", date, courses1, "testMatricola2");
		Student student3 = new Student(3, "testName3", "testSurname3", "testAddress3", "test3@email.com", "testPassword3", date, courses1, "testMatricola3");
		Student student4 = new Student(4, "testName4", "testSurname4", "testAddress4", "test4@email.com", "testPassword4", date, courses1, "testMatricola4");
		Professor professor1 = new Professor(1, "testName", "testSurname", "testAddress", "testProfessor@email.com", "testPassword",courses1,"d0");
		Lecture lecture1 = new Lecture(1, 10, course1, professor1, false, date, 90, "testDetails", room1);
		BookingInfo bookingInfo = BookingInfo.BOOKED;
		Booking booking1 = new Booking(1, student1, lecture1, bookingInfo);
		Booking booking2 = new Booking(2, student2, lecture1, bookingInfo);
		Booking booking3 = new Booking(3, student3, lecture1, bookingInfo);
		List<Booking> bookings = new ArrayList<>();
		bookings.add(booking1);
		bookings.add(booking2);
		bookings.add(booking3);


		when(bookingRepository.findAll()).thenReturn(bookings);
		when(bookingRepository.save(anyObject())).thenReturn(null);
		when(bookingRepository.save(anyObject())).thenReturn(null);
		when(lectureRepository.findByLectureId(anyInt())).thenReturn(lecture1);
		when(studentRepository.findByEmail(anyString())).thenReturn(student4);
		doNothing().when(studentService).sendEmail(anyObject(), anyString(), anyString());
		when(notificationProfessorService.sendProfessorNotification(anyObject(), anyString(), anyString())).thenReturn(true);

		assertTrue( "Expected a booking with booking info equal to Waiting", bookingServiceImpl.addBooking(1, "test4@email.com").getBookingInfo() == BookingInfo.WAITING);
	}


	@Test
	void testAddBooking7() throws ParseException {
		//passing a valid id an email, result should be Attended (room number of seats = 4)
		
		Room room1 = new Room(1, "testName", 4);
		Date date = new SimpleDateFormat("dd/MM/yyyy").parse("01/01/0101");
		
		Course course1 = new Course(1, "testName1", "A",1,1);
		List<Course> courses1 = new ArrayList<>();
		courses1.add(course1);
		
		Student student1 = new Student(1, "testName1", "testSurname1", "testAddress1", "test1@email.com", "testPassword1", date, courses1, "testMatricola1");
		Student student2 = new Student(2, "testName2", "testSurname2", "testAddress2", "test2@email.com", "testPassword2", date, courses1, "testMatricola2");
		Student student3 = new Student(3, "testName3", "testSurname3", "testAddress3", "test3@email.com", "testPassword3", date, courses1, "testMatricola3");
		Student student4 = new Student(4, "testName4", "testSurname4", "testAddress4", "test4@email.com", "testPassword4", date, courses1, "testMatricola4");
		Professor professor1 = new Professor(1, "testName", "testSurname", "testAddress", "testProfessor@email.com", "testPassword",courses1,"d0");
		Lecture lecture1 = new Lecture(1, 10, course1, professor1, false, date, 90, "testDetails1", room1);
		Lecture lecture2 = new Lecture(2, 10, course1, professor1, false, date, 90, "testDetails2", room1);
		BookingInfo bookingInfo = BookingInfo.BOOKED;
		BookingInfo bookingDeleted = BookingInfo.CANCELED_BY_STUD;
		Booking booking1 = new Booking(1, student1, lecture1, bookingInfo);
		Booking booking2 = new Booking(2, student2, lecture1, bookingInfo);
		Booking booking3 = new Booking(3, student3, lecture1, bookingDeleted);
		Booking booking4 = new Booking(4, student3, lecture2, bookingDeleted);

		List<Booking> bookings = new ArrayList<>();
		bookings.add(booking1);
		bookings.add(booking2);
		bookings.add(booking3);
		bookings.add(booking4);

		when(bookingRepository.findAll()).thenReturn(bookings);
		when(bookingRepository.save(anyObject())).thenReturn(null);
		when(lectureRepository.findByLectureId(anyInt())).thenReturn(lecture1);
		when(studentRepository.findByEmail(anyString())).thenReturn(student4);
		doNothing().when(studentService).sendEmail(anyObject(), anyString(), anyString());
		when(notificationProfessorService.sendProfessorNotification(anyObject(), anyString(), anyString())).thenReturn(true);
		
		assertTrue("Expected a booking with booking info equal to Attended", bookingServiceImpl.addBooking(1, "test4@email.com").getBookingInfo() == BookingInfo.BOOKED);
	}
	
	
	@Test
	void testGetBooking1() {
		//passing an invalid id	
		when(bookingRepository.findByProfessor(anyString())).thenReturn(new ArrayList<BookingEntry>());

		assertTrue("Expected an empty list to be returned", bookingServiceImpl.getBooking("").size() == 0);
	}
	
	@Test
	void testGetListAllBookings1() {
		//empty List
		List<Booking> allBookings = new ArrayList<>();
		
		when(bookingRepository.findAll()).thenReturn(allBookings);
		assertTrue("Expected an empty list", bookingServiceImpl.getListAllBookings().isEmpty());
	}
	

	
	@Test
	void testGetListAllBookings2() throws ParseException {
		//list of size 4
		
		Room room1 = new Room(1, "testName", 4);
		Date date = new SimpleDateFormat("dd/MM/yyyy").parse("01/01/0101");
		
		Course course1 = new Course(1, "testName1", "A",1,1);
		List<Course> courses1 = new ArrayList<>();
		courses1.add(course1);
		
		Student student1 = new Student(1, "testName1", "testSurname1", "testAddress1", "test1@email.com", "testPassword1", date, courses1, "testMatricola1");
		Student student2 = new Student(2, "testName2", "testSurname2", "testAddress2", "test2@email.com", "testPassword2", date, courses1, "testMatricola2");
		Student student3 = new Student(3, "testName3", "testSurname3", "testAddress3", "test3@email.com", "testPassword3", date, courses1, "testMatricola3");
		Student student4 = new Student(4, "testName4", "testSurname4", "testAddress4", "test4@email.com", "testPassword4", date, courses1, "testMatricola4");
		Professor professor1 = new Professor(1, "testName", "testSurname", "testAddress", "testProfessor@email.com", "testPassword",courses1,"d0");
		Lecture lecture1 = new Lecture(1, 10, course1, professor1, false, date, 90, "testDetails1", room1);
		Lecture lecture2 = new Lecture(2, 10, course1, professor1, false, date, 90, "testDetails2", room1);
		BookingInfo bookingInfo = BookingInfo.BOOKED;
		BookingInfo bookingDeleted = BookingInfo.CANCELED_BY_STUD;
		Booking booking1 = new Booking(1, student1, lecture1, bookingInfo);
		Booking booking2 = new Booking(2, student2, lecture1, bookingInfo);
		Booking booking3 = new Booking(3, student3, lecture1, bookingDeleted);
		Booking booking4 = new Booking(4, student4, lecture2, bookingDeleted);

		List<Booking> bookings = new ArrayList<>();
		bookings.add(booking1);
		bookings.add(booking2);
		bookings.add(booking3);
		bookings.add(booking4);
		
		when(bookingRepository.findAll()).thenReturn(bookings);
		assertTrue("Expected an empty list", bookingServiceImpl.getListAllBookings().size() == 4);
	}
	
	@Test
	void testGetBookingByLecture1(){
		//null lectureDto passed
		
		assertTrue("Expected an empty list to be returned", bookingServiceImpl.getBookingsByLecture(null).isEmpty());
	}
	
	@Test
	void testGetBookingByLecture2() throws ParseException{
		//expected a list returned of 3 elements
		
		Room room1 = new Room(1, "testName", 4);
		Date date = new SimpleDateFormat("dd/MM/yyyy").parse("01/01/0101");
		
		Course course1 = new Course(1, "testName1", "A",1,1);
		List<Course> courses1 = new ArrayList<>();
		courses1.add(course1);
		
		Student student1 = new Student(1, "testName1", "testSurname1", "testAddress1", "test1@email.com", "testPassword1", date, courses1, "testMatricola1");
		Student student2 = new Student(2, "testName2", "testSurname2", "testAddress2", "test2@email.com", "testPassword2", date, courses1, "testMatricola2");
		Student student3 = new Student(3, "testName3", "testSurname3", "testAddress3", "test3@email.com", "testPassword3", date, courses1, "testMatricola3");
		Professor professor1 = new Professor(1, "testName", "testSurname", "testAddress", "testProfessor@email.com", "testPassword",courses1,"d0");
		Lecture lecture1 = new Lecture(1, 10, course1, professor1, false, date, 90, "testDetails1", room1);
		BookingInfo bookingInfo = BookingInfo.BOOKED;
		BookingInfo bookingDeleted = BookingInfo.CANCELED_BY_STUD;
		Booking booking1 = new Booking(1, student1, lecture1, bookingInfo);
		Booking booking2 = new Booking(2, student2, lecture1, bookingInfo);
		Booking booking3 = new Booking(3, student3, lecture1, bookingDeleted);

		List<Booking> bookings = new ArrayList<>();
		bookings.add(booking1);
		bookings.add(booking2);
		bookings.add(booking3);
		
		CourseDto courseDto1 = new CourseDto(1, "testName1", "A", 1,1 );
		List<CourseDto> coursesDto1 = new ArrayList<>();
		coursesDto1.add(courseDto1);
		ProfessorDto professorDto1 = new ProfessorDto(1, "testName", "testSurname", "testAddress", "testProfessor@email.com", "testPassword",coursesDto1, "d0");
		RoomDto roomDto1 = new RoomDto(1, "testName", 4);

		LectureDto lectureDto1 = new LectureDto(1, 10, courseDto1, professorDto1, false, date, 90, "testDetails1", roomDto1);

		when(bookingRepository.findByLecture(anyObject())).thenReturn(bookings);
		
		assertTrue("Expected a list of 3 elements to be returned, but was " + bookingServiceImpl.getBookingsByLecture(lectureDto1).size(), bookingServiceImpl.getBookingsByLecture(lectureDto1).size() == 3);
	}

	@Test
	void testGetBookingByLecture3() throws ParseException{
		//expected a list returned of 1 element
		
		Room room1 = new Room(1, "testName", 4);
		Date date = new SimpleDateFormat("dd/MM/yyyy").parse("01/01/0101");
		
		Course course1 = new Course(1, "testName1", "A",1,1);
		List<Course> courses1 = new ArrayList<>();
		courses1.add(course1);

		Student student4 = new Student(4, "testName4", "testSurname4", "testAddress4", "test4@email.com", "testPassword4", date, courses1, "testMatricola4");
		Professor professor1 = new Professor(1, "testName", "testSurname", "testAddress", "testProfessor@email.com", "testPassword",courses1,"d0");
		Lecture lecture2 = new Lecture(2, 10, course1, professor1, false, date, 90, "testDetails2", room1);
		BookingInfo bookingDeleted = BookingInfo.CANCELED_BY_STUD;
		Booking booking4 = new Booking(4, student4, lecture2, bookingDeleted);

		List<Booking> bookings = new ArrayList<>();
		bookings.add(booking4);
		
		
		CourseDto courseDto1 = new CourseDto(1, "testName1", "A",1,1);
		List<CourseDto> coursesDto1 = new ArrayList<>();
		coursesDto1.add(courseDto1);
		ProfessorDto professorDto1 = new ProfessorDto(1, "testName", "testSurname", "testAddress", "testProfessor@email.com", "testPassword",coursesDto1,"d0");
		RoomDto roomDto1 = new RoomDto(1, "testName", 4);

		LectureDto lectureDto1 = new LectureDto(2, 10, courseDto1, professorDto1, false, date, 90, "testDetails2", roomDto1);

		when(bookingRepository.findByLecture(anyObject())).thenReturn(bookings);
		assertTrue("Expected a list of 1 element to be returned, but was " + bookingServiceImpl.getBookingsByLecture(lectureDto1).size(), bookingServiceImpl.getBookingsByLecture(lectureDto1).size() == 1);
	}

	
	@Test
	public void testsave1(){
	   try{
			RoomDto roomDto = new RoomDto(1, "testName", 4);
			Date date = new SimpleDateFormat("dd/MM/yyyy").parse("01/01/0101");
			
			CourseDto courseDto = new CourseDto(1, "testName1", "A",1,1);
			List<CourseDto> coursesDto = new ArrayList<>();
			coursesDto.add(courseDto);

			StudentDto studentDto = new StudentDto(4, "testName4", "testSurname4", "testAddress4", "test4@email.com", "testPassword4", date, coursesDto, "testMatricola4");
			ProfessorDto professorDto = new ProfessorDto(1, "testName", "testSurname", "testAddress", "testProfessor@email.com", "testPassword",coursesDto,"d0");
			LectureDto lectureDto = new LectureDto(2, 10, courseDto, professorDto, false, date, 90, "testDetails2", roomDto);
			BookingInfo bookingDeleted = BookingInfo.CANCELED_BY_STUD;
			BookingDto bookingDto = new BookingDto(4, studentDto, lectureDto, bookingDeleted);
		
			when(bookingRepository.save(anyObject())).thenReturn(null);
			bookingServiceImpl.save(bookingDto);
	   }
	   catch(Exception e){
	      fail("Should not have thrown any exception");
	   }
	}
	
	@Test
	public void testsave2(){
	   try{
	      bookingServiceImpl.save(null);
	   }
	   catch(Exception e){
	      fail("Should not have thrown any exception");
	   }
	}
}
