package com.polito.bookingsystem;

import static org.junit.Assert.*;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.polito.bookingsystem.entity.*;
import com.polito.bookingsystem.utils.BookingInfo;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
class BookingsystemEntityTests {
	
	public Course createCourseTestSuite() {
		return new Course(11, "testCourse", "A", 1, 1);
	}
	
	public User createUserTestSuite(){
		return new User(11, "testName", "testSurname", "testAddress", "testEmail", "testPassword");
	}
	
	public Student createStudentTestSuite () throws ParseException {
		Course c = createCourseTestSuite();
		List<Course> courses = new ArrayList<>();
		courses.add(c);
		Date d =  new SimpleDateFormat("dd/MM/yyyy").parse("01/01/0101");
		return new Student(11, "testName", "testSurname", "testAddress", "testEmail", "testPassword", d, courses, "testMatricola");
	}
	
	public Professor createProfessorTestSuite () {
		Course c = createCourseTestSuite();
		List<Course> courses = new ArrayList<>();
		courses.add(c);
		return new Professor(11, "testName", "testSurname", "testAddress", "testEmail", "testPassword", courses, "d0");
	}
	
	public Room createRoomTestSuite () {
		return new Room(11, "testName", 11);
	}
	
	public Notification createNotificationTestSuite() throws ParseException {
		Date d =  new SimpleDateFormat("dd/MM/yyyy").parse("01/01/0101");
		return new Notification(11, "this is a test description", d,false,"link");
	}

	public NotificationProfessor createNotificationProfessorTestSuite() throws ParseException {
		Date d =  new SimpleDateFormat("dd/MM/yyyy").parse("01/01/0101");
		Professor professor = createProfessorTestSuite();
		return new NotificationProfessor(11, "this is a test description", d, professor,false,"link");
	}
	
	public NotificationStudent createNotificationStudentTestSuite() throws ParseException {
		Date d =  new SimpleDateFormat("dd/MM/yyyy").parse("01/01/0101");
		Student student = createStudentTestSuite();
		return new NotificationStudent(11, "this is a test description", d, student,false,"link");
	}
	
	public Lecture createLectureTestSuite() throws ParseException {
		Professor professor = createProfessorTestSuite();
		Date date =  new SimpleDateFormat("dd/MM/yyyy").parse("01/01/0101");
		Course course = createCourseTestSuite();
		Room room = createRoomTestSuite();
		return new Lecture(11, 11, course, professor, true, date, 90, "this is a test detail", room);
	}
	
	public Holiday createHolidayTestSuite() throws ParseException {
		Date date =  new SimpleDateFormat("dd/MM/yyyy").parse("01/01/0101");
		return new Holiday(11, date);
	}
	
	public Booking createBookingTestSuite() throws ParseException {
		Student student = createStudentTestSuite();
		Lecture lecture = createLectureTestSuite();
		BookingInfo bookingInfo = BookingInfo.BOOKED;
		return new Booking(11, student, lecture, bookingInfo);
	}
	
	
	/*
	 * 
   		TEST FOR COURSE ENTITY.	
	 *
	 */
	
	
	@Test
	void testGetCourseId() {
		Course c = null;
		c = createCourseTestSuite();
		assertTrue("course id is wrong", c.getCourseId().equals(11));
	}
	
	@Test
	void testSetCourseId() {
		Integer id = 0;
		Course c = null;
		c = createCourseTestSuite();
		c.setCourseId(id);
		assertNotNull("course id is null", c.getCourseId());
		assertEquals("course id is wrong", c.getCourseId(), id);
	}
	
	@Test
	void testGetCourseName() {
		Course c = null;
		c = createCourseTestSuite();
		assertEquals("course name is wrong","testCourse", c.getName());
	}
	
	@Test
	void testSetCourseName() {
		String name = "testCourseNew";
		Course c = null;
		c = createCourseTestSuite();
		c.setName(name);
		assertNotNull("course name is null", c.getName());
		assertEquals("course name is wrong", c.getName(), name);
	}
	
	@Test
	void testGetCourseDescription() {
		Course c = null;
		c = createCourseTestSuite();
		assertEquals("course code is wrong", "A", c.getCode());
	}
	
