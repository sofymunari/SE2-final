package com.polito.bookingsystem.utils.serviceTests;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.fail;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import org.junit.Rule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import org.springframework.test.context.junit4.SpringRunner;
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
import com.polito.bookingsystem.repository.RoomRepository;
import com.polito.bookingsystem.repository.LectureRepository;
import com.polito.bookingsystem.repository.CourseRepository;
import com.polito.bookingsystem.repository.ProfessorRepository;
import com.polito.bookingsystem.repository.HolidayRepository;
import com.polito.bookingsystem.repository.StudentRepository;
import com.polito.bookingsystem.service.StudentService;
import com.polito.bookingsystem.service.ProfessorService;
import com.polito.bookingsystem.service.BookingService;
import com.polito.bookingsystem.service.impl.LectureServiceImpl;
import com.polito.bookingsystem.service.impl.CourseServiceImpl;
import com.polito.bookingsystem.utils.BookingInfo;
import com.polito.bookingsystem.utils.Schedule;

@RunWith(SpringRunner.class)
@DataJpaTest
class LectureServiceTests {

	@Autowired
	private LectureRepository lectureRepository;
	@Autowired
	private HolidayRepository holidayRepository;
	@Autowired
	private StudentRepository studentRepository;
	@Autowired
	private BookingRepository bookingRepository;
	@Autowired
	private CourseRepository courseRepository;
	@Autowired
	private ProfessorRepository professorRepository;
	@Autowired
	private RoomRepository roomRepository;
	
	private StudentService studentService;

	private ProfessorService professorService;

	private BookingService bookingService;
	
	private LectureServiceImpl lectureServiceImpl;
	
	private ArrayList<Schedule> scheduleCourses;
	
	
	
	@BeforeEach
	public void setUp() throws Exception {

		bookingRepository = mock(BookingRepository.class);
		holidayRepository = mock(HolidayRepository.class);
		studentRepository = mock(StudentRepository.class);
		lectureRepository = mock(LectureRepository.class);
		roomRepository = mock(RoomRepository.class);
		courseRepository = mock(CourseRepository.class);
		studentService = mock(StudentService.class);
		professorService = mock(ProfessorService.class);
		professorRepository = mock(ProfessorRepository.class);		
		bookingService = mock(BookingService.class);
		Room room = new Room(1, "testName", 100);
		Course course1 = new Course(1, "testName1", "XY1211",1,1);
		Course course2 = new Course(1, "testName1", "XY1212",1,1);
		Schedule schedule1 = new Schedule(1,"Mon",120,"12:00",room,course1);
		Schedule schedule2 = new Schedule(2,"Tue",120,"12:00",room,course2);
		scheduleCourses = new ArrayList<>();
		scheduleCourses.add(schedule1);
		scheduleCourses.add(schedule2);
		lectureServiceImpl = new LectureServiceImpl(lectureRepository, studentRepository, bookingRepository, studentService, professorRepository,courseRepository,roomRepository,holidayRepository,professorService,bookingService, scheduleCourses);

	}

	@Test
	void testGetListLectures1() {
		//passing an invalid mail 
		
		when(studentRepository.findByEmail(anyString())).thenReturn(null);
		assertEquals("Expected an empty list to be returned", lectureServiceImpl.getListLectures("wrong@email.com"), new ArrayList<>());
	}
	
	@Test
	void testGetListLectures2() {
		//passing a null mail 
		
		when(studentRepository.findByEmail(anyString())).thenReturn(null);		
		assertEquals("Expected an empty list to be returned", lectureServiceImpl.getListLectures(null), new ArrayList<>());
	}
	
	@Test
	void testGetListLectures3() throws ParseException {
		//passing a valid mail 
		
		Room room1 = new Room(1, "testName", 100);
		Date date = new SimpleDateFormat("dd/MM/yyyy").parse("01/01/3000");
		
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

		when(studentRepository.findByEmail(anyString())).thenReturn(student1);
		when(lectureRepository.findAll()).thenReturn(lectures);
		
		assertTrue("The number of lecture is wrong", lectureServiceImpl.getListLectures("test@email.com").size() == 2);

	}
	
