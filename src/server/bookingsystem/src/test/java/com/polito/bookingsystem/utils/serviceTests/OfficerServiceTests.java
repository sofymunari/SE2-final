package com.polito.bookingsystem.utils.serviceTests;
import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import java.text.ParseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.polito.bookingsystem.entity.Officer;
import com.polito.bookingsystem.repository.OfficerRepository;
import com.polito.bookingsystem.service.impl.OfficerServiceImpl;

@RunWith(SpringRunner.class)
@DataJpaTest
class OfficerServiceTests {
	
	@Autowired
	private OfficerRepository officerRepository;
	
	private OfficerServiceImpl officerServiceImpl;

	@BeforeEach
	void setUp() throws Exception {
		
		officerRepository = mock(OfficerRepository.class);
		officerServiceImpl = new OfficerServiceImpl(officerRepository);
	}

	@Test
	void testGetOfficer1() {
		//passing an invalid mail 
		when(officerRepository.findByEmail(anyString())).thenReturn(null);
		assertNull("Expected a null value returned, invalid mail", officerServiceImpl.getOfficer("wrong@email.com"));
	}
	
	@Test
	void testGetOfficer2()  {
		//passing a valid mail 
		
		Officer officer = new Officer(1, "testName", "testSurname", "testAddress", "test@email.com", "testPassword");
		
		when(officerRepository.findByEmail(anyString())).thenReturn(officer);

		assertTrue("The manager returned is not the expected one", officerServiceImpl.getOfficer("test@email.com").getUserId() == 1);
	}
	
	@Test
	void testGetOfficer3() {
		//passing a null mail 
		
		when(officerRepository.findByEmail(anyString())).thenReturn(null);
		
		assertNull("Expected a null value to be returned", officerServiceImpl.getOfficer(null));
	}
	
	@Test
	void testLoginOfficer1() {
		//passing an invalid mail 
		when(officerRepository.findByEmail(anyString())).thenReturn(null);
		assertNull("Expected a null value to be returned, invalid email", officerServiceImpl.login("wrong@email.com", "fakePassword"));
	}
	
	@Test
	void testLoginOfficer2() throws ParseException {
		//passing a valid mail but an invalid password

		Officer officer = new Officer(1, "testName", "testSurname", "testAddress", "test@email.com", "testPassword");
		
		when(officerRepository.findByEmail(anyString())).thenReturn(officer);


		assertNull("Expected a null value to be returned, invalid password", officerServiceImpl.login("test@email.com", "wrongPassword"));
	}
	
	@Test
	void testLoginOfficer3() throws ParseException {
		//passing a valid mail and a valid password

		Officer officer = new Officer(1, "testName", "testSurname", "testAddress", "test@email.com", "testPassword");
		
		when(officerRepository.findByEmail(anyString())).thenReturn(officer);



		assertEquals("Expected test@email.com to be returned, successful login", "test@email.com",officerServiceImpl.login("test@email.com", "testPassword"));
	}
	
	@Test
	void testLoginOfficer4() {
		//passing a null mail and a null password
		
		when(officerRepository.findByEmail(anyString())).thenReturn(null);
		
		assertNull("Expected a null value returned, invalid email and password", officerServiceImpl.login(null, null));
	}
}