	@Test
	void testSetCourseDescription() {
		String code = "A";
		Course c = null;
		c = createCourseTestSuite();
		c.setCode(code);
		assertNotNull("course code is null", c.getCode());
		assertEquals("course code is wrong", c.getCode(), code);
	}
	
	
	
	/*
	 * 
   		TEST FOR USER ENTITY.	
	 *
	 */
	
	@Test
	void testGetUserId() {
		User u = null;
		u = createUserTestSuite();
		assertTrue("user id is wrong",u.getUserId()==11);
	}
	
	@Test
	void testSetUserId() {
		Integer id = 0;
		User u = null;
		u = createUserTestSuite();
		u.setUserId(id);
		assertNotNull("user id is null", u.getUserId());
		assertEquals("user id is wrong",u.getUserId(), id);
	}
	
	@Test
	void testGetUserName() {
		User c = null;
		c = createUserTestSuite();
		assertEquals("user name is wrong","testName",c.getName());
	}
	
	@Test
	void testSetUserName() {
		String name = "testNameNew";
		User u = null;
		u = createUserTestSuite();
		u.setName(name);
		assertNotNull("user name is null", u.getName());
		assertEquals("user name is wrong",u.getName(), name);
	}
	
	@Test
	void testGetUserSurname() {
		User u = null;
		u = createUserTestSuite();
		assertEquals("user surname is wrong","testSurname",u.getSurname());
	}
	
	@Test
	void testSetUserSurname() {
		String surname = "testSurnameNew";
		User u = null;
		u = createUserTestSuite();
		u.setSurname(surname);
		assertNotNull("user surname is null", u.getSurname());
		assertEquals("user surname is wrong",u.getSurname(), surname);
	}
	
	@Test
	void testGetUserAddress() {
		User u = null;
		u = createUserTestSuite();
		assertEquals("user address is wrong","testAddress",u.getAddress());
	}
	
	@Test
	void testSetUserAddress() {
		String address = "testAddressNew";
		User u = null;
		u = createUserTestSuite();
		u.setAddress(address);
		assertNotNull("user address is null", u.getAddress());
		assertEquals("user address is wrong",u.getAddress(), address);
	}
	
	@Test
	void testGetUserEmail() {
		User u = null;
		u = createUserTestSuite();
		assertEquals("user email is wrong","testEmail",u.getEmail());
	}
	
	@Test
	void testSetUserEmail() {
		String email = "testEmailNew";
		User u = null;
		u = createUserTestSuite();
		u.setEmail(email);
		assertNotNull("user email is null", u.getEmail());
		assertEquals("user email is wrong", u.getEmail(), email);
	}
	
	@Test
	void testGetUserPassword() {
		User u = null;
		u = createUserTestSuite();
		assertEquals("user's password is wrong", "testPassword", u.getPassword());
	}
	
	@Test
	void testSetUserPassword() {
		String password = "testPasswordNew";
		User u = null;
		u = createUserTestSuite();
		u.setPassword(password);
		assertNotNull("user password is null", u.getPassword());
		assertEquals("user's password is wrong", u.getPassword(), password);
	}
	
	
	/*
	 * 
   		TEST FOR STUDENT ENTITY.	
	 *
	 */
	
	@Test
	void testGetStudentBirthOfDate() throws ParseException {
		Date d =  new SimpleDateFormat("dd/MM/yyyy").parse("01/01/0101");
		Student s = null;
		s = createStudentTestSuite();
		assertEquals("student's date of birth is wrong", s.getDateOfBirth(), d);
	}
	
	@Test
	void testSetStudentBirthOfDate() throws ParseException {
		Date new_d =  new SimpleDateFormat("dd/MM/yyyy").parse("02/02/0202");
		Student s = null;
		s = createStudentTestSuite();
		s.setDateOfBirth(new_d);
		assertNotNull("user date of birth is null", s.getDateOfBirth());
		assertEquals("student's date of birth is wrong", s.getDateOfBirth(), new_d);
	}
	
	@Test
	void testGetStudentCourses() throws ParseException {
		Student s = null;
		s = createStudentTestSuite();
		assertEquals("student's course list is wrong",1, s.getCourses().size());

	}
	
