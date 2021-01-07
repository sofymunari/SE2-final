package com.polito.bookingsystem.utils.serviceTests;
import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.junit4.SpringRunner;
import com.polito.bookingsystem.dto.CourseDto;
import com.polito.bookingsystem.dto.ProfessorDto;
import com.polito.bookingsystem.dto.StudentDto;
import com.polito.bookingsystem.dto.RoomDto;
import com.polito.bookingsystem.dto.LectureDto;
import com.polito.bookingsystem.dto.BookingDto;
import com.polito.bookingsystem.entity.Booking;
import com.polito.bookingsystem.entity.Course;
import com.polito.bookingsystem.entity.Lecture;
import com.polito.bookingsystem.entity.Professor;
import com.polito.bookingsystem.entity.Room;
import com.polito.bookingsystem.entity.Student;
import com.polito.bookingsystem.repository.BookingRepository;
import com.polito.bookingsystem.repository.LectureRepository;
import com.polito.bookingsystem.repository.ProfessorRepository;
import com.polito.bookingsystem.service.impl.BookingServiceImpl;
import com.polito.bookingsystem.service.impl.ProfessorServiceImpl;
import com.polito.bookingsystem.service.BookingService;
import com.polito.bookingsystem.utils.BookingInfo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@RunWith(SpringRunner.class)
@DataJpaTest
class ProfessorServiceTests {
	
	@Autowired
	private ProfessorRepository professorRepository;
	
	@Autowired
	private LectureRepository lectureRepository;
	
	@Autowired
	private BookingRepository bookingRepository;
	
	private ProfessorServiceImpl professorServiceImpl;
	
	private BookingService bookingService;

	private JavaMailSender javaMailSender;

	@BeforeEach
	void setUp() throws Exception {
		bookingService = mock(BookingService.class);
		professorRepository = mock(ProfessorRepository.class);
		lectureRepository = mock(LectureRepository.class);
		bookingRepository = mock(BookingRepository.class);
		javaMailSender = mock( JavaMailSender.class);
		professorServiceImpl = new ProfessorServiceImpl(professorRepository, javaMailSender, lectureRepository, bookingRepository,bookingService);
	}

	@Test
	void testLoginProfessor1() {
		//passing an invalid mail 
		when(professorRepository.findByEmail(anyString())).thenReturn(null);
		assertNull("Expected a null value returned, invalid email", professorServiceImpl.login("wrong@email.com", "fakePassword"));
	}
	
	@Test
	void testLoginProfessor2() throws ParseException {
		//passing a valid mail but an invalid password
		
		Course course1 = new Course(1, "testName", "A", 1,1);
		List<Course> courses = new ArrayList<>();
		courses.add(course1);
		Professor professor = new Professor(1, "testName", "testSurname", "testAddress", "test@email.com", "testPassword", courses, "d0");
		
		when(professorRepository.findByEmail(anyString())).thenReturn(professor);

		assertNull("Expected a null value returned, invalid password", professorServiceImpl.login("test@email.com", "wrongPassword"));
	}
	
	@Test
	void testLoginProfessor3() throws ParseException {
		//passing a valid mail and a valid password
		
		Course course1 = new Course(1, "testName", "A", 1,1);
		List<Course> courses = new ArrayList<>();
		courses.add(course1);
		Professor professor = new Professor(1, "testName", "testSurname", "testAddress", "test@email.com", "testPassword", courses, "d0");
		
		when(professorRepository.findByEmail(anyString())).thenReturn(professor);

		assertEquals("Expected test@email.com to be returned, successful login", "test@email.com", professorServiceImpl.login("test@email.com", "testPassword"));
	}
	
	@Test
	void testLoginProfessor4() {
		//passing a null mail and a null password
		
		when(professorRepository.findByEmail(anyString())).thenReturn(null);
		
		assertNull("Expected a null value returned, invalid email and password", professorServiceImpl.login(null, null));
	}

	
	@Test
	void testGetProfessor1() {
		//null email 
		
		assertNull("Expected a null value to be returned, null email", professorServiceImpl.getProfessor(null));
	}
	
	@Test
	void testGetProfessor2() {
		//invalid email 
		
		when(professorRepository.findByEmail(anyString())).thenReturn(null);
		
		assertNull("Expected a null value to be returned, invalid email", professorServiceImpl.getProfessor("wrong@email.com"));
	}
	
