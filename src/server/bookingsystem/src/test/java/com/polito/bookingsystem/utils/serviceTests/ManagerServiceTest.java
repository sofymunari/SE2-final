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
import com.polito.bookingsystem.entity.Manager;
import com.polito.bookingsystem.repository.ManagerRepository;
import com.polito.bookingsystem.service.impl.ManagerServiceImpl;

@RunWith(SpringRunner.class)
@DataJpaTest
class ManagerServiceTest {
	
	@Autowired
	private ManagerRepository managerRepository;
	
	private ManagerServiceImpl managerServiceImpl;

	@BeforeEach
	void setUp() throws Exception {
		
		managerRepository = mock(ManagerRepository.class);
		managerServiceImpl = new ManagerServiceImpl(managerRepository);
	}

	@Test
	void testGetManager1() {
		//passing an invalid mail 
		when(managerRepository.findByEmail(anyString())).thenReturn(null);
		assertNull("Expected a null value returned, invalid mail", managerServiceImpl.getManager("wrong@email.com"));
	}
	
	@Test
	void testGetManager2()  {
		//passing a valid mail 
		
		Manager manager = new Manager(1, "testName", "testSurname", "testAddress", "test@email.com", "testPassword");
		
		when(managerRepository.findByEmail(anyString())).thenReturn(manager);

		assertTrue("The manager returned is not the expected one", managerServiceImpl.getManager("test@email.com").getUserId() == 1);
	}
	
	@Test
	void testGetManager3() {
		//passing a null mail 
		
		when(managerRepository.findByEmail(anyString())).thenReturn(null);
		
		assertNull("Expected a null value to be returned", managerServiceImpl.getManager(null));
	}
	
	@Test
	void testLoginManager1() {
		//passing an invalid mail 
		when(managerRepository.findByEmail(anyString())).thenReturn(null);
		assertNull("Expected a null value to be returned, invalid email", managerServiceImpl.login("wrong@email.com", "fakePassword"));
	}
	
	@Test
	void testLoginManager2() throws ParseException {
		//passing a valid mail but an invalid password

		Manager manager = new Manager(1, "testName", "testSurname", "testAddress", "test@email.com", "testPassword");
		
		when(managerRepository.findByEmail(anyString())).thenReturn(manager);


		assertNull("Expected a null value to be returned, invalid password", managerServiceImpl.login("test@email.com", "wrongPassword"));
	}
	
	@Test
	void testLoginManager3() throws ParseException {
		//passing a valid mail and a valid password

		Manager manager = new Manager(1, "testName", "testSurname", "testAddress", "test@email.com", "testPassword");
		
		when(managerRepository.findByEmail(anyString())).thenReturn(manager);



		assertEquals("Expected test@email.com to be returned, successful login", "test@email.com", managerServiceImpl.login("test@email.com", "testPassword"));
	}
	
	@Test
	void testLoginManager4() {
		//passing a null mail and a null password
		
		when(managerRepository.findByEmail(anyString())).thenReturn(null);
		
		assertNull("Expected a null value returned, invalid email and password", managerServiceImpl.login(null, null));
	}
}
