package com.polito.bookingsystem;

import static org.junit.jupiter.api.Assertions.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import java.text.ParseException;
import com.polito.bookingsystem.converter.*;
import com.polito.bookingsystem.dto.*;
import com.polito.bookingsystem.entity.*;
import com.polito.bookingsystem.utils.BookingInfo;


class BookingSystemConverterTests {

	
	/*
	 * 
	 USER_CONVERTER
	 * 
	 */
	
	
	@Test
	void testToUserDto1() {
		User user = null;
		UserDto userDto = null;
		
		userDto = UserConverter.toDto(user);
		
		assertNull(userDto);
	}
	
	@Test
	void testToUserDto2() {
		User user = new User(1, "testName", "testSurname", "testAddress", "testEmail", "testPassword");
		UserDto userDto = null;
		
		userDto = UserConverter.toDto(user);
		
		assertEquals(user.getUserId(), userDto.getUserId());
		assertEquals(user.getName(), userDto.getName());
		assertEquals(user.getSurname(), userDto.getSurname());
		assertEquals(user.getAddress(), userDto.getAddress());
		assertEquals(user.getEmail(), userDto.getEmail());
		assertEquals(user.getPassword(), userDto.getPassword());
	}
	
	@Test
	void testToUser1() {
		User user = null;
		UserDto userDto = null;
		
		user = UserConverter.toEntity(userDto);
		
		assertNull(user);
	}
	
	@Test
	void testToUser2() {
		UserDto userDto = new UserDto(1, "testName", "testSurname", "testAddress", "testEmail", "testPassword");
		User user = null;
		
		user = UserConverter.toEntity(userDto);
		
		assertEquals(user.getUserId(), userDto.getUserId());
		assertEquals(user.getName(), userDto.getName());
		assertEquals(user.getSurname(), userDto.getSurname());
		assertEquals(user.getAddress(), userDto.getAddress());
		assertEquals(user.getEmail(), userDto.getEmail());
		assertEquals(user.getPassword(), userDto.getPassword());
	}
	
	
	/*
	 * 
	 STUDENT_CONVERTER
	 * 
	 */
	
	
	@Test
	void testToStudentDto1() {
		Student student = null;
		StudentDto studentDto = null;
		
		studentDto = StudentConverter.toDto(student);
		
		assertNull(studentDto);
	}
	
	@Test
	void testToStudentDto2() throws ParseException {
		Course c = new Course(11, "testCourse", "This is a test course");
		List<Course> courses = new ArrayList<>();
		courses.add(c);
		Date date = new SimpleDateFormat("dd/MM/yyyy").parse("01/01/0101");
		Student student = new Student(1, "testName", "testSurname", "testAddress", "testEmail", "testPassword", date, courses);
		StudentDto studentDto = null;
		
		studentDto = StudentConverter.toDto(student);
		
		assertEquals(student.getUserId(), studentDto.getUserId());
		assertEquals(student.getName(), studentDto.getName());
		assertEquals(student.getSurname(), studentDto.getSurname());
		assertEquals(student.getAddress(), studentDto.getAddress());
		assertEquals(student.getEmail(), studentDto.getEmail());
		assertEquals(student.getPassword(), studentDto.getPassword());
		assertEquals(student.getDateOfBirth(), studentDto.getDateOfBirth());
		assertEquals(student.getCourses().size(), studentDto.getCoursesDto().size());

	}
	
	@Test
	void testToStudent1() {
		Student student = null;
		StudentDto studentDto = null;
		
		student = StudentConverter.toEntity(studentDto);
		
		assertNull(student);
	}
	
	@Test
	void testToStudent2() throws ParseException {
		CourseDto c = new CourseDto(11, "testCourse", "This is a test course");
		List<CourseDto> courses = new ArrayList<>();
		courses.add(c);
		Date date = new SimpleDateFormat("dd/MM/yyyy").parse("01/01/0101");
		Student student = null;
		StudentDto studentDto = new StudentDto(1, "testName", "testSurname", "testAddress", "testEmail", "testPassword", date, courses);
		
		student = StudentConverter.toEntity(studentDto);
		
		assertEquals(student.getUserId(), studentDto.getUserId());
		assertEquals(student.getName(), studentDto.getName());
		assertEquals(student.getSurname(), studentDto.getSurname());
		assertEquals(student.getAddress(), studentDto.getAddress());
		assertEquals(student.getEmail(), studentDto.getEmail());
		assertEquals(student.getPassword(), studentDto.getPassword());
		assertEquals(student.getDateOfBirth(), studentDto.getDateOfBirth());
		assertEquals(student.getCourses().size(), studentDto.getCoursesDto().size());
	
	}
	

