package com.polito.bookingsystem.controller;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.polito.bookingsystem.dto.BookingDto;
import com.polito.bookingsystem.dto.LectureDto;
import com.polito.bookingsystem.dto.ManagerDto;
import com.polito.bookingsystem.dto.OfficerDto;
import com.polito.bookingsystem.dto.ProfessorDto;
import com.polito.bookingsystem.dto.StudentDto;
import com.polito.bookingsystem.entity.Course;
import com.polito.bookingsystem.entity.Room;
import com.polito.bookingsystem.service.BookingService;
import com.polito.bookingsystem.service.CourseService;
import com.polito.bookingsystem.service.FileStorageService;
import com.polito.bookingsystem.service.LectureService;
import com.polito.bookingsystem.service.ManagerService;
import com.polito.bookingsystem.service.NotificationProfessorService;
import com.polito.bookingsystem.service.NotificationStudentService;
import com.polito.bookingsystem.service.OfficerService;
import com.polito.bookingsystem.service.ProfessorService;
import com.polito.bookingsystem.service.RoomService;
import com.polito.bookingsystem.service.StudentService;
import com.polito.bookingsystem.utils.BookingEntry;
import com.polito.bookingsystem.utils.BookingInfo;
import com.polito.bookingsystem.utils.Schedule;
import com.polito.bookingsystem.utils.UploadFileResponse;

@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*", maxAge= 3000)
@RestController
public class HomeController {
	private static final String PASSWORD = "password";
	private static final String USERNAME = "username";
	private static final String PATH_UPLOADS = "../../uploads";
	private static final String PATH_UPLOADS2 = "../../uploads/";
	@Autowired
	StudentService studentService;
	
	@Autowired
	BookingService bookingService;
	
	@Autowired
	RoomService roomService;
	
	@Autowired
	CourseService courseService;
	
	@Autowired
	LectureService lectureService;
	
	@Autowired
	ProfessorService professorService;
	
	@Autowired
	NotificationStudentService notificationStudentService;
	
	@Autowired
	ManagerService managerService;
	
	@Autowired
	OfficerService officerService;
	
	 @Autowired
	 private FileStorageService fileStorageService;
	 
	@Autowired
	NotificationProfessorService notificationProfessorService;
	
	
	@GetMapping(value="studentinfo/{email}")
	public StudentDto getStudentInfo(@PathVariable String email) {
        return studentService.getStudent(email);
	}
	
	@GetMapping(value="studentbookings/{email}")
	public List<BookingDto> getStudentBookings(@PathVariable String email) {
        return bookingService.getListBooking(email);
	}
	
	@GetMapping(value="studentlectures/{email}")
	public List<LectureDto> getStudentLectures(@PathVariable String email) {
		List<BookingDto> bookingList=bookingService.getListBooking(email);
		List<Integer> ids=bookingList.stream().map(b->b.getLectureDto().getLectureId()).collect(Collectors.toList());
		return lectureService.getListLectures(email).stream().filter(l->{
			for(Integer id: ids) {
				if(l.getLectureId().equals(id)) {
					return false;
				}
			}
			return true;
		})
		.filter(l -> !l.getDeleted())
		.collect(Collectors.toList());
	}
	
	@PostMapping(value = "addbooking", headers="Content-Type=application/json")
    @ResponseBody
	public BookingDto addBooking(@RequestBody Map<String, String> lectureId) {
		 Integer lectId=Integer.parseInt(lectureId.get("lectureId"));
         return bookingService.addBooking(lectId, lectureId.get("email"));
	}
	
	
	@DeleteMapping(value="deletebooking/{bookingId}")
	public Boolean deleteStudentBookings(@PathVariable String bookingId) {
		Integer bookingid=Integer.parseInt(bookingId);
        return bookingService.deleteBooking(bookingid);
	}
	
	
	@PostMapping(value= "studentlogin")
	public String login(@RequestBody Map<String, String> userPass) {
			String email= studentService.login(userPass.get(USERNAME), userPass.get(PASSWORD));
			if(email!=null) {
				return email;
			}
			return "";
	}
	
