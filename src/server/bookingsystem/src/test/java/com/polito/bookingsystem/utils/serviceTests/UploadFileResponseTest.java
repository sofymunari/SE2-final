package com.polito.bookingsystem.utils.serviceTests;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.polito.bookingsystem.repository.BookingRepository;
import com.polito.bookingsystem.repository.LectureRepository;
import com.polito.bookingsystem.repository.ProfessorRepository;
import com.polito.bookingsystem.repository.StudentRepository;
import com.polito.bookingsystem.service.StudentService;
import com.polito.bookingsystem.service.impl.LectureServiceImpl;
import com.polito.bookingsystem.utils.UploadFileResponse;

@RunWith(SpringRunner.class)
@DataJpaTest
class UploadFileResponseTest {
	
	 private UploadFileResponse uploadFileResponse;
	
	/*@BeforeEach
	public void setUp() throws Exception {

		String fileName="test.txt";
		String fileDownloadUri="./test/";
		String fileType="txt";
		long size= 2;
		uploadFileResponse = new UploadFileResponse(fileName,fileDownloadUri,fileType,size);

	}*/
	@ Test
	void testGetFileName1() {
		String fileName=null;
		String fileDownloadUri=null;
		String fileType=null;
		long size= -1;
		uploadFileResponse = new UploadFileResponse(fileName,fileDownloadUri,fileType,size);
		assertEquals("Expected null value",null, uploadFileResponse.getFileName());
		assertEquals("Expected null value",null, uploadFileResponse.getFileDownloadUri());
		assertEquals("Expected null value",null, uploadFileResponse.getFileType());
		assertEquals("Expected null value",-1, uploadFileResponse.getSize());
		
	}
	
	@Test
	void testGetFileName2() {
		String fileName="test.txt";
		String fileDownloadUri="./test/";
		String fileType="txt";
		long size= 2;
		uploadFileResponse = new UploadFileResponse(null,null,null,0);
		uploadFileResponse.setFileDownloadUri(fileDownloadUri);
		uploadFileResponse.setFileName(fileName);
		uploadFileResponse.setFileType(fileType);
		uploadFileResponse.setSize(size);
		assertEquals("Expected test.txt value", uploadFileResponse.getFileName(),fileName);
		assertEquals("Expected test.txt value", uploadFileResponse.getFileDownloadUri(),fileDownloadUri);
		assertEquals("Expected test.txt value", uploadFileResponse.getFileType(),fileType);
		assertEquals("Expected test.txt value", uploadFileResponse.getSize(),size);
		
	}


}