	/*
	 * 
	 ROOM_CONVERTER
	 * 
	 */
	
	
	@Test
	void testToRoomDto1() {
		Room student = null;
		RoomDto studentDto = null;
		
		studentDto = RoomConverter.toDto(student);
		
		assertNull(studentDto);
	}
	
	@Test
	void testToRoomDto2(){
		Room room = new Room(1, "testName", 100);
		RoomDto roomDto = null;
		
		roomDto = RoomConverter.toDto(room);
		
		assertEquals(room.getRoomId(), roomDto.getRoomId());
		assertEquals(room.getName(), roomDto.getName());
		assertEquals(room.getNumberOfSeat(), roomDto.getNumberOfSeat());
	}
	
	@Test
	void testToRoom1() {
		Room room = null;
		RoomDto roomDto = null;
		
		room = RoomConverter.toEntity(roomDto);
		
		assertNull(room);
	}
	
	@Test
	void testToRoom2(){
		Room room = null;
		RoomDto roomDto = new RoomDto(1, "testName",100);
		
		room = RoomConverter.toEntity(roomDto);
		
		assertEquals(room.getRoomId(), roomDto.getRoomId());
		assertEquals(room.getName(), roomDto.getName());
		assertEquals(room.getNumberOfSeat(), roomDto.getNumberOfSeat());
		
	}
	
	
	/*
	 * 
	 PROFESSOR_CONVERTER
	 * 
	 */
	
	
	@Test
	void testToProfessorDto1() {
		Professor professor = null;
		ProfessorDto professorDto = null;
		
		professorDto = ProfessorConverter.toDto(professor);
		
		assertNull(professorDto);
	}
	
	@Test
	void testToProfessorDto2()  {
		Course c = new Course(11, "testCourse", "This is a test course");
		List<Course> courses = new ArrayList<>();
		courses.add(c);
		Professor professor = new Professor(1, "testName", "testSurname", "testAddress", "testEmail", "testPassword", courses);
		ProfessorDto professorDto = null;
		
		professorDto = ProfessorConverter.toDto(professor);
		
		assertEquals(professor.getUserId(), professorDto.getUserId());
		assertEquals(professor.getName(), professorDto.getName());
		assertEquals(professor.getSurname(), professorDto.getSurname());
		assertEquals(professor.getAddress(), professorDto.getAddress());
		assertEquals(professor.getEmail(), professorDto.getEmail());
		assertEquals(professor.getPassword(), professorDto.getPassword());
		assertEquals(professor.getCourses().size(), professorDto.getCoursesDto().size());

	}
	
	@Test
	void testToProfessor1()  {
		Professor professor = null;
		ProfessorDto professorDto = null;
		
		professor = ProfessorConverter.toEntity(professorDto);
		
		assertNull(professor);
	}
	
	@Test
	void testToProfessor2() {
		CourseDto c = new CourseDto(11, "testCourse", "This is a test course");
		List<CourseDto> courses = new ArrayList<>();
		courses.add(c);
		Professor professor = null;
		ProfessorDto professorDto = new ProfessorDto(1, "testName", "testSurname", "testAddress", "testEmail", "testPassword", courses);
		
		professor = ProfessorConverter.toEntity(professorDto);
		
		assertEquals(professor.getUserId(), professorDto.getUserId());
		assertEquals(professor.getName(), professorDto.getName());
		assertEquals(professor.getSurname(), professorDto.getSurname());
		assertEquals(professor.getAddress(), professorDto.getAddress());
		assertEquals(professor.getEmail(), professorDto.getEmail());
		assertEquals(professor.getPassword(), professorDto.getPassword());
		assertEquals(professor.getCourses().size(), professorDto.getCoursesDto().size());
	
	}

	
	/*
	 * 
	 OFFICER_CONVERTER
	 * 
	 */
	
	
	@Test
	void testToOfficerDto1() {
		Officer officer = null;
		OfficerDto officerDto = null;
		
		officerDto = OfficerConverter.toDto(officer);
		
		assertNull(officerDto);
	}
	