	@Test
	void testGetListLectures4() throws ParseException {
		//passing a valid mail but student without courses
		
		Date date = new SimpleDateFormat("dd/MM/yyyy").parse("01/01/0101");
		List<Course> courses = new ArrayList<>();
		Student student1 = new Student(1, "testName", "testSurname", "testAddress", "test@email.com", "testPassword", date, courses, "testMatricola");

		when(studentRepository.findByEmail(anyString())).thenReturn(student1);
				
		assertEquals("The number of lecture is wrong", lectureServiceImpl.getListLectures("test@email.com"), new ArrayList<>());

	}
	
	@Test
	void testDeleteLecture1() {
		//null lectureId
		
		assertFalse("Expected false to be returned, null lectureId", lectureServiceImpl.deleteLecture(null));
		
	}
	
	@Test
	void testDeleteLecture2() {
		//negative lectureId
		
		assertFalse("Expected false to be returned, negative lectureId", lectureServiceImpl.deleteLecture(-1));
		
	}
	
	@Test
	void testDeleteLecture3() {
		//invalid lectureId
		
		when(lectureRepository.findByLectureId(anyInt())).thenReturn(null);
		
		assertFalse("Expected false to be returned, invalidId lectureId", lectureServiceImpl.deleteLecture(999));

	}
	
	@Test
	void testDeleteLecture4() throws ParseException {
		//lecture cannot be deleted, the lecture is in less than an hour
		
		Room room1 = new Room(1, "testName", 100);
		Date date = new Date();
		
		Course course1 = new Course(1, "testName1", "A",1,1);
		Course course2 = new Course(2, "testName2", "B",1,1);
		Course course3 = new Course(3, "testName3", "C",1,1);

		List<Course> courses2 = new ArrayList<>();
		courses2.add(course1);
		courses2.add(course2);
		courses2.add(course3);
		
		Professor professor1 = new Professor(1, "testName", "testSurname", "testAddress", "testProfessor@email.com", "testPassword",courses2,"d0");

		Lecture lecture1 = new Lecture(1, 10, course1, professor1, true, date, 90, "testDetails", room1);
		
		when(lectureRepository.findByLectureId(anyInt())).thenReturn(lecture1);
		
		assertFalse("Expected false to be returned, lesson in less than an hour", lectureServiceImpl.deleteLecture(1));

	}
	
	@Test
	void testDeleteLecture5() throws ParseException {
		//lecture can be deleted
		
		Room room1 = new Room(1, "testName", 100);
		Date date = new SimpleDateFormat("dd-MM-yy-HH.mm.ss").parse("20-05-2021-12.00.00");
		
		Course course1 = new Course(1, "testName1", "A",1,1);
		Course course2 = new Course(2, "testName2", "B",1,1);
		Course course3 = new Course(3, "testName3", "C",1,1);

		List<Course> courses2 = new ArrayList<>();
		courses2.add(course1);
		courses2.add(course2);
		courses2.add(course3);
		
		Professor professor1 = new Professor(1, "testName", "testSurname", "testAddress", "testProfessor@email.com", "testPassword",courses2,"d0");

		Lecture lecture1 = new Lecture(1, 10, course1, professor1, true, date, 90, "testDetails", room1);
		when(lectureRepository.findByLectureId(anyInt())).thenReturn(lecture1);
		when(lectureRepository.save(anyObject())).thenReturn(null);
		
		assertTrue("Expected true to be returned, lesson should be cancelled", lectureServiceImpl.deleteLecture(1));

	}
	
	@Test
	void testDeleteLecture6() throws ParseException {
		//lecture can be deleted
		
		Room room1 = new Room(1, "testName", 100);
		Date date = new SimpleDateFormat("dd-MM-yy-HH.mm.ss").parse("20-05-2021-12.00.00");
		
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
		
		BookingInfo bookingInfo = BookingInfo.WAITING;
		
		Booking booking1 = new Booking(1, student1, lecture1, bookingInfo);
		Booking booking2 = new Booking(2, student1, lecture1, bookingInfo);
		Booking booking3 = new Booking(3, student1, lecture1, bookingInfo);
		
		List<Booking> bookings = new ArrayList<>();
		bookings.add(booking1);
		bookings.add(booking2);
		bookings.add(booking3);
		
		when(lectureRepository.findByLectureId(anyInt())).thenReturn(lecture1);
		when(bookingRepository.findAll()).thenReturn(bookings);
		when(lectureRepository.save(anyObject())).thenReturn(null);
		when(bookingRepository.save(anyObject())).thenReturn(null);
		doNothing().when(studentService).sendEmail(anyObject(), anyString(), anyString());
		
		assertTrue("Expected true to be returned, lesson should be cancelled", lectureServiceImpl.deleteLecture(1));

	}

	
	@Test
	void testGetProfessorLectures1() {
		//null email
		assertTrue("Expected empty list, null email",lectureServiceImpl.getProfessorLectures(null).isEmpty());
	}
	
