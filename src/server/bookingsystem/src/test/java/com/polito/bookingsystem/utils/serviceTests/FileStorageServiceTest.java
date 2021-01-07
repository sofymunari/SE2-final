package com.polito.bookingsystem.utils.serviceTests;
import static org.junit.Assert.*;
import java.io.File;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;

import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.core.io.UrlResource;
import org.springframework.mock.web.MockMultipartFile;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;

import org.springframework.test.context.junit4.SpringRunner;
import com.polito.bookingsystem.utils.FileStorageProperties;
import com.polito.bookingsystem.service.impl.FileStorageServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;

@RunWith(SpringRunner.class)
@DataJpaTest
class FileStorageServiceTest {
	
	private FileStorageProperties fileStorageProperties;
	
	private FileStorageServiceImpl fileStorageServiceImpl;

	@BeforeEach
	void setUp() {
		fileStorageProperties = new FileStorageProperties();
		
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
	
	@Test
	void testStoreFile2 (){
		//invalid file name
		String fileName = "test.txt";
	     File file = new File("../../downloads/StudentsList" + fileName);
	      //delete if exits
	      file.delete();
	      
		MockMultipartFile mockMultipartFile = new MockMultipartFile("user-file",fileName,
	              "text/plain", "test data".getBytes());
		
		//when(StringUtils.cleanPath(mockMultipartFile.getOriginalFilename())).thenReturn("../");
		assertEquals("Expected a null value. Invalid file name passed","test.txt",fileStorageServiceImpl.storeFile(mockMultipartFile));
		
		
	}
	
	
	@Test
	void testStoreFile3() {
	    // given
		
		String fileName = null;
	     File file = new File("../../downloads/StudentsList" + fileName);
	      //delete if exits
	      file.delete();
	      
		MockMultipartFile mockMultipartFile = new MockMultipartFile("user-file",fileName,
	              "text/plain", "test data".getBytes());
		
			assertEquals("Expected empty string","",fileStorageServiceImpl.storeFile(mockMultipartFile));
			
	    
	}
	
	@Test
	void testStoreFile4() {
	    // given
		
		String fileName = null;
	     File file = new File("../../downloads/StudentsList" + fileName);
	      //delete if exits
	      file.delete();
	      
		MockMultipartFile mockMultipartFile = new MockMultipartFile("user-file",fileName,
	              "text/plain", "test data".getBytes());
		
			assertEquals("Expected empty string","",fileStorageServiceImpl.storeFile(mockMultipartFile));
			
	    
	}
	
	
	@Test
	void testLoadFileAsResource1() throws Exception {
		//exception thrown because of null file
		String fileName = null;
		
		assertEquals("Expected null value", null, fileStorageServiceImpl.loadFileAsResource(fileName));
		
	}
	
	@Test
	void testLoadFileAsResource2() throws Exception {
		//file doesn't exist
		String fileName = "wrongFileTest.txt";
		
		assertEquals("Expected null value", null,fileStorageServiceImpl.loadFileAsResource(fileName));
		
	}
	
	@Test
	void testLoadFileAsResource3() throws Exception {
		//file name is correct
		String fileName = "test.txt";
		
		assertNotNull("Expected null value", fileStorageServiceImpl.loadFileAsResource(fileName));
		
	}
	
	

	

}