	@Test
	void testToOfficerDto2()  {
		Officer officer = new Officer(1, "testName", "testSurname", "testAddress", "testEmail", "testPassword");
		OfficerDto officerDto = null;
		
		officerDto = OfficerConverter.toDto(officer);
		
		assertEquals(officer.getUserId(), officerDto.getUserId());
		assertEquals(officer.getName(), officerDto.getName());
		assertEquals(officer.getSurname(), officerDto.getSurname());
		assertEquals(officer.getAddress(), officerDto.getAddress());
		assertEquals(officer.getEmail(), officerDto.getEmail());
		assertEquals(officer.getPassword(), officerDto.getPassword());

	}
	
	@Test
	void testToOfficer1()  {
		Officer officer = null;
		OfficerDto officerDto = null;
		
		officer = OfficerConverter.toEntity(officerDto);
		
		assertNull(officer);
	}
	
	@Test
	void testToOfficer2()  {
		Officer officer = null;
		OfficerDto officerDto = new OfficerDto(1, "testName", "testSurname", "testAddress", "testEmail", "testPassword");
		
		officer = OfficerConverter.toEntity(officerDto);
		
		assertEquals(officer.getUserId(), officerDto.getUserId());
		assertEquals(officer.getName(), officerDto.getName());
		assertEquals(officer.getSurname(), officerDto.getSurname());
		assertEquals(officer.getAddress(), officerDto.getAddress());
		assertEquals(officer.getEmail(), officerDto.getEmail());
		assertEquals(officer.getPassword(), officerDto.getPassword());
	
	}
	
	
	/*
	 * 
	 NOTIFICATION_CONVERTER
	 * 
	 */
	
	
	@Test
	void testToNotificatioDto1() {
		Notification notification = null;
		NotificationDto notificationDto = null;
		
		notificationDto = NotificationConverter.toDto(notification);
		
		assertNull(notificationDto);
	}
	
	@Test
	void testToNotificationDto2() throws ParseException {
		Date date =  new SimpleDateFormat("dd/MM/yyyy").parse("01/01/0101");
		Notification notification = new Notification(1, "testDescription", date,false,"link");
		NotificationDto notificationDto = null;
		
		notificationDto = NotificationConverter.toDto(notification);
		
		assertEquals(notification.getNotificationId(), notificationDto.getNotificationId());
		assertEquals(notification.getDescription(), notificationDto.getDescription());
		assertEquals(notification.getDate(), notificationDto.getDate());
		assertEquals(notification.getLink(), notificationDto.getLink());
		assertEquals(notification.getStatus(), notificationDto.getStatus());
	

	}
	
	@Test
	void testToNotification1()  {
		Notification notification = null;
		NotificationDto notificationDto = null;
	
		
		notification = NotificationConverter.toEntity(notificationDto);
		
		assertNull(notification);
	}
	
	@Test
	void testToNotification2() throws ParseException {
		Date date =  new SimpleDateFormat("dd/MM/yyyy").parse("01/01/0101");
		Notification notification = null;
		NotificationDto notificationDto = new NotificationDto(1, "testDescription", date,false,"link");
		
		notification = NotificationConverter.toEntity(notificationDto);
		
		assertEquals(notification.getNotificationId(), notificationDto.getNotificationId());
		assertEquals(notification.getDescription(), notificationDto.getDescription());
		assertEquals(notification.getDate(), notificationDto.getDate());
		assertEquals(notification.getLink(), notificationDto.getLink());
		assertEquals(notification.getStatus(), notificationDto.getStatus());	
	}
	
	/*
	 * 
	 NOTIFICATIONPROFESSOR_CONVERTER
	 * 
	 */
	
	
	@Test
	void testToNotificatioProfessorDto1() {
		NotificationProfessor notificationProfessor = null;
		NotificationProfessorDto notificationProfessorDto = null;
		
		notificationProfessorDto = NotificationProfessorConverter.toDto(notificationProfessor);
		
		assertNull(notificationProfessorDto);
	}
	