	@Test
	void testSetStudentCourses() throws ParseException {
		Course c = new Course(0, "courseNew", "A", 1, 1);
		Student s = null;
		s = createStudentTestSuite();
		List<Course> courses = new ArrayList<>();
		courses.add(c);
		s.setCourses(courses);
		assertNotNull("user courses list is null", s.getCourses());
		assertTrue("user courses list is empty", s.getCourses().size() == 1);
		}
	
	
	/*
	 * 
   		TEST FOR PROFESSOR ENTITY.	
	 *
	 */
	
	@Test
	void testGetProfessorCourses(){
		Professor p = null;
		p = createProfessorTestSuite();
		assertEquals("professor's course list is wrong", 1, p.getCourses().size());

	}
	
	@Test
	void testSetProfessorCourses() {
		Course c = new Course(0, "courseNew", "A", 1, 1);
		Professor p = null;
		p = createProfessorTestSuite();
		List<Course> courses = new ArrayList<>();
		courses.add(c);
		p.setCourses(courses);
		assertNotNull("user courses list is null", p.getCourses());
		assertTrue("user courses list is empty", p.getCourses().size() == 1);
		}
	
	
	/*
	 * 
   		TEST FOR ROOM ENTITY.	
	 *
	 */
	
	@Test
	void testGetRoomId() {
		Room room = null;
		room = createRoomTestSuite();
		assertTrue("room id is wrong", room.getRoomId()==11);
	}
	
	@Test
	void testSetRoomId() {
		Integer id = 0;
		Room room = null;
		room = createRoomTestSuite();
		room.setRoomId(id);
		assertNotNull("room id is null", room.getRoomId());
		assertEquals("room id is wrong", room.getRoomId(), id);
	}
	
	@Test
	void testGetRoomName() {
		Room room = null;
		room = createRoomTestSuite();
		assertEquals("room name is wrong",  "testName", room.getName());
	}
	
	@Test
	void testSetRoomName() {
		String name = "testNameNew";
		Room room = null;
		room = createRoomTestSuite();
		room.setName(name);
		assertNotNull("room name is null", room.getName());
		assertEquals("room name is wrong", room.getName(), name);
	}
	
	@Test
	void testGetRoomSeats() {
		Room room = null;
		room = createRoomTestSuite();
		assertTrue("room number of seats is wrong", room.getNumberOfSeat() == 11);
	}
	
	@Test
	void testSetRoomSeats() {
		Integer seats = 12;
		Room room = null;
		room = createRoomTestSuite();
		room.setNumberOfSeat(seats);
		assertTrue("room number of seats is wrong", room.getNumberOfSeat()  == seats);
	}
	
	/*
	 * 
   		TEST FOR NOTIFICATION ENTITY.	
	 *
	 */
	
	@Test
	void testGetNotificationId() throws ParseException {
		Notification notification = null;
		notification = createNotificationTestSuite();
		assertTrue("notification id is wrong", notification.getNotificationId()==11);
	}
	
	@Test
	void testSetNotificationId() throws ParseException {
		Integer id = 0;
		Notification notification = null;
		notification = createNotificationTestSuite();
		notification.setNotificationId(id);
		assertNotNull("notification id is null", notification.getNotificationId());
		assertEquals("notification id is wrong", notification.getNotificationId(), id);
	}
	
	@Test
	void testGetNotificationDescription() throws ParseException {
		Notification notification = null;
		notification = createNotificationTestSuite();
		assertEquals("notification description is wrong", "this is a test description", notification.getDescription());
	}
	
	@Test
	void testSetNotificationDescription() throws ParseException {
		String description = "this is a new description";
		Notification notification = null;
		notification = createNotificationTestSuite();
		notification.setDescription(description);
		assertNotNull("notification description is null", notification.getDescription());
		assertEquals("notification description is wrong", notification.getDescription(), description);
	}
	
	@Test
	void testGetNotificationDate() throws ParseException {
		Date d =  new SimpleDateFormat("dd/MM/yyyy").parse("01/01/0101");
		Notification notification = null;
		notification = createNotificationTestSuite();
		assertEquals("notification date is wrong", notification.getDate(), d);
	}
	
	@Test
	void testSetNotificationDate() throws ParseException {
		Date new_d =  new SimpleDateFormat("dd/MM/yyyy").parse("02/02/0202");
		Notification notification = null;
		notification = createNotificationTestSuite();
		notification.setDate(new_d);
		assertNotNull("notification date is null", notification.getDate());
		assertEquals("notification date is wrong", notification.getDate(), new_d);
	}
	
