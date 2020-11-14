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

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
class BookingsystemEntityTests {
	
	public Course createCourseTestSuite() {
		return new Course(11, "testCourse", "This is a test course");
	}
	
	public User createUserTestSuite(){
		return new User(11, "testName", "testSurname", "testAddress", "testEmail", "testPassword");
	}
	
	public Student createStudentTestSuite () throws ParseException {
		Course c = createCourseTestSuite();
		List<Course> courses = new ArrayList<>();
		courses.add(c);
		Date d =  new SimpleDateFormat("dd/MM/yyyy").parse("01/01/0101");
		return new Student(11, "testName", "testSurname", "testAddress", "testEmail", "testPassword", d, courses);
	}
	
	public Professor createProfessorTestSuite () {
		Course c = createCourseTestSuite();
		List<Course> courses = new ArrayList<>();
		courses.add(c);
		return new Professor(11, "testName", "testSurname", "testAddress", "testEmail", "testPassword", courses);
	}
	
	public Room createRoomTestSuite () {
		return new Room(11, "testName", 11);
	}
	
	public Notification createNotificationTestSuite() throws ParseException {
		Date d =  new SimpleDateFormat("dd/MM/yyyy").parse("01/01/0101");
		return new Notification(11, "this is a test description", d);
	}

	public NotificationProfessor createNotificationProfessorTestSuite() throws ParseException {
		Date d =  new SimpleDateFormat("dd/MM/yyyy").parse("01/01/0101");
		Professor professor = createProfessorTestSuite();
		return new NotificationProfessor(11, "this is a test description", d, professor);
	}
	
	public NotificationStudent createNotificationStudentTestSuite() throws ParseException {
		Date d =  new SimpleDateFormat("dd/MM/yyyy").parse("01/01/0101");
		Student student = createStudentTestSuite();
		return new NotificationStudent(11, "this is a test description", d, student);
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
		assertTrue("course id is wrong", c.getCourseId()==11);
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
		assertEquals("course name is wrong", c.getName(), "testCourse");
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
		assertEquals("course description is wrong", c.getDescriptions(), "This is a test course");
	}
	
	@Test
	void testSetCourseDescription() {
		String description = "testDescription";
		Course c = null;
		c = createCourseTestSuite();
		c.setDescriptions(description);
		assertNotNull("course description is null", c.getDescriptions());
		assertEquals("course description is wrong", c.getDescriptions(), description);
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
		assertEquals("user name is wrong",c.getName(), "testName");
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
		assertEquals("user surname is wrong",u.getSurname(), "testSurname");
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
		assertEquals("user address is wrong",u.getAddress(), "testAddress");
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
		assertEquals("user email is wrong",u.getEmail(), "testEmail");
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
		assertEquals("user's password is wrong", u.getPassword(), "testPassword");
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
		assertEquals("student's course list is wrong",s.getCourses().size(), 1);

	}
	
	@Test
	void testSetStudentCourses() throws ParseException {
		Course c = new Course(0, "courseNew", "This is a new course");
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
		assertEquals("professor's course list is wrong", p.getCourses().size(), 1);

	}
	
	@Test
	void testSetProfessorCourses() {
		Course c = new Course(0, "courseNew", "This is a new course");
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
		room.setClassId(id);
		assertNotNull("room id is null", room.getRoomId());
		assertEquals("room id is wrong", room.getRoomId(), id);
	}
	
	@Test
	void testGetRoomName() {
		Room room = null;
		room = createRoomTestSuite();
		assertEquals("room name is wrong", room.getName(), "testName");
	}
	
	@Test
	void testSetRoomName() {
		String name = "testNameNew";
		Room room = null;
		room = createRoomTestSuite();
		room.setRoom(name);
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
		assertEquals("notification description is wrong", notification.getDescription(), "this is a test description");
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
		Course c = new Course(0, "testCoursenew", "This is a new test course");
		List<Course> courses = new ArrayList<>();
		courses.add(c);
		Professor professor = new Professor(0, "testNamenew", "testSurnamenew", "testAddressnew", "testEmailnew", "testPasswordnew", courses);
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
		Course c = new Course(0, "testCoursenew", "This is a new test course");
		List<Course> courses = new ArrayList<>();
		courses.add(c);
		Date d =  new SimpleDateFormat("dd/MM/yyyy").parse("02/02/0202");
		Student student = new Student(0, "testNamenew", "testSurnamenew", "testAddressnew", "testEmailnew", "testPasswordnew", d, courses);
		NotificationStudent notification = null;
		notification = createNotificationStudentTestSuite();
		notification.setStudent(student);
		assertEquals("Professor is not the one expected", notification.getStudent(), student);
	}
}