	@Test
	void testToNotificationProfessorDto2() throws ParseException {
		Course c = new Course(11, "testCourse", "This is a test course");
		List<Course> courses = new ArrayList<>();
		courses.add(c);
		Professor professor = new Professor(1, "testName", "testSurname", "testAddress", "testEmail", "testPassword", courses);
		Date date =  new SimpleDateFormat("dd/MM/yyyy").parse("01/01/0101");
		NotificationProfessor notificationProfessor = new NotificationProfessor(1, "testDescription", date, professor, false, "link");
		NotificationProfessorDto notificationProfessorDto = null;
		
		notificationProfessorDto = NotificationProfessorConverter.toDto(notificationProfessor);
		
		assertEquals(notificationProfessor.getNotificationId(), notificationProfessorDto.getNotificationId());
		assertEquals(notificationProfessor.getDescription(), notificationProfessorDto.getDescription());
		assertEquals(notificationProfessor.getDate(), notificationProfessorDto.getDate());
		assertEquals(notificationProfessor.getLink(), notificationProfessorDto.getLink());
		assertEquals(notificationProfessor.getStatus(), notificationProfessorDto.getStatus());
	

	}
	
	@Test
	void testToNotificationProfessor1()  {
		NotificationProfessor notificationProfessor = null;
		NotificationProfessorDto notificationProfessorDto = null;

		notificationProfessor = NotificationProfessorConverter.toEntity(notificationProfessorDto);
		
		assertNull(notificationProfessor);
	}
	
	@Test
	void testToNotificationProfessor2() throws ParseException {
		CourseDto c = new CourseDto(11, "testCourse", "This is a test course");
		List<CourseDto> courses = new ArrayList<>();
		courses.add(c);

		ProfessorDto professorDto = new ProfessorDto(1, "testName", "testSurname", "testAddress", "testEmail", "testPassword", courses);
		Date date =  new SimpleDateFormat("dd/MM/yyyy").parse("01/01/0101");
		NotificationProfessor notificationProfessor = null;
		NotificationProfessorDto notificationProfessorDto = new NotificationProfessorDto(1, "testDescription", date, professorDto, false,"link");
		
		notificationProfessor = NotificationProfessorConverter.toEntity(notificationProfessorDto);
		
		assertEquals(notificationProfessor.getNotificationId(), notificationProfessorDto.getNotificationId());
		assertEquals(notificationProfessor.getDescription(), notificationProfessorDto.getDescription());
		assertEquals(notificationProfessor.getDate(), notificationProfessorDto.getDate());
		assertEquals(notificationProfessor.getLink(), notificationProfessorDto.getLink());
		assertEquals(notificationProfessor.getStatus(), notificationProfessorDto.getStatus());
	
	}
	
	/*
	 * 
	 NOTIFICATION_CONVERTER
	 * 
	 */
	
	
	@Test
	void testToNotificatioStudentDto1() {
		NotificationStudent notificationStudent = null;
		NotificationStudentDto notificationStudentDto = null;
		
		notificationStudentDto = NotificationStudentConverter.toDto(notificationStudent);
		
		assertNull(notificationStudentDto);
	}
	
	@Test
	void testToNotificationStudentDto2() throws ParseException {
		Date date =  new SimpleDateFormat("dd/MM/yyyy").parse("01/01/0101");
		Course c = new Course(11, "testCourse", "This is a test course");
		List<Course> courses = new ArrayList<>();
		courses.add(c);
		Student student = new Student(1, "testName", "testSurname", "testAddress", "testEmail", "testPassword", date, courses);
		
		NotificationStudent notificationStudent = new NotificationStudent(1, "testDescription", date, student, false,"link");
		NotificationStudentDto notificationStudentDto = null;
		
		notificationStudentDto = NotificationStudentConverter.toDto(notificationStudent);
		
		assertEquals(notificationStudent.getNotificationId(), notificationStudentDto.getNotificationId());
		assertEquals(notificationStudent.getDescription(), notificationStudentDto.getDescription());
		assertEquals(notificationStudent.getDate(), notificationStudentDto.getDate());
		assertEquals(notificationStudent.getLink(), notificationStudentDto.getLink());
		assertEquals(notificationStudent.getStatus(), notificationStudentDto.getStatus());
	

	}
	
	@Test
	void testToNotificationStudent1()  {
		NotificationStudent notificationStudent = null;
		NotificationStudentDto notificationStudentDto = null;
	
		
		notificationStudent = NotificationStudentConverter.toEntity(notificationStudentDto);
		
		assertNull(notificationStudent);
	}
	