	@Test
	void testGetProfessorLectures2() {
		//invalid email
		
		when(professorRepository.findByEmail(anyString())).thenReturn(null);

		assertTrue("Expected null, null email", lectureServiceImpl.getProfessorLectures("wrong@email").isEmpty());
	}
	
	@Test
	void testGetProfessorLectures3() throws ParseException {
		//valid email		
		
		Room room1 = new Room(1, "testName", 100);
		Date date = new SimpleDateFormat("dd-MM-yy-HH.mm.ss").parse("20-05-2021-12.00.00");
		
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
		
		Professor professor1 = new Professor(1, "testName", "testSurname", "testAddress", "testProfessor@email.com", "testPassword",courses2,"d0");

		Lecture lecture1 = new Lecture(1, 10, course1, professor1, true, date, 90, "testDetails", room1);
		Lecture lecture2 = new Lecture(2, 10, course2, professor1, true, date, 90, "testDetails", room1);
		Lecture lecture3 = new Lecture(3, 10, course3, professor1, true, date, 90, "testDetails", room1);

		List<Lecture> lectures = new ArrayList<>();
		lectures.add(lecture1);
		lectures.add(lecture2);
		lectures.add(lecture3);
		
		when(professorRepository.findByEmail(anyString())).thenReturn(professor1);
		when(lectureRepository.findByProfessor(anyObject())).thenReturn(lectures);

		assertTrue("Expected a list of 3 elements",lectureServiceImpl.getProfessorLectures("testProfessor@email").size() == 3 );
	}
	
	@Test
	void testGetLectureById1 () {
		//null id
		
		assertNull("Expected null to be returned, null id passed", lectureServiceImpl.getLectureById(null));
	}
	
	@Test
	void testGetLectureById2 () {
		//negative id
		
		assertNull("Expected null to be returned, negative id passed", lectureServiceImpl.getLectureById(-4));
	}
	
	@Test
	void testGetLectureById3 () {
		//valid id
		
		Room room1 = new Room(1, "testName", 100);
		Date date = new Date();
		
		Course course1 = new Course(1, "testName1", "A",1,1);
		Course course2 = new Course(2, "testName2", "B",1,1);
		Course course3 = new Course(3, "testName3", "C",1,1);

		List<Course> courses2 = new ArrayList<>();
		courses2.add(course1);
		courses2.add(course2);
		courses2.add(course3);
		
		Professor professor1 = new Professor(1, "testName", "testSurname", "testAddress", "testProfessor@email.com", "testPassword",courses2,"d0");

		Lecture lecture1 = new Lecture(1, 10, course1, professor1, true, date, 90, "testDetails", room1);
		
		when(lectureRepository.findByLectureId(anyInt())).thenReturn(lecture1);
		
		assertTrue("Expected lecture 1 to be returned", lectureServiceImpl.getLectureById(1).getLectureId() == 1);
	}
	
	
	
		
	@Test
	void testsave1(){
	   try{
			RoomDto roomDto = new RoomDto(1, "testName", 4);
			Date date = new SimpleDateFormat("dd/MM/yyyy").parse("01/01/0101");
			
			CourseDto courseDto = new CourseDto(1, "testName1", "A",1,1);
			List<CourseDto> coursesDto = new ArrayList<>();
			coursesDto.add(courseDto);

			StudentDto studentDto = new StudentDto(4, "testName4", "testSurname4", "testAddress4", "test4@email.com", "testPassword4", date, coursesDto, "testMatricola4");
			ProfessorDto professorDto = new ProfessorDto(1, "testName", "testSurname", "testAddress", "testProfessor@email.com", "testPassword",coursesDto,"d0");
			LectureDto lectureDto = new LectureDto(2, 10, courseDto, professorDto, false, date, 90, "testDetails2", roomDto);
			
		
			when(lectureRepository.save(anyObject())).thenReturn(null);
			
			lectureServiceImpl.save(lectureDto);
	   }
	   catch(Exception e){
	      fail("Should not have thrown any exception");
	   }
	}
	