	@PostMapping(value= "professorlogin")
	public String loginProf(@RequestBody Map<String, String> userPass) {
			String email= professorService.login(userPass.get(USERNAME), userPass.get(PASSWORD));
			if(email!=null) {
				return email;
			}
			return "";
	}
	
	@PostMapping(value= "managerlogin")
	public String loginManager(@RequestBody Map<String, String> userPass) {
		String email = managerService.login(userPass.get(USERNAME), userPass.get(PASSWORD));
		
		if(email != null) return email;
		
		return "";
	}
	
	
	@PostMapping(value = "supportOfficerlogin")
	public String officerLogin(@RequestBody Map<String,String> userPass) {
		String email = officerService.login(userPass.get(USERNAME), userPass.get(PASSWORD));
		
		if(email == null) return "";
		
		return email;
	}
	
	@PutMapping(value = "lecture/toremote/{id}")
	public boolean lectureToRemote(@PathVariable Integer id) {
		LectureDto lectureDto = lectureService.getLectureById(id);
		
		if(lectureDto == null)
			return false;
		
		Date date = new Date();  
		
		long diffInMillies = lectureDto.getDate().getTime() - date.getTime();
		long diffInMinutes = TimeUnit.MINUTES.convert(diffInMillies,TimeUnit.MILLISECONDS);
		if(diffInMinutes>30) {
			lectureDto.setRemotly(true);
			lectureService.save(lectureDto);
			List<BookingDto> bookingDtos = bookingService.getBookingsByLecture(lectureDto);
			for (BookingDto bookingDto : bookingDtos) {
				if(bookingDto.getBookingInfo()==BookingInfo.CANCELED_BY_STUD) {
					continue;
				}
				bookingDto.setBookingInfo(BookingInfo.MOVED_AS_REMOTLY);
				bookingService.save(bookingDto);
			}
			return true;
		}
		return false;
	} 


	
	
	@GetMapping(value= "professorbookings/{email}")
	public List<BookingEntry> getBooking(@PathVariable String email) {
			return bookingService.getBooking(email);
	}
	
	@GetMapping(value="professorinfo/{email}")
	public ProfessorDto getProfessorInfo(@PathVariable String email) {
        return professorService.getProfessor(email);
	}
	
	@GetMapping(value="professorlectures/{email}")
	public List<LectureDto> getProfessorLectures(@PathVariable String email){
		return lectureService.getProfessorLectures(email);
	}
	
	@GetMapping(value="listbookings")
	public List<BookingDto> getAllBookings() {
        return bookingService.getListAllBookings();
	}
	
	@GetMapping(value="listlectures")
	public List<LectureDto> getAllLectures() {
        return lectureService.getListAllLectures();
	}
	
	@GetMapping(value = "listcourses")
	public List<Course> getCourses(){
		return courseService.getCourses();
	}
	
	@DeleteMapping(value="professor/deletelecture/{lectureId}")
	public Boolean deleteLecture(@PathVariable Integer lectureId) {
        return lectureService.deleteLecture(lectureId);
	}
	
	
	@GetMapping(value = "managerinfo/{email}")
	public ManagerDto getManagerInfo(@PathVariable String email) {
		return managerService.getManager(email);
	}
	

	@PostMapping(value = "lecture/{lectureId}/attendance")
	public Boolean attendance(@RequestBody Map<String, List<String>> studentsList, @PathVariable Integer lectureId) {
		List<String> students = studentsList.get("studentIds");
		BookingDto bookingDto;
		
		for(String email : students) {
			bookingDto = bookingService.getByLectureAndStudent(lectureId, email);
			if(bookingDto == null) return false;
			
			
			bookingDto.setBookingInfo(BookingInfo.ATTENDED);
			bookingService.save(bookingDto);
		}
		
		return true;
	}
		
	
	@GetMapping(value = "officerinfo/{email}")
	public OfficerDto getOfficerInfo(@PathVariable String email) {
		return officerService.getOfficer(email);
	}
	
	