	@Test
	void testToNotificationStudent2() throws ParseException {
		CourseDto c = new CourseDto(11, "testCourse", "This is a test course");
		List<CourseDto> courses = new ArrayList<>();
		courses.add(c);
		Date date = new SimpleDateFormat("dd/MM/yyyy").parse("01/01/0101");
		StudentDto studentDto = new StudentDto(1, "testName", "testSurname", "testAddress", "testEmail", "testPassword", date, courses);
		
		NotificationStudent notificationStudent = null;
		NotificationStudentDto notificationStudentDto = new NotificationStudentDto(1, "testDescription", date, studentDto, false,"link");
		
		notificationStudent = NotificationStudentConverter.toEntity(notificationStudentDto);
		
		assertEquals(notificationStudent.getNotificationId(), notificationStudentDto.getNotificationId());
		assertEquals(notificationStudent.getDescription(), notificationStudentDto.getDescription());
		assertEquals(notificationStudent.getDate(), notificationStudentDto.getDate());
		assertEquals(notificationStudent.getLink(), notificationStudentDto.getLink());
		assertEquals(notificationStudent.getStatus(), notificationStudentDto.getStatus());	
	}

	
	/*
	 * 
	 MANAGER_CONVERTER
	 * 
	 */
	
	
	@Test
	void testToManagerDto1() {
		Manager manager = null;
		ManagerDto managerDto = null;
		
		managerDto = ManagerConverter.toDto(manager);
		
		assertNull(managerDto);
	}
	
	@Test
	void testToManagerDto2()  {
		Manager manager = new Manager(1, "testName", "testSurname", "testAddress", "testEmail", "testPassword");
		ManagerDto managerDto = null;
		
		managerDto = ManagerConverter.toDto(manager);
		
		assertEquals(manager.getUserId(), managerDto.getUserId());
		assertEquals(manager.getName(), managerDto.getName());
		assertEquals(manager.getSurname(), managerDto.getSurname());
		assertEquals(manager.getAddress(), managerDto.getAddress());
		assertEquals(manager.getEmail(), managerDto.getEmail());
		assertEquals(manager.getPassword(), managerDto.getPassword());

	}
	
	@Test
	void testToManager1() {
		Manager manager = null;
		ManagerDto managerDto = null;
		
		manager = ManagerConverter.toEntity(managerDto);
		
		assertNull(manager);
	}
	
	@Test
	void testToManager2()  {
		Manager manager = null;
		ManagerDto managerDto = new ManagerDto(1, "testName", "testSurname", "testAddress", "testEmail", "testPassword");
		
		manager = ManagerConverter.toEntity(managerDto);
		
		assertEquals(manager.getUserId(), managerDto.getUserId());
		assertEquals(manager.getName(), managerDto.getName());
		assertEquals(manager.getSurname(), managerDto.getSurname());
		assertEquals(manager.getAddress(), managerDto.getAddress());
		assertEquals(manager.getEmail(), managerDto.getEmail());
		assertEquals(manager.getPassword(), managerDto.getPassword());	
	}
	
	/*
	 * 
	 LECTURE_CONVERTER
	 * 
	 */
	
	
	@Test
	void testToLectureDto1()  {
		Lecture lecture = null;
		LectureDto lectureDto = null;
		
		lectureDto = LectureConverter.toDto(lecture);
		
		assertNull(lectureDto);
	}
	
	
	@Test
	void testToLectureDto2() throws ParseException {
		Course course = new Course(11, "testCourse", "This is a test course");
		Date date = new SimpleDateFormat("dd/MM/yyyy").parse("01/01/0101");
		List<Course> courses = new ArrayList<>();
		courses.add(course);
		Professor professor = new Professor(1, "testName", "testSurname", "testAddress", "testEmail", "testPassword", courses);
		Room room = new Room(1, "testName", 100);
		
		Lecture lecture = new Lecture(1, 10, course, professor, true, date, 90, "testDetails", room);
		LectureDto lectureDto = null;
		
		
		lectureDto = LectureConverter.toDto(lecture);
		
		assertEquals(lecture.getLectureId(), lectureDto.getLectureId());
		assertEquals(lecture.getNumberOfLesson(), lectureDto.getNumberOfLesson());
		assertEquals(lecture.getCourse().getCourseId(), lectureDto.getCourseDto().getCourseId());
		assertEquals(lecture.getProfessor().getUserId(), lectureDto.getProfessorDto().getUserId());
		assertEquals(lecture.getRemotly(), lectureDto.getRemotly());
		assertEquals(lecture.getDate(), lectureDto.getDate());
		assertEquals(lecture.getDuration(),lectureDto.getDuration());
		assertEquals(lecture.getProgramDetails(), lectureDto.getProgramDetails());
		assertEquals(lecture.getRoom().getRoomId(), lectureDto.getRoomDto().getRoomId());
	}
	