	@Test
	void testsave2(){
	   try{
		   lectureServiceImpl.save(null);
	   }
	   catch(Exception e){
	      fail("Should not have thrown any exception");
	   }
	}
	
	
	@Test
	void testGetListAllLectures1() {
		
		when(lectureRepository.findAll()).thenReturn(new ArrayList<>());
		
		assertTrue("Expected an empty list to be returned", lectureServiceImpl.getListAllLectures().size() == 0);
		
	}
	
	
	@Test
	void testGetListAllLectures2() throws ParseException {
		
		
		Room room1 = new Room(1, "testName", 100);
		Date date = new SimpleDateFormat("dd-MM-yy-HH.mm.ss").parse("20-05-2021-12.00.00");
		
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
		
		Professor professor1 = new Professor(1, "testName", "testSurname", "testAddress", "testProfessor@email.com", "testPassword",courses2,"d0");

		Lecture lecture1 = new Lecture(1, 10, course1, professor1, true, date, 90, "testDetails", room1);
		Lecture lecture2 = new Lecture(2, 10, course2, professor1, true, date, 90, "testDetails", room1);
		Lecture lecture3 = new Lecture(3, 10, course3, professor1, true, date, 90, "testDetails", room1);

		List<Lecture> lectures = new ArrayList<>();
		lectures.add(lecture1);
		lectures.add(lecture2);
		lectures.add(lecture3);
		
		when(lectureRepository.findAll()).thenReturn(lectures);
		
		assertTrue("Expected an empty list to be returned", lectureServiceImpl.getListAllLectures().size() == 3);
		
	}
	
	@Test
	void testAddLectures1() {
		String fileName = "wrong-file";
		
		try {
			
			lectureServiceImpl.addLectures(fileName);
			
		}catch(Exception e) {
			fail("Shouldn't get here");
		}
		
	}
	
	
	@Test
	void testAddLectures2() throws ParseException {
		String fileName = "../../test-files/Schedule.csv";
		Date date = new SimpleDateFormat("dd-MM-yy-HH.mm.ss").parse("20-05-2021-12.00.00");
		Course course1 = new Course(1, "testName1", "A",1,1);
		Course course2 = new Course(2, "testName2", "B",1,1);
		Course course3 = new Course(3, "testName3", "C",1,1);
		Room room = new Room(1, "testName", 100);
		List<Course> courses = new ArrayList<>();
		courses.add(course1);
		courses.add(course2);
		courses.add(course3);
		List<Professor> proflist=new ArrayList<>();
		List<Room> rooms = new ArrayList<>();
		rooms.add(room);
		Professor professor1 = new Professor(1, "testName", "testSurname", "testAddress", "testProfessor@email.com", "testPassword",courses,"d0");
		proflist.add(professor1);
		Lecture lecture1 = new Lecture(1, 10, course1, professor1, true, date, 90, "testDetails", room);
		List<Lecture> lectures = new ArrayList<>();
		lectures.add(lecture1);

		try {
		     
			when(courseRepository.findByCode(anyObject())).thenReturn(course1);
			when(roomRepository.findByName(anyObject())).thenReturn(room);
			when(roomRepository.findAll()).thenReturn(rooms);
			when(professorRepository.findAll()).thenReturn(proflist);
			when(roomRepository.save(anyObject())).thenReturn(null);
			when(lectureRepository.findAll()).thenReturn(lectures);
			when(lectureRepository.save(anyObject())).thenReturn(null);
			lectureServiceImpl.addLectures(fileName);
		        

		} catch (Exception e) {
		          System.out.println(e.getMessage());
		          e.printStackTrace();
		       }
	}
	