	@Test
	void testGetProfessor3() {
		//invalid email 
		
		Course course1 = new Course(1, "testName", "A", 1,1);
		List<Course> courses = new ArrayList<>();
		courses.add(course1);
		Professor professor = new Professor(1, "testName", "testSurname", "testAddress", "test@email.com", "testPassword", courses, "d0");
		
		when(professorRepository.findByEmail(anyString())).thenReturn(professor);
		
		assertEquals("Expected a professor to be returned, valid email", professorServiceImpl.getProfessor("test@email.com").getUserId(), professor.getUserId());
	}
	
	
	@Test 
	void testGetNextDate1() throws ParseException {
		//null string
		
		assertNull("Expected a null value", professorServiceImpl.getNextDate(null));
	}
	
	@Test 
	void testGetNextDate2() {
		//invalid string
		
		assertThrows(ParseException.class, ()-> professorServiceImpl.getNextDate("this could not be parsed"), "Expected a parse exception to be thrown");
	}
	
	
	@Test 
	void testGetNextDate3() throws ParseException {
		//null string
		
		String date = "2020-12-12";
		
		assertTrue("Expected a null value", professorServiceImpl.getNextDate("2020-12-11").equals(date));
	}
	
	@Test
	void testSendEmail1(){
	   try{
		   professorServiceImpl.sendEmail(null, "test", "test");
	   }
	   catch(Exception e){
	      fail("Should not have thrown any exception");
	   }
	}
	
	@Test
	void testSendEmail2(){
	   try{
			SimpleMailMessage msg = new SimpleMailMessage();
			Date date = new SimpleDateFormat("dd/MM/yyyy").parse("01/01/0101");
			CourseDto course1 = new CourseDto(1, "testName", "A", 1,1);
			List<CourseDto> courses = new ArrayList<>();
			courses.add(course1);
			ProfessorDto professorDto = new ProfessorDto(1, "testName", "testSurname", "testAddress", "test@email.com", "testPassword", courses, "d0");		 
			doNothing().when(javaMailSender).send(msg);
			professorServiceImpl.sendEmail(professorDto, "test", "test");
	  
	   }
	   catch(Exception e){
	      fail("Should not have thrown any exception");
	   }
	}
	
	
	
	@Test
	void testNotifyProfessorsAboutNumberOfStudents1() throws ParseException {
		
		Room room1 = new Room(1, "testName", 100);
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		Date date1 = new Date();
		Calendar c = Calendar.getInstance(); 
		c.setTime(date); 
		c.add(Calendar.DATE, 1);
		date = c.getTime();		
		Course course1 = new Course(1, "testName1", "A", 1,1);
		Course course2 = new Course(2, "testName2","B", 1,1);
		Course course3 = new Course(3, "testName3","C", 1,1);
		List<Course> courses1 = new ArrayList<>();
		courses1.add(course1);
		courses1.add(course2);

		List<Course> courses2 = new ArrayList<>();
		courses2.add(course1);
		courses2.add(course2);
		courses2.add(course3);
		
		Professor professor1 = new Professor(1, "testName", "testSurname", "testAddress", "testProfessor@email.com", "testPassword",courses2, "d0");

		Lecture lecture1 = new Lecture(1, 10, course1, professor1, true, date1, 90, "testDetails", room1);
		Lecture lecture2 = new Lecture(2, 10, course2, professor1, true, date, 90, "testDetails", room1);
		Lecture lecture3 = new Lecture(3, 10, course3, professor1, true, date, 90, "testDetails", room1);

		List<Lecture> lectures = new ArrayList<>();
		lectures.add(lecture1);
		lectures.add(lecture2);
		lectures.add(lecture3);

		when(lectureRepository.findAll()).thenReturn(lectures);
		when(bookingRepository.findByLecture(anyObject())).thenReturn(new ArrayList<>());
		

		   try{
			   professorServiceImpl.notifyProfessorsAboutNumberOfStudents();
		   }
		   catch(Exception e){
		      fail("Should not have thrown any exception");
		   }
		
	}
	
