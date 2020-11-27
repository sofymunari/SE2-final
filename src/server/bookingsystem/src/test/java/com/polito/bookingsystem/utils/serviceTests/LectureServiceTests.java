package com.polito.bookingsystem.utils.serviceTests;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.polito.bookingsystem.entity.Course;
import com.polito.bookingsystem.entity.Lecture;
import com.polito.bookingsystem.entity.Professor;
import com.polito.bookingsystem.entity.Room;
import com.polito.bookingsystem.entity.Student;
import com.polito.bookingsystem.repository.BookingRepository;
import com.polito.bookingsystem.repository.LectureRepository;
import com.polito.bookingsystem.repository.StudentRepository;
import com.polito.bookingsystem.service.impl.LectureServiceImpl;

@RunWith(SpringRunner.class)
@DataJpaTest
class LectureServiceTests {

	@Autowired
	private LectureRepository lectureRepository;
	@Autowired
	private StudentRepository studentRepository;
	@Autowired
	private BookingRepository bookingRepository;
	
	private LectureServiceImpl lectureServiceImpl;
	
	@BeforeEach
	public void setUp() throws Exception {

		studentRepository = mock(StudentRepository.class);
		lectureRepository = mock(LectureRepository.class);
		lectureServiceImpl = new LectureServiceImpl(lectureRepository, studentRepository, bookingRepository);
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
		Date date = new SimpleDateFormat("dd/MM/yyyy").parse("01/01/0101");
		
		Course course1 = new Course(1, "testName1", "testDescription1");
		Course course2 = new Course(2, "testName2", "testDescription2");
		Course course3 = new Course(3, "testName3", "testDescription3");
		List<Course> courses1 = new ArrayList<>();
		courses1.add(course1);
		courses1.add(course2);

		List<Course> courses2 = new ArrayList<>();
		courses2.add(course1);
		courses2.add(course2);
		courses2.add(course3);
		
		Student student1 = new Student(1, "testName", "testSurname", "testAddress", "test@email.com", "testPassword", date, courses1, "testMatricola");
		Professor professor1 = new Professor(1, "testName", "testSurname", "testAddress", "testProfessor@email.com", "testPassword",courses2);

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
		
		when(lectureRepository.findByLectureId(anyInt())).thenReturn(null);

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
		
		Course course1 = new Course(1, "testName1", "testDescription1");
		Course course2 = new Course(2, "testName2", "testDescription2");
		Course course3 = new Course(3, "testName3", "testDescription3");

		List<Course> courses2 = new ArrayList<>();
		courses2.add(course1);
		courses2.add(course2);
		courses2.add(course3);
		
		Professor professor1 = new Professor(1, "testName", "testSurname", "testAddress", "testProfessor@email.com", "testPassword",courses2);

		Lecture lecture1 = new Lecture(1, 10, course1, professor1, true, date, 90, "testDetails", room1);
		
		when(lectureRepository.findByLectureId(anyInt())).thenReturn(lecture1);
		
		assertFalse("Expected false to be returned, lesson in less than an hour", lectureServiceImpl.deleteLecture(1));

	}
	
	@Test
	void testDeleteLecture5() throws ParseException {
		//lecture can be deleted
		
		Room room1 = new Room(1, "testName", 100);
		Date date = new SimpleDateFormat("dd-MM-yy-HH.mm.ss").parse("20-05-2021-12.00.00");
		
		Course course1 = new Course(1, "testName1", "testDescription1");
		Course course2 = new Course(2, "testName2", "testDescription2");
		Course course3 = new Course(3, "testName3", "testDescription3");

		List<Course> courses2 = new ArrayList<>();
		courses2.add(course1);
		courses2.add(course2);
		courses2.add(course3);
		
		Professor professor1 = new Professor(1, "testName", "testSurname", "testAddress", "testProfessor@email.com", "testPassword",courses2);

		Lecture lecture1 = new Lecture(1, 10, course1, professor1, true, date, 90, "testDetails", room1);
		
		when(lectureRepository.findByLectureId(anyInt())).thenReturn(lecture1);
		when(bookingRepository.findAll()).thenReturn(null);
		when(lectureRepository.save(anyObject())).thenReturn(null);
		
		assertTrue("Expected true to be returned, lesson should be cancelled", lectureServiceImpl.deleteLecture(1));

	}
	
	
	

}