	@Test
	void testAddLectures3() throws ParseException {
		String fileName = "../../test-files/Schedule.csv";
		Date date = new SimpleDateFormat("dd-MM-yy-HH.mm.ss").parse("20-05-2021-12.00.00");
		Course course1 = new Course(1, "testName1", "XY1211",1,1);
		Course course2 = new Course(2, "testName2", "B",1,1);
		Course course3 = new Course(3, "testName3", "C",1,1);
		Room room = new Room(1, "testName", 100);
		List<Course> courses = new ArrayList<>();
		courses.add(course1);
		courses.add(course2);
		courses.add(course3);
		List<Professor> proflist=new ArrayList<>();
		List<Room> rooms = new ArrayList<>();
		rooms.add(room);
		Professor professor1 = new Professor(1, "testName", "testSurname", "testAddress", "testProfessor@email.com", "testPassword",courses,"d0");
		proflist.add(professor1);
		Lecture lecture1 = new Lecture(1, 10, course1, professor1, true, date, 90, "testDetails", room);
		List<Lecture> lectures = new ArrayList<>();
		lectures.add(lecture1);

		try {
		     
			when(courseRepository.findByCode(anyObject())).thenReturn(course1);
			when(roomRepository.findByName(anyObject())).thenReturn(null);
			when(roomRepository.findAll()).thenReturn(rooms);
			when(professorRepository.findAll()).thenReturn(proflist);
			when(roomRepository.save(anyObject())).thenReturn(null);
			when(lectureRepository.findAll()).thenReturn(lectures);
			when(lectureRepository.save(anyObject())).thenReturn(null);
			lectureServiceImpl.addLectures(fileName);
		        

		} catch (Exception e) {
		          System.out.println(e.getMessage());
		          e.printStackTrace();
		       }
		}
	
	@Test
	void testGetFirstDate1() {
		Calendar myCalendar = Calendar.getInstance();
		Calendar testCalendar = Calendar.getInstance();
		Integer[] days = {Calendar.MONDAY,Calendar.TUESDAY,Calendar.WEDNESDAY,Calendar.THURSDAY,Calendar.FRIDAY,Calendar.SATURDAY};
		String[] days1 = {"Mon","Tue","Wed","Thu","Fri","Sat"};
		for(int i=0;i<6;i++) {
			myCalendar.set(Calendar.DAY_OF_WEEK,days[i] );
			assertEquals("Expected",lectureServiceImpl.getFirstDate(testCalendar, days1[i]).getFirstDayOfWeek(),myCalendar.getFirstDayOfWeek() );
		}
		
	}
	
	@Test
	void testGetFirstDate() {
		//null case
		
		Calendar testCalendar = Calendar.getInstance();
		String day = "wrongDay";
		
		assertNull("Expected",lectureServiceImpl.getFirstDate(testCalendar, day));
	}
	
	@Test
	void testRemoveHolidays1() throws ParseException {
		String fileName = "../../test-files/Holidays.csv";
		Date date = new SimpleDateFormat("dd-MM-yy-HH.mm.ss").parse("20-05-2021-12.00.00");
		Date date1 = new SimpleDateFormat("dd-MM-yy-HH.mm.ss").parse("06-01-2021-12.00.00");
		Date date2 = new SimpleDateFormat("dd-MM-yy-HH.mm.ss").parse("25-12-2020-12.00.00");
		Room room = new Room(1, "testName", 100);
		Course course1 = new Course(1, "testName1", "XY1211",1,1);
		Course course2 = new Course(2, "testName2", "B",1,1);
		Course course3 = new Course(3, "testName3", "C",1,1);
		List<Course> courses = new ArrayList<>();
		courses.add(course1);
		courses.add(course2);
		courses.add(course3);
		Professor professor1 = new Professor(1, "testName", "testSurname", "testAddress", "testProfessor@email.com", "testPassword",courses,"d0");
		Lecture lecture1 = new Lecture(1, 10, course1, professor1, true, date, 90, "testDetails", room);
		Lecture lecture2 = new Lecture(1, 10, course2, professor1, true, date1, 90, "testDetails", room);
		Lecture lecture3 = new Lecture(1, 10, course3, professor1, true, date2, 90, "testDetails", room);
		List<Lecture> lectures = new ArrayList<>();
		lectures.add(lecture1);
		lectures.add(lecture2);
		lectures.add(lecture3);
		Student student1 = new Student(1, "testName", "testSurname", "testAddress", "test@email.com", "testPassword", date, courses, "testMatricola");
		Student student2 = new Student(1, "testName", "testSurname", "testAddress", "test@email.com", "testPassword", date, courses, "testMatricola");
		BookingInfo bookingInfo = BookingInfo.WAITING;
		BookingInfo bookingInfo1 = BookingInfo.ATTENDED;
		Booking booking10 = new Booking(1, student1, lecture2, bookingInfo);
		Booking booking11 = new Booking(1, student2, lecture2, bookingInfo1);
		List<Booking> bookings1 = new ArrayList<>();
		bookings1.add(booking10);
		bookings1.add(booking11);
		Booking booking20 = new Booking(1, student1, lecture3, bookingInfo);
		Booking booking21 = new Booking(1, student2, lecture3, bookingInfo1);
		List<Booking> bookings2 = new ArrayList<>();
		bookings2.add(booking20);
		bookings2.add(booking21);
		try {
			when(lectureRepository.findAll()).thenReturn(lectures);
			when(holidayRepository.save(anyObject())).thenReturn(null);
			when(bookingRepository.findByLecture(anyObject())).thenReturn(bookings1).thenReturn(bookings2);
			doNothing().when(bookingRepository).deleteById(anyInt());
			doNothing().when(lectureRepository).deleteById(anyInt());
			doNothing().when(studentService).sendEmail(anyObject(), anyString(), anyString());
			doNothing().when(professorService).sendEmail(anyObject(), anyString(), anyString());
			
			lectureServiceImpl.removeHolidays(fileName);
		
		}catch(Exception e) {
	          System.out.println(e.getMessage());
	          e.printStackTrace();
	       }
	}

	@Test	
	void testModifySchedule1() throws ParseException  {
		//String day="Monday"
		Room room = new Room(1, "testName", 100);
		Date date = new SimpleDateFormat("dd-MM-yyyy").parse("11-01-1997");
		Date date1 = new SimpleDateFormat("dd-MM-yyyy-HH.mm.ss").parse("11-01-2021-00.00.00");
		Date date2 = new SimpleDateFormat("dd-MM-yyyy-HH.mm.ss").parse("09-12-2020-00.00.00");
		Course course1 = new Course(1, "testName1", "XY1211",1,1);
		Course course2 = new Course(2, "testName2", "XY1212",2,2);
		Course course3 = new Course(3, "testName3", "XY1213",3,3);
		List<Course> courses = new ArrayList<>();
		List<Course> courses1 = new ArrayList<>();
		courses.add(course2);
		courses.add(course1);
		courses1.add(course3);
		Professor professor1 = new Professor(1, "testName", "testSurname", "testAddress", "testProfessor@email.com", "testPassword",courses,"d0");
		Professor professor2 = new Professor(2, "testName", "testSurname", "testAddress", "testProfessor@email.com", "testPassword",courses1,"d1");
		List<Professor> professors = new ArrayList<>();
		Lecture lecture1 = new Lecture(1, 10, course1, professor1, true, date2, 120, "testDetails", room);//lecture passed not on monday
		Lecture lecture2 = new Lecture(2, 10, course1, professor1, true, date1, 120, "testDetails", room);//lecture future monday 
		Lecture lecture3 = new Lecture(1, 10, course2, professor1, true, date1, 120, "testDetails", room);//lecture of another course
		List<Lecture> lectures = new ArrayList<>();
		lectures.add(lecture1);
		lectures.add(lecture2);
		lectures.add(lecture3);
		BookingInfo bookingInfo = BookingInfo.BOOKED;
        Student student1 = new Student(1, "testName", "testSurname", "testAddress", "test@email.com", "testPassword", date, courses, "testMatricola");	
        Student student2 = new Student(2, "testName", "testSurname", "testAddress", "test@email.com", "testPassword", date, courses, "testMatricola");	
		Booking booking1 = new Booking(1, student1, lecture2, bookingInfo);	//booking for changing lecture	
		Booking booking2 = new Booking(2, student2, lecture3, bookingInfo); //booking for not changing lecture
		List<Booking> bookings = new ArrayList<>();
		bookings.add(booking1);
		bookings.add(booking2);
		professors.add(professor2);
		professors.add(professor1);
		List<Student> students = new ArrayList<>();
		students.add(student1);
		students.add(student2);
		when(roomRepository.findByRoomId(anyInt())).thenReturn(room); //room
		when(courseRepository.findByCode(anyObject())).thenReturn(course1); //course1 //schedule = Monday 12:00 120 minutes room
		when(professorRepository.findAll()).thenReturn(professors); //professor1
		when(lectureRepository.findAll()).thenReturn(lectures); // two lectures of course
		when(lectureRepository.findByLectureId(anyInt())).thenReturn(lecture2);
		when(bookingRepository.findAll()).thenReturn(bookings);
		when(bookingService.deleteBookingOfficer(anyInt())).thenReturn(false);
		when(lectureRepository.save(anyObject())).thenReturn(null);
		when(studentRepository.findAll()).thenReturn(students);
		doNothing().when(studentService).sendEmail(anyObject(), anyString(), anyString());
		assertTrue("should return true",lectureServiceImpl.modifySchedule("Tue", 120, "13:00", 1,"XY1211" , 1));
		
	}
		
	
	