	/*
	 * 
   		TEST FOR NOTIFICATIONPROFESSOR ENTITY.	
	 *
	 */
	@Test
	void testGetNotificationProfessor() throws ParseException {
		Professor professor = createProfessorTestSuite();
		NotificationProfessor notification = null;
		notification = createNotificationProfessorTestSuite();
		assertEquals("Professor is not the one expected", notification.getProfessor().getUserId(), professor.getUserId());
	}
	
	@Test
	void testSetNotificationProfessor() throws ParseException {
		Course c = new Course(0, "testCoursenew", "A", 1, 1);
		List<Course> courses = new ArrayList<>();
		courses.add(c);
		Professor professor = new Professor(0, "testNamenew", "testSurnamenew", "testAddressnew", "testEmailnew", "testPasswordnew", courses, "d0");
		NotificationProfessor notification = null;
		notification = createNotificationProfessorTestSuite();
		notification.setProfessor(professor);
		assertEquals("Professor is not the one expected", notification.getProfessor(), professor);
	}
	
	/*
	 * 
   		TEST FOR NOTIFICATIONSTUDENT ENTITY.	
	 *
	 */
	
	@Test
	void testGetNotificationStudent() throws ParseException {
		Student student = createStudentTestSuite();
		NotificationStudent notification = null;
		notification = createNotificationStudentTestSuite();
		assertEquals("Student is not the one expected", notification.getStudent().getUserId(), student.getUserId());
	}
	
	@Test
	void testSetNotificationStudent() throws ParseException {
		Course c = new Course(0, "testCoursenew", "A", 1, 1);
		List<Course> courses = new ArrayList<>();
		courses.add(c);
		Date d =  new SimpleDateFormat("dd/MM/yyyy").parse("02/02/0202");
		Student student = new Student(0, "testNamenew", "testSurnamenew", "testAddressnew", "testEmailnew", "testPasswordnew", d, courses, "testMatricolanew");
		NotificationStudent notification = null;
		notification = createNotificationStudentTestSuite();
		notification.setStudent(student);
		assertEquals("Professor is not the one expected", notification.getStudent(), student);
	}
	
	
	/*
	 * 
   		TEST FOR LECTURE ENTITY.	
	 *
	 */
	
	@Test
	void testGetLectureId() throws ParseException {
		Lecture lecture = null;
		lecture = createLectureTestSuite();
		assertTrue("user id is wrong",lecture.getLectureId() == 11);
	}
	
	@Test
	void testSetLectureId() throws ParseException {
		Integer id = 0;
		Lecture lecture = null;
		lecture = createLectureTestSuite();
		lecture.setLectureId(id);
		assertNotNull("lecture id is null",lecture.getLectureId());
		assertEquals("lecture id is wrong",lecture.getLectureId(), id);
	}
	
	@Test
	void testGetLectureNumberOfLesson() throws ParseException {
		Lecture lecture = null;
		lecture = createLectureTestSuite();
		assertTrue("lecture number of lesson is wrong", lecture.getNumberOfLesson() == 11);
	}
	
	@Test
	void testSetLectureNumberOfLesson() throws ParseException {
		Integer n = 12;
		Lecture lecture = null;
		lecture = createLectureTestSuite();
		lecture.setNumberOfLesson(n);
		assertNotNull("lecture number of lesson is wrong", lecture.getNumberOfLesson());
		assertEquals("lecture number of lesson is wrong",lecture.getNumberOfLesson(), n);
	}
	
	@Test
	void testGetLectureCourse() throws ParseException {
		Lecture lecture = null;
		Course course = createCourseTestSuite();
		lecture = createLectureTestSuite();
		assertEquals("lecture course is wrong", lecture.getCourse().getCourseId(), course.getCourseId());
	}
	
	@Test
	void testSetLectureCourse() throws ParseException {
		Lecture lecture = null;
		Course course = new Course(0, "testNamenew", "A", 1, 1);
		lecture = createLectureTestSuite();
		lecture.setCourse(course);
		assertNotNull("lecture course is wrong", lecture.getCourse());
		assertEquals("lecture course is wrong", lecture.getCourse(), course);
	}
	
	@Test
	void testGetLectureProfessor() throws ParseException {
		Lecture lecture = null;
		Professor professor = createProfessorTestSuite();
		lecture = createLectureTestSuite();
		assertEquals("lecture professor is wrong",lecture.getProfessor().getUserId(), professor.getUserId());
	}
	