	@Test
	void testToLecture1() {
		Lecture lecture = null;
		LectureDto lectureDto = null;
		
		lecture = LectureConverter.toEntity(lectureDto);
		
		assertNull(lecture);
	}
	
	@Test
	void testToLecture2() throws ParseException {
		CourseDto courseDto = new CourseDto(11, "testCourse", "This is a test course");
		Date date = new SimpleDateFormat("dd/MM/yyyy").parse("01/01/0101");
		List<CourseDto> courses = new ArrayList<>();
		courses.add(courseDto);
		ProfessorDto professorDto = new ProfessorDto(1, "testName", "testSurname", "testAddress", "testEmail", "testPassword", courses);
		RoomDto roomDto = new RoomDto(1, "testName", 100);
		
		LectureDto lectureDto = new LectureDto(1, 10, courseDto, professorDto, true, date, 90, "testDetails", roomDto);
		Lecture lecture = null;
		
		lecture = LectureConverter.toEntity(lectureDto);
		
		assertEquals(lecture.getLectureId(), lectureDto.getLectureId());
		assertEquals(lecture.getNumberOfLesson(), lectureDto.getNumberOfLesson());
		assertEquals(lecture.getCourse().getCourseId(), lectureDto.getCourseDto().getCourseId());
		assertEquals(lecture.getProfessor().getUserId(), lectureDto.getProfessorDto().getUserId());
		assertEquals(lecture.getRemotly(), lectureDto.getRemotly());
		assertEquals(lecture.getDate(), lectureDto.getDate());
		assertEquals(lecture.getDuration(),lectureDto.getDuration());
		assertEquals(lecture.getProgramDetails(), lectureDto.getProgramDetails());
		assertEquals(lecture.getRoom().getRoomId(), lectureDto.getRoomDto().getRoomId());
	
	}
	
	/*
	 * 
	 HOLIDAY_CONVERTER
	 * 
	 */
	
	
	@Test
	void testToHolidayDto1() {
		Holiday holiday = null;
		HolidayDto holidayDto = null;
		
		holidayDto = HolidayConverter.toDto(holiday);
		
		assertNull(holidayDto);
	}
	
	@Test
	void testToHolidayDto2() throws ParseException {
		Date date =  new SimpleDateFormat("dd/MM/yyyy").parse("01/01/0101");
		Holiday holiday = new Holiday(1, date);
		HolidayDto holidayDto = null;
		
		holidayDto = HolidayConverter.toDto(holiday);
		
		assertEquals(holiday.getHolidayId(), holidayDto.getHolidayId());
		assertEquals(holiday.getDate(), holidayDto.getDate());

	}
	
	@Test
	void testHoliday1() {
		Holiday holiday = null;
		HolidayDto holidayDto = null;
	
		
		holiday = HolidayConverter.toEntity(holidayDto);
		
		assertNull(holiday);
	}
	
	@Test
	void testToHoliday2() throws ParseException {
		Date date =  new SimpleDateFormat("dd/MM/yyyy").parse("01/01/0101");
		Holiday holiday = null;
		HolidayDto holidayDto = new HolidayDto(1, date);
		
		holiday = HolidayConverter.toEntity(holidayDto);
		
		assertEquals(holiday.getHolidayId(), holidayDto.getHolidayId());
		assertEquals(holiday.getDate(), holidayDto.getDate());
	
	}

	/*
	 * 
	 COURSE_CONVERTER
	 * 
	 */
	
	
	@Test
	void testToCourseDto1() {
		Course course = null;
		CourseDto courseDto = null;
		
		courseDto = CourseConverter.toDto(course);
		
		assertNull(courseDto);
	}
	
