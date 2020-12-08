package com.polito.bookingsystem.utils.serviceTests;
import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.File;
import java.nio.file.Paths;
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
import org.springframework.mock.web.MockMultipartFile;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;

import org.springframework.test.context.junit4.SpringRunner;



import com.polito.bookingsystem.utils.FileStorageProperties;
//import com.polito.bookingsystem.repository.LectureRepository;
//import com.polito.bookingsystem.repository.OfficerRepository;
//import com.polito.bookingsystem.repository.ProfessorRepository;
import com.polito.bookingsystem.service.impl.FileStorageServiceImpl;
//import com.polito.bookingsystem.service.impl.OfficerServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@RunWith(SpringRunner.class)
@DataJpaTest
public class FileStorageServiceTest {
	
	private FileStorageProperties fileStorageProperties;
	
	private FileStorageServiceImpl fileStorageServiceImpl;

	@BeforeEach
	void setUp() throws Exception {
		fileStorageProperties = new FileStorageProperties();
		//when(Paths.get(fileStorageProperties.getUploadDir())).thenReturn(value)
		fileStorageProperties.setUploadDir("../../downloads/StudentsList");
		fileStorageServiceImpl = new FileStorageServiceImpl(fileStorageProperties);
		
	}
	
	@Test
	void testStoreFile1 (){
		//invalid file name
		String fileName = "/../test.txt";
	     File file = new File("../../downloads/StudentsList" + fileName);
	      //delete if exits
	      file.delete();
	      
		MockMultipartFile mockMultipartFile = new MockMultipartFile("user-file",fileName,
	              "text/plain", "test data".getBytes());
		
		//when(StringUtils.cleanPath(mockMultipartFile.getOriginalFilename())).thenReturn("../");
		assertNull("Expected a null value. Invalid file name passed",fileStorageServiceImpl.storeFile(mockMultipartFile));
		
		
	}

	

}