	@Test
	void testSetLectureProfessor() throws ParseException {
		Course course = new Course(12, "testNamenew", "A", 1, 1);
		List<Course> courses = new ArrayList<>();
		courses.add(course);
		Professor professor =  new Professor(12, "testNamenew","testSurnamenew", "testAddressnew", "testEmailnew","testPasswordnew", courses, "d0");
		Lecture lecture = null;
		lecture = createLectureTestSuite();
		lecture.setProfessor(professor);
		assertNotNull("lecture professor is wrong", lecture.getProfessor());
		assertEquals("user address is wrong", lecture.getProfessor(), professor);
	}
	
	@Test
	void testGetLectureRemotly() throws ParseException {
		Lecture lecture = null;
		lecture = createLectureTestSuite();
		assertEquals("lecture remotly is wrong",true, lecture.getRemotly());
	}
	
	@Test
	void testSetLectureRemotly() throws ParseException {
		Lecture lecture = null;
		lecture = createLectureTestSuite();
		lecture.setRemotly(false);
		assertEquals("lecture remotly is wrong",false, lecture.getRemotly());
	}
	
	@Test
	void testGetLectureDate() throws ParseException {
		Lecture lecture = null;
		Date date =  new SimpleDateFormat("dd/MM/yyyy").parse("01/01/0101");
		lecture = createLectureTestSuite();
		assertEquals("lecture date is wrong", lecture.getDate(), date);
	}
	
	@Test
	void testSetLectureDate() throws ParseException {
		Lecture lecture = null;
		Date date =  new SimpleDateFormat("dd/MM/yyyy").parse("02/02/0202");
		lecture = createLectureTestSuite();
		lecture.setDate(date);
		assertNotNull("lecture date is wrong", lecture.getDate());
		assertEquals("lecture date is wrong", lecture.getDate(), date);
	}
	
	
	@Test
	void testGetLectureDuration() throws ParseException {
		Lecture lecture = null;
		lecture = createLectureTestSuite();
		Integer duration = 90;
		assertEquals("lecture duration is wrong", lecture.getDuration(), duration);
	}
	
	@Test
	void testSetLectureDuration() throws ParseException {
		Lecture lecture = null;
		Integer duration = 120;
		lecture = createLectureTestSuite();
		lecture.setDuration(duration);
		assertNotNull("lecture duration is wrong", lecture.getDuration());
		assertEquals("lecture duration is wrong", lecture.getDuration(), duration);
	}
	
	
	@Test
	void testGetLectureProgramDetails() throws ParseException {
		Lecture lecture = null;
		lecture = createLectureTestSuite();
		assertEquals("lecture program details are wrong","this is a test detail", lecture.getProgramDetails());
	}
	
	@Test
	void testSetLectureProgramDetails() throws ParseException {
		Lecture lecture = null;
		String detail =  "this is a new test detail";
		lecture = createLectureTestSuite();
		lecture.setProgramDetails(detail);
		assertNotNull("lecture program details are wrong", lecture.getProgramDetails());
		assertEquals("lecture program details are wrong", lecture.getProgramDetails(), detail);
	}
	
	
	@Test
	void testGetLectureRoom() throws ParseException {
		Lecture lecture = null;
		lecture = createLectureTestSuite();
		Room room = createRoomTestSuite();
		assertEquals("lecture room is wrong", lecture.getRoom().getRoomId(), room.getRoomId());
	}
	
	@Test
	void testSetLectureRoom() throws ParseException {
		Lecture lecture = null;
		Room room = new Room();
		lecture = createLectureTestSuite();
		lecture.setRoom(room);
		assertNotNull("lecture room is wrong", lecture.getRoom());
		assertEquals("lecture room is wrong", lecture.getRoom(), room);
	}
	
	
	/*
	 * 
   		TEST FOR HOLIDAY ENTITY.	
	 *
	 */
	
	@Test
	void testGetHolidayId() throws ParseException {
		Holiday holiday = null;
		holiday = createHolidayTestSuite();
		assertTrue("holiday id is wrong",holiday.getHolidayId() == 11);
	}
	