	@Test
	void testToCourseDto2() {
		Course course = new Course(1, "testName", "testDescription");
		CourseDto courseDto = null;
		
		courseDto = CourseConverter.toDto(course);
		
		assertEquals(course.getCourseId(), courseDto.getCourseId());
		assertEquals(course.getName(), courseDto.getName());
		assertEquals(course.getDescriptions(), courseDto.getDescriptions());
	}
	
	@Test
	void testCourse1() {
		Course course = null;
		CourseDto courseDto = null;
	
		
		course = CourseConverter.toEntity(courseDto);
		
		assertNull(course);
	}
	
	@Test
	void testToCourse2() {
		Course course = null;
		CourseDto courseDto = new CourseDto(1, "testName", "testDescription");
		
		course = CourseConverter.toEntity(courseDto);
		
		assertEquals(course.getCourseId(), courseDto.getCourseId());
		assertEquals(course.getName(), courseDto.getName());
		assertEquals(course.getDescriptions(), courseDto.getDescriptions());

	}
	
	/*
	 * 
	 BOOKING_CONVERTER
	 * 
	 */
	
	
	@Test
	void testToBookingDto1() {
		BookingDto bookingDto = null;
		Booking booking = null;
		
		
		bookingDto = BookingConverter.toDto(booking);
		
		assertNull(bookingDto);
	}
	
	@Test
	void testToBookingDto2() throws ParseException {
		Course course = new Course(11, "testCourse", "This is a test course");
		List<Course> courses = new ArrayList<>();
		courses.add(course);
		Date date = new SimpleDateFormat("dd/MM/yyyy").parse("01/01/0101");
		Student student = new Student(1, "testName", "testSurname", "testAddress", "testEmail", "testPassword", date, courses);
		BookingInfo bookingInfo = BookingInfo.ATTENDED;
		Professor professor = new Professor(1, "testName", "testSurname", "testAddress", "testEmail", "testPassword", courses);
		Room room = new Room(1, "testName", 100); 
		Lecture lecture = new Lecture(1, 10, course, professor, true, date, 90, "testDetails", room);
		
	
		BookingDto bookingDto = null;
		Booking booking = new Booking(1, student, lecture, bookingInfo);
		
		bookingDto = BookingConverter.toDto(booking);
		
		assertEquals(booking.getBookingId(), bookingDto.getBookingId());
		assertEquals(booking.getLecture().getLectureId(), bookingDto.getLectureDto().getLectureId());
		assertEquals(booking.getStudent().getUserId(), bookingDto.getStudentDto().getUserId());
		assertEquals(booking.getBookingInfo(), bookingDto.getBookingInfo());
}
	
	@Test
	void testBooking1() {
		BookingDto bookingDto = null;
		Booking booking = null;
	
		
		booking = BookingConverter.toEntity(bookingDto);
		
		assertNull(booking);
	}
	
	@Test
	void testToBooking2() throws ParseException {
		CourseDto courseDto = new CourseDto(11, "testCourse", "This is a test course");
		List<CourseDto> courses = new ArrayList<>();
		courses.add(courseDto);
		Date date = new SimpleDateFormat("dd/MM/yyyy").parse("01/01/0101");
		StudentDto studentDto = new StudentDto(1, "testName", "testSurname", "testAddress", "testEmail", "testPassword", date, courses);
		BookingInfo bookingInfo = BookingInfo.ATTENDED;
		ProfessorDto professorDto = new ProfessorDto(1, "testName", "testSurname", "testAddress", "testEmail", "testPassword", courses);
		RoomDto roomDto = new RoomDto(1, "testName", 100); 
		LectureDto lectureDto = new LectureDto(1, 10, courseDto, professorDto, true, date, 90, "testDetails", roomDto);
		
	
		Booking booking = null;
		BookingDto bookingDto = new BookingDto(1, studentDto, lectureDto, bookingInfo);
		
		booking = BookingConverter.toEntity(bookingDto);
		
		assertEquals(booking.getBookingId(), bookingDto.getBookingId());
		assertEquals(booking.getLecture().getLectureId(), bookingDto.getLectureDto().getLectureId());
		assertEquals(booking.getStudent().getUserId(), bookingDto.getStudentDto().getUserId());
		assertEquals(booking.getBookingInfo(), bookingDto.getBookingInfo());

	}

	

}