	@Test
	void testAddProfessors1() throws ParseException  {
		String fileName = "../../test-files/Professors.csv";
		Course course1 = new Course(1, "testName1", "A",1,1);
		Course course2 = new Course(2, "testName2", "B",1,1);
		Course course3 = new Course(3, "testName3", "C",1,1);
		Date date = new SimpleDateFormat("dd/MM/yyyy").parse("01/01/0101");
		List<Course> courses = new ArrayList<>();
		courses.add(course1);
		courses.add(course2);
		courses.add(course3);
		Professor professor1 = new Professor(1, "testName", "testSurname", "testAddress", "testProfessor@email.com", "testPassword",courses, "d0");
		List<Professor> professors = new ArrayList<>();
		
		//Student student = new Student(1, "testName", "testSurname", "testAddress", "test@email.com", "testPassword", date, courses, "testMatricola");
		professors.add(professor1);
		
		try {
		     
			when(professorRepository.findByCode(anyObject())).thenReturn(professor1).thenReturn(null);
			
			when(professorRepository.findAll()).thenReturn(professors);
			when(professorRepository.save(anyObject())).thenReturn(null);
			professorServiceImpl.addProfessors(fileName);
			
		} catch (Exception e) {
		          fail("should't come here. exception wrongly thrown");
		}
	}
	
	@Test
	void testAddProfessors2() {
		String fileName = "test-wrong";
		IOException e= new IOException();
		try {
			professorServiceImpl.addProfessors(fileName);
			
		}catch (Exception ex) {
			fail("Shouldn't come here");
		}
	}
	
	@Test
	void testGetContactedStudents1 () throws ParseException {
		Date date = new SimpleDateFormat("dd/MM/yyyy").parse("01/01/0101");//input date
		Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse("02/01/0100");
		CourseDto course1 = new CourseDto(1, "testName", "A", 1,1);
		List<CourseDto> courses = new ArrayList<>();
		RoomDto room = new RoomDto(1, "testName", 100);
		courses.add(course1);
		ProfessorDto professorDto = new ProfessorDto(1, "testName", "testSurname", "testAddress", "test@email.com", "testPassword", courses, "d0"); //input professor
		ProfessorDto professorDto1 = new ProfessorDto(2, "testName", "testSurname", "testAddress", "test@email1.com", "testPassword", courses, "d0");
		LectureDto lecture1 = new LectureDto(1, 10, course1, professorDto, true, date, 90, "testDetails", room);
		LectureDto lecture2 = new LectureDto(2, 10, course1, professorDto1, true, date, 90, "testDetails", room);//wrong professor
		LectureDto lecture3 = new LectureDto(3, 10, course1, professorDto, true, date1, 90, "testDetails", room);//wrong date
		LectureDto lecture4 = new LectureDto(4, 10, course1, professorDto1, true, date1, 90, "testDetails", room);//wrong date and prof
		StudentDto student1 = new StudentDto(1, "testName", "testSurname", "testAddress", "test@email1.com", "testPassword", date, courses, "testMatricola1");
		StudentDto student2 = new StudentDto(2, "testName", "testSurname", "testAddress", "test@email2.com", "testPassword", date, courses, "testMatricola2");
		BookingInfo bookingInfo = BookingInfo.BOOKED;
		BookingDto booking1 = new BookingDto(1, student1, lecture1, bookingInfo);
		BookingDto booking6 = new BookingDto(6, student1, lecture1, bookingInfo);
		BookingDto booking2 = new BookingDto(2, student2, lecture1, bookingInfo);
		BookingDto booking3 = new BookingDto(3, student2, lecture2, bookingInfo);//lecture of wrong professor
		BookingDto booking4 = new BookingDto(4, student2, lecture3, bookingInfo);//lecture of wrong date
		BookingDto booking5 = new BookingDto(5, student2, lecture4, bookingInfo);//lecture of wrong professor and date
		List<BookingDto> bookings = new ArrayList<>();
		bookings.add(booking1);
		bookings.add(booking2);
		bookings.add(booking3);
		bookings.add(booking4);
		bookings.add(booking5);
		bookings.add(booking6);
		List<StudentDto> students = new ArrayList<>();
		students.add(student1);
		students.add(student2);
		when(bookingService.getListAllBookings()).thenReturn(bookings);
		assertEquals("should contain student 1 and 2",professorServiceImpl.getContactedStudents(professorDto, date).size(),2);
		
	}
	
	@Test
	void testGetContactedStudents2 () throws ParseException {
		Date date = new SimpleDateFormat("dd/MM/yyyy").parse("01/01/0101");
		List<StudentDto> contactedStudents = new ArrayList<>();
		assertEquals("Expected empty list",professorServiceImpl.getContactedStudents(null, date),contactedStudents);
	}
	
	

}