	@PostMapping(value = "/uploadStudents", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public UploadFileResponse uploadStudents(@RequestParam("file") MultipartFile file) {
        String fileName = fileStorageService.storeFile(file);
        studentService.addStudents(PATH_UPLOADS2 + fileName);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
        		.path(PATH_UPLOADS)
                .path(fileName)
                .toUriString();

        return new UploadFileResponse(fileName, fileDownloadUri,
                file.getContentType(), file.getSize());
    }
	
	@PostMapping(value = "/uploadProfessors", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public UploadFileResponse uploadProfessors(@RequestParam("file") MultipartFile file) {
        String fileName = fileStorageService.storeFile(file);
        professorService.addProfessors(PATH_UPLOADS2 +fileName);
               
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
        		.path(PATH_UPLOADS)
                .path(fileName)
                .toUriString();

        return new UploadFileResponse(fileName, fileDownloadUri,
                file.getContentType(), file.getSize());
    }
	

	@PostMapping(value = "/uploadEnrollments", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public UploadFileResponse uploadEnrollments(@RequestParam("file") MultipartFile file) {
        String fileName = fileStorageService.storeFile(file);
        studentService.addClasses(PATH_UPLOADS2 +fileName);
        
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
        		.path(PATH_UPLOADS)
                .path(fileName)
                .toUriString();

        return new UploadFileResponse(fileName, fileDownloadUri,
                file.getContentType(), file.getSize());
    }
	
	@PostMapping(value = "/uploadLectures", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public UploadFileResponse uploadLectures(@RequestParam("file") MultipartFile file) {
        String fileName = fileStorageService.storeFile(file);
        lectureService.addLectures(PATH_UPLOADS2 +fileName);
        
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
        		.path(PATH_UPLOADS)
                .path(fileName)
                .toUriString();

        return new UploadFileResponse(fileName, fileDownloadUri,
                file.getContentType(), file.getSize());
    }
	
	@PostMapping(value = "/uploadCourses", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public UploadFileResponse uploadCourses(@RequestParam("file") MultipartFile file) {
        String fileName = fileStorageService.storeFile(file);
        courseService.addCourses(PATH_UPLOADS2 + fileName);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(PATH_UPLOADS)
                .path(fileName)
                .toUriString();

        return new UploadFileResponse(fileName, fileDownloadUri,
                file.getContentType(), file.getSize());
    }
	
	@PostMapping(value = "/sendCourse", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	  public void courseToRemote(@RequestBody Map<String, String> course) {
		courseService.setCourseToRemote(course.get("course"));
	  }
	
	@PostMapping(value = "/sendCourses", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	  public void coursesToRemote(@RequestBody Map<String,List<String>> courses) {
    	
		for (String courseCode : courses.get("courses")) {
			courseService.setCourseToRemote(courseCode);
		}
	  } 
    

    @PostMapping(value = "/uploadHolidays", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public UploadFileResponse uploadHolidays(@RequestParam("file") MultipartFile file) {
    	String fileName = fileStorageService.storeFile(file);
    	
    	lectureService.removeHolidays(PATH_UPLOADS2 + fileName);
    	
    	String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                 .path(PATH_UPLOADS)
                 .path(fileName)
                 .toUriString();

         return new UploadFileResponse(fileName, fileDownloadUri,
                 file.getContentType(), file.getSize());
    }

    
    @GetMapping(value = "getScheduleCourse/{code}")
	public List<Schedule> getScheduleCourse(@PathVariable String code) {
    	return lectureService.getScheduleCourses(code);
	}
    
    @GetMapping(value = "getRooms")
	public List<Room> getRooms() {
		return lectureService.getRooms();
	}
    
    @PostMapping(value = "modifySchedule/{courseCode}/{scheduleId}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void modifySchedule(@RequestBody Map<String, String> scheduleInfo, @PathVariable String courseCode,  @PathVariable Integer scheduleId) {
    	String day = scheduleInfo.get("day");
		String duration = scheduleInfo.get("duration");
		String timeStart = scheduleInfo.get("timeStart");
		String roomId = scheduleInfo.get("roomId");
		lectureService.modifySchedule(day, Integer.parseInt(duration), timeStart, Integer.parseInt(roomId), courseCode, scheduleId);
	}

}