	@Test
	void testSetHolidayId() throws ParseException {
		Integer id = 0;
		Holiday holiday = null;
		holiday = createHolidayTestSuite();
		holiday.setHolidayId(id);
		assertNotNull("holiday id is null",holiday.getHolidayId());
		assertEquals("holiday id is wrong",holiday.getHolidayId(), id);
	}
	
	
	@Test
	void testGetHolidayDate() throws ParseException {
		Holiday holiday = null;
		holiday = createHolidayTestSuite();
		Date date =  new SimpleDateFormat("dd/MM/yyyy").parse("01/01/0101");
		assertEquals("holiday date is wrong", holiday.getDate(), date);
	}
	
	@Test
	void testSetHolidayDate() throws ParseException {
		Date date =  new SimpleDateFormat("dd/MM/yyyy").parse("02/02/0202");
		Holiday holiday = null;
		holiday = createHolidayTestSuite();
		holiday.setDate(date);
		assertNotNull("holiday date is wrong", holiday.getDate());
		assertEquals("holiday date is wrong", holiday.getDate(), date);
	}
	
	/*
	 * 
   		TEST FOR BOOKING ENTITY.	
	 *
	 */
	
	@Test
	void testGetBookingId() throws ParseException {
		Booking booking = null;
		booking = createBookingTestSuite();
		assertTrue("booking id is wrong",booking.getBookingId() == 11);
	}
	
	@Test
	void testSetBookingtId() throws ParseException {
		Integer id = 0;
		Booking booking = null;
		booking = createBookingTestSuite();
		booking.setBookingId(id);
		assertNotNull("booking id is wrong",booking.getBookingId());
		assertEquals("booking id is wrong",booking.getBookingId(), id);
	}
	
	@Test
	void testGetBookingStudent() throws ParseException {
		Booking booking = null;
		booking = createBookingTestSuite();
		Student student = createStudentTestSuite();
		assertTrue("booking student is wrong",booking.getStudent().getUserId() == student.getUserId());
	}
	
	@Test
	void testSetBookingtStudent() throws ParseException {
		Date date =  new SimpleDateFormat("dd/MM/yyyy").parse("02/02/0202");
		Course c = new Course(0, "courseNew", "A", 1,1 );
		List<Course> courses = new ArrayList<>();
		courses.add(c);
		Student student = new Student(0, "testNamenew", "testSurnamenew", "testAddressnew", "testEmailnew", "testPasswordnew", date, courses, "testMatricolanew");
		Booking booking = null;
		booking = createBookingTestSuite();
		booking.setStudent(student);
		assertNotNull("booking student is wrong",booking.getStudent());
		assertEquals("booking student is wrong",booking.getStudent().getUserId(), student.getUserId());
	}
	
	@Test
	void testGetBookingLecture() throws ParseException {
		Booking booking = null;
		booking = createBookingTestSuite();
		Lecture lecture = createLectureTestSuite();
		assertTrue("booking id is wrong",booking.getLecture().getLectureId() == lecture.getLectureId());
	}
	
	@Test
	void testSetBookingtLecture() throws ParseException {
		Professor professor = createProfessorTestSuite();
		Date date =  new SimpleDateFormat("dd/MM/yyyy").parse("02/02/0202");
		Course course = createCourseTestSuite();
		Room room = createRoomTestSuite();
		Lecture lecture = new Lecture(12, 12, course, professor, true, date, 120, "this is a new test detail", room);
		Booking booking = null;
		booking = createBookingTestSuite();
		booking.setLecture(lecture);
		assertNotNull("booking lecture is wrong", booking.getLecture());
		assertEquals("booking lecture is wrong", booking.getLecture().getLectureId(), lecture.getLectureId());
	}
	
	
	@Test
	void testGetBookingInfo() throws ParseException {
		BookingInfo bookingInfo = BookingInfo.BOOKED;
		Booking booking = null;
		booking = createBookingTestSuite();
		assertTrue("booking info is wrong",booking.getBookingInfo() == bookingInfo);
	}
	
	@Test
	void testSetBookingInfo() throws ParseException {
		BookingInfo bookingInfo = BookingInfo.CANCELED_BY_PROF;
		Booking booking = null;
		booking = createBookingTestSuite();
		booking.setBookingInfo(bookingInfo);
		assertNotNull("booking info is wrong",booking.getBookingInfo());
		assertEquals("booking info is wrong",booking.getBookingInfo(), bookingInfo);
	}
}