	@Test
	void testRemoveHolidays2 () {
		String fileName = "wrong-file-name";
		
		try {
			
			lectureServiceImpl.removeHolidays(fileName);
			
		}catch(Exception e) {
			fail("Shouldn't get here");
		}
		
	}
	
	@Test
	void testRemoveHolidays3 () {
		String fileName = "../../test-files/Holidays-wrong.csv";
		
		try {
			when(lectureRepository.findAll()).thenReturn(null);
			lectureServiceImpl.removeHolidays(fileName);
			
		}catch(Exception e) {
			fail("Shouldn't get here");
		}
	}       	
	
	
	@Test
	void testModifySchedule2() {
		//room = null
		when(roomRepository.findByRoomId(anyInt())).thenReturn(null);
		assertFalse(lectureServiceImpl.modifySchedule("Tue", 120, "13:00", 1,"XY1211" , 1));
	}
		
	@Test
	void testModifySchedule3() {
		//course = null
		Room room = new Room(1, "testName", 100);
		when(roomRepository.findByRoomId(anyInt())).thenReturn(room);
		when(courseRepository.findByCode(anyObject())).thenReturn(null);
		assertFalse(lectureServiceImpl.modifySchedule("Tue", 120, "13:00", 1,"XY1211" , 1));
	}
	
	@Test
	void testModifySchedule4() {
		//course doesn't belong to any professor
		Room room = new Room(1, "testName", 100);
		Course course1 = new Course(1, "testName1", "XY1211",1,1);
		Course course2 = new Course(2, "testName2", "XY1212",2,2);
		List<Course> courses = new ArrayList<>();
		courses.add(course2);
		Professor professor1 = new Professor(1, "testName", "testSurname", "testAddress", "testProfessor@email.com", "testPassword",courses,"d0");
		List<Professor> professors = new ArrayList<>();
		professors.add(professor1);
		when(roomRepository.findByRoomId(anyInt())).thenReturn(room);
		when(courseRepository.findByCode(anyObject())).thenReturn(course1);
		when(professorRepository.findAll()).thenReturn(professors);
		assertFalse(lectureServiceImpl.modifySchedule("Tue", 120, "13:00", 1,"XY1211" , 1));
	}
	
	@Test
	void testGetRooms() {
		Room room1 = new Room(1, "testName", 100);
		Room room2 = new Room(2, "testName", 100);
		List<Room> rooms = new ArrayList<>();
		rooms.add(room1);
		rooms.add(room2);
		when(roomRepository.findAll()).thenReturn(rooms);
		assertTrue("expected two rooms",lectureServiceImpl.getRooms().size()==2);
		assertTrue("expected room id 1",lectureServiceImpl.getRooms().get(0).getRoomId()==1);
	}
	
	@Test
	void getScheduleCourses() {
		assertTrue(lectureServiceImpl.getScheduleCourses("XY1211").get(0).getId()==1);
	}
	

	    
	
	
	
}
	
