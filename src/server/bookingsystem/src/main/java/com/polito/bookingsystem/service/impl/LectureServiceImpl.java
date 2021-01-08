package com.polito.bookingsystem.service.impl;
import com.polito.bookingsystem.converter.LectureConverter;
import com.polito.bookingsystem.converter.ProfessorConverter;
import com.polito.bookingsystem.converter.StudentConverter;
import com.polito.bookingsystem.dto.LectureDto;
import com.polito.bookingsystem.entity.Booking;
import com.polito.bookingsystem.entity.Course;
import com.polito.bookingsystem.entity.Holiday;
import com.polito.bookingsystem.entity.Lecture;
import com.polito.bookingsystem.entity.Professor;
import com.polito.bookingsystem.entity.Room;
import com.polito.bookingsystem.entity.Student;
import com.polito.bookingsystem.repository.BookingRepository;
import com.polito.bookingsystem.repository.CourseRepository;
import com.polito.bookingsystem.repository.LectureRepository;
import com.polito.bookingsystem.repository.ProfessorRepository;
import com.polito.bookingsystem.repository.RoomRepository;
import com.polito.bookingsystem.repository.StudentRepository;
import com.polito.bookingsystem.repository.HolidayRepository;
import com.polito.bookingsystem.service.BookingService;
import com.polito.bookingsystem.service.LectureService;
import com.polito.bookingsystem.service.ProfessorService;
import com.polito.bookingsystem.service.StudentService;
import com.polito.bookingsystem.utils.BookingInfo;
import com.polito.bookingsystem.utils.Schedule;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LectureServiceImpl implements LectureService {
	private static final Long MILLIHOUR = 3600000L;
	
	@Autowired
	private LectureRepository lectureRepository;
	
	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private ProfessorRepository professorRepository;
	
	@Autowired
	private BookingRepository bookingRepository;
	
	@Autowired
	private RoomRepository roomRepository;
	
	@Autowired
	private CourseRepository courseRepository;
	
	@Autowired
	private StudentService studentService;
	
	@Autowired
	private ProfessorService professorService;
	
	@Autowired
	private HolidayRepository holidayRepository;
	
	@Autowired
	private BookingService bookingService;
	
	
	private List<Schedule> scheduleCourses;
	
	
	@Autowired
	public LectureServiceImpl(LectureRepository lectureRepository, StudentRepository studentRepository, BookingRepository bookingRepository, StudentService studentService, ProfessorRepository professorRepository,CourseRepository courseRepository,RoomRepository roomRepository,HolidayRepository holidayRepository,ProfessorService professorService,BookingService bookingService, List<Schedule> scheduleCourses )
	{
		this.studentService = studentService;
		this.lectureRepository = lectureRepository;
		this.studentRepository = studentRepository;
		this.bookingRepository = bookingRepository;
		this.professorRepository = professorRepository;
		this.courseRepository = courseRepository;
		this.roomRepository= roomRepository;
		this.holidayRepository=holidayRepository;
		this.professorService=professorService;
		if(scheduleCourses == null)
			this.scheduleCourses = new ArrayList<>();
		else
			this.scheduleCourses = scheduleCourses;
		this.bookingService = bookingService;
	}


	@Override
	public List<LectureDto> getListLectures(String email) {
		Student student = studentRepository.findByEmail(email);
		
		if(student == null)
			return new ArrayList<>();
		
		List<Course> courses = student.getCourses();
		if(courses.isEmpty())
			return new ArrayList<>();
		
		List<Lecture> allLectures = lectureRepository.findAll();
		List<LectureDto> studentLectures = new ArrayList<>();
		
		Date today = new Date();
		
		for(Course course : courses) {
		   List<Lecture> courseLectures = allLectures.stream()
				                    .filter(l -> course.getCourseId().equals(l.getCourse().getCourseId()))
				                    .filter(l -> l.getDate().after(today))
				                    .collect(Collectors.toList());
		   
	       for(Lecture lecture : courseLectures) {
	    	   studentLectures.add(LectureConverter.toDto(lecture));
	       }
		}
		
		
		//Sort the lecture by date
		studentLectures.sort(Comparator.comparing(LectureDto::getDate));
		
		return studentLectures;
	}

	@Override
	public List<LectureDto> getProfessorLectures(String email) {
		List<LectureDto> lecturesDto = new ArrayList<>();
		
		if(email == null)
			return new ArrayList<>();
		
		Professor professor = professorRepository.findByEmail(email);
		if(professor == null)
			return new ArrayList<>();
		
		List<Lecture> lectures = lectureRepository.findByProfessor(professor);
		
		//Sort the lecture by date
		lectures.sort(Comparator.comparing(Lecture::getDate));
		
		for(Lecture lecture : lectures) 
			lecturesDto.add(LectureConverter.toDto(lecture));
		
		return lecturesDto;
	}


	@Override
	public boolean deleteLecture(Integer lectureId) {
		
		if(lectureId == null || lectureId<0) 
			return false;
		
		Boolean result = false;
		
		Lecture lecture = lectureRepository.findByLectureId(lectureId);
		if(lecture == null) {
			return result;
		}
		
		Long timeNowMilli = System.currentTimeMillis();
		Long diff = (lecture.getDate().getTime() - timeNowMilli);
		
		if(diff < MILLIHOUR) {
			return result;
		}
		
		result = true;
		lecture.setDeleted(result);
		List<Booking> bookingsDelete = bookingRepository.findAll().stream()  
				                      .filter(b -> b.getLecture().getLectureId().equals(lectureId))
				                      .collect(Collectors.toList());
		
		for(Booking booking:bookingsDelete) {
			booking.setLecture(lecture);
			booking.setBookingInfo(BookingInfo.CANCELED_BY_PROF);
			Student student = booking.getStudent();
			String date = new SimpleDateFormat("dd/MM/yyyy").format(lecture.getDate());
			String subject = "Lecture " + lecture.getNumberOfLesson() + "-" + lecture.getCourse().getName() + " is cancelled";
			String text = "Dear student,\n"+ "The lecture " + lecture.getNumberOfLesson() + " of course " 
			               + lecture.getCourse().getName() + " in date " + date 
					       + " is cancelled!\nBest regard";
			studentService.sendEmail(StudentConverter.toDto(student), subject, text);
			bookingRepository.save(booking);
		}
		lectureRepository.save(lecture);
		return result;
	}
	
	
	@Override
	public LectureDto getLectureById(Integer id) {
		if(id == null || id < 0)
			return null;
		return LectureConverter.toDto(lectureRepository.findByLectureId(id));
	}


	@Override
	public void save(LectureDto lectureDto) {
		lectureRepository.save(LectureConverter.toEntity(lectureDto));
		
	}
	
	@Override
	public List<LectureDto> getListAllLectures(){
		List<Lecture> allLectures = lectureRepository.findAll();
        List<LectureDto> allLecturesDto = new ArrayList<>();
        
        //Sort the lecture by date
        allLectures.sort(Comparator.comparing(Lecture::getDate));
        
        for(Lecture lecture:allLectures) {
        	allLecturesDto.add(LectureConverter.toDto(lecture));
        }
		return allLecturesDto;
	}


	@Override
	public void addLectures(String fileName){
		try (BufferedReader reader = new BufferedReader(new FileReader(fileName))){
			 Calendar startSemester = Calendar.getInstance();
			 startSemester.set(2020, 8, 28);
			 Calendar endSemester = Calendar.getInstance();
			 endSemester.set(2021, 0, 17);
			 Calendar calendar = Calendar.getInstance();
			 int idSchedule = 0;
			 String currentLine = reader.readLine(); //read first line
			 while((currentLine = reader.readLine()) != null){
				  Schedule schedule = new Schedule();
				  schedule.setId(idSchedule);
				  idSchedule++;
				  String[] fields = currentLine.split(",");
				  calendar = Calendar.getInstance();
				  if(calendar.before(startSemester)) {
					  calendar = startSemester;
				  }
				  calendar = getFirstDate(calendar, fields[2]);
				  schedule.setDay(fields[2]);
				  if(calendar != null) {
					  calendar.set(Calendar.HOUR, 0);
					  calendar.set(Calendar.MINUTE, 0);
					  calendar.set(Calendar.SECOND, 0);
					  calendar.set(Calendar.MILLISECOND, 0);
					  
					  //create lessons for this schedule
					  Course course = courseRepository.findByCode(fields[0]);
					  
					  if(course != null)
					  {
						  schedule.setCourse(course);
						  Room room = roomRepository.findByName(fields[1]);
						  if(room == null){
							  room = null;
							  room = new Room();
							  Integer roomId = roomRepository.findAll().stream()
			                              .mapToInt(l -> l.getRoomId())
			                              .max()
						                  .orElse(0);
							  
							  room.setRoomId(roomId+1);
							  room.setName(fields[1]);
							  room.setNumberOfSeat(Integer.parseInt(fields[3]));
							  roomRepository.save(room);
						  }
						  schedule.setRoom(room);
						  List<Professor>  professors = professorRepository.findAll().stream()
								                       .filter(p -> 
												               {
																  List<Course> courseProf = p.getCourses().stream()
																		  .filter(c -> c.getCode().compareTo(fields[0]) == 0)
																		  .collect(Collectors.toList());
																  return !courseProf.isEmpty();
												               })
								                       .collect(Collectors.toList());
						  
						  if(!professors.isEmpty()) {
							  Professor professor = professors.get(0);
							  try {
								  String[] t = fields[4].split("-");
								  String[] timestamp = new String[3];
								  if(t.length == 2) {
									  timestamp[0] = t[0];
									  timestamp[1] = t[1];
								  }else if(t.length == 1){
									  String[] t2 = fields[4].split(":");
									  timestamp[0] = t2[0] + ":" + t2[1];
									  timestamp[1] = t2[2] + ":" + t2[3];
								  }
								  String[] timeS = timestamp[0].split(":");
								  SimpleDateFormat df = new SimpleDateFormat("hh:mm");
								  try {
									  schedule.setTimeStart(timestamp[0]);
									  Date timeStart = df.parse(timestamp[0]);
									  Date timeEnd = df.parse(timestamp[1]);
									  Long duration = ( timeEnd.getTime() - timeStart.getTime())/(1000*60);
									  schedule.setDuration(duration.intValue());
									  scheduleCourses.add(schedule);
									  calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(timeS[0]));
									  calendar.set(Calendar.MINUTE, Integer.parseInt(timeS[1]));
									  while(calendar.before(endSemester)){
										  Integer lectureId = lectureRepository.findAll().stream()
		                                               .mapToInt(Lecture::getLectureId)
		                                               .max()
									                   .orElse(0);
										  
										  Integer numberOfLesson = lectureRepository.findAll().stream()
												                   .filter(l -> l.getCourse().getCode().compareTo(fields[0]) == 0)
												                   .mapToInt(Lecture::getNumberOfLesson)
												                   .max()
												                   .orElse(0);
										  
										  Lecture newLecture = new Lecture();
										  newLecture.setLectureId(lectureId+1);
										  newLecture.setNumberOfLesson(numberOfLesson + 1); 
										  newLecture.setDeleted(false);
										  newLecture.setDuration(duration.intValue());
										  newLecture.setCourse(course);
										  newLecture.setBookedSeats(0);
										  newLecture.setDate(calendar.getTime());
										  newLecture.setProfessor(professor);
										  newLecture.setRemotly(false);
										  newLecture.setProgramDetails("");
										  newLecture.setRoom(room);
										  lectureRepository.save(newLecture);
									      calendar.add(Calendar.DATE, 7);
									 }
							    }catch(ParseException e) {
							    	System.err.println(e.getMessage());
							    }
						  }catch(Exception e) {
						    	System.err.println(e.getMessage());
						  }
					    }
			       }
			    }
			 }
		}catch(IOException e) {
			System.err.println(e.getMessage());
		}
	}
	
	
	public Calendar getFirstDate(Calendar calendar, String day) {
		
	   switch(day) {
	   case "Mon":
		   calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		   break;
	   case "Tue":
		   calendar.set(Calendar.DAY_OF_WEEK, Calendar.TUESDAY);
		   break;
	   case "Wed":
		   calendar.set(Calendar.DAY_OF_WEEK, Calendar.WEDNESDAY);
		   
		   break;
	   case "Thu":
		   calendar.set(Calendar.DAY_OF_WEEK, Calendar.THURSDAY);
		   
		   break;
	   case "Fri":
		   calendar.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
		  
		   break;
	   case "Sat":
		   calendar.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
		   
		   break;
	    default:
		   calendar = null;
	   }
	   return calendar;
	}

	
	//given two dates return true if same day (only day, no time)
	private boolean dateEquals(Date first, Date second) {
		SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");
		return fmt.format(first).equals(fmt.format(second));
	}
	

	@Override
	public void removeHolidays(String fileName) {
		try (BufferedReader reader = new BufferedReader(new FileReader(fileName))){

			List<Lecture> lectures = lectureRepository.findAll();
			
			 String currentLine = reader.readLine(); //read first line (header)
			 while((currentLine = reader.readLine()) != null){
				 try {
					Date date = new SimpleDateFormat("yyyy-MM-dd").parse(currentLine);
					
					//saving the new holiday on database
					Holiday holiday = new Holiday(date);
					holidayRepository.save(holiday);
					
					for(Lecture lecture : lectures) {
						
						if(dateEquals(lecture.getDate(), date)) {	
							
							//get bookings for the deleted lecture and delete them
							List<Booking> bookings = bookingRepository.findByLecture(lecture);
							for(Booking booking : bookings) { 
								bookingService.deleteBookingOfficer(booking.getBookingId());
								
								//send email to booked students to inform lecture has been deleted
								String dateEmail = new SimpleDateFormat("dd/MM/yyyy").format(date);
								String subject = "Lecture " + lecture.getNumberOfLesson() + "-" + lecture.getCourse().getName() + " cancellation";
								String text = "Dear student,\n"+ "The lecture " + lecture.getNumberOfLesson() + " of course " 
								               + lecture.getCourse().getName() + " in date " + dateEmail 
										       + " has been cancelled since the university will be closed that day.\nBest regard";
								 //commenta solo durante demo (o con mail preimpostate)
								//studentService.sendEmail(StudentConverter.toDto(booking.getStudent()), subject, text);
							}
							lecture.setDeleted(true);
							lectureRepository.save(lecture);
							//lectureRepository.deleteById(lecture.getLectureId()); //delete lecture 
							
							//send email to teacher to inform lecture has been deleted
							String dateEmail = new SimpleDateFormat("dd/MM/yyyy").format(date);
							String subject = "Lecture " + lecture.getNumberOfLesson() + "-" + lecture.getCourse().getName() + " cancellation";
							String text = "Dear professor,\n"+ "The lecture " + lecture.getNumberOfLesson() + " of course " 
							               + lecture.getCourse().getName() + " in date " + dateEmail 
									       + " has been cancelled since the university will be closed that day.\nBest regard";
							 //commenta solo durante demo (o con mail preimpostate)
							//professorService.sendEmail(ProfessorConverter.toDto(lecture.getProfessor()), subject, text);
							
							
							
						} 
					}
				 } 
				 catch (ParseException e) {
					e.printStackTrace();
				 }
		
			 }
		}catch(IOException e) {
			System.err.println(e.getMessage());
		}
	}
		
	@Override
	public List<Schedule> getScheduleCourses(String codeCourse){
		return scheduleCourses
				.stream()
                .filter(s -> s.getCourse().getCode().compareTo(codeCourse)==0)
                .collect(Collectors.toList());
	}
	
	@Override
	public List<Room> getRooms(){
		return roomRepository.findAll();
	}
	
	@Override
	public Boolean modifySchedule(String day, Integer duration, String timeStart, Integer roomId, String codeCourse, Integer scheduleId) {
		Room room = roomRepository.findByRoomId(roomId);
		if(room == null)
			return false;
		Course course = courseRepository.findByCode(codeCourse);
		if(course == null)
			return false;
		
		List<Professor>  professors = professorRepository.findAll().stream()
                .filter(p -> 
			               {
							  List<Course> courseProf = p.getCourses().stream()
									  .filter(c -> c.getCode().compareTo(codeCourse) == 0)
									  .collect(Collectors.toList());
							  return !courseProf.isEmpty();
			               })
                .collect(Collectors.toList());
		if(professors.isEmpty())
			return false;
		
		Professor professor = professors.get(0);
		
		Schedule scheduleCourse = scheduleCourses.stream().filter(s -> s.getId().equals(scheduleId)).collect(Collectors.toList()).get(0);
		
		Calendar startSemester = Calendar.getInstance();
		startSemester.set(2020, 8, 28);
		
		Calendar endSemester = Calendar.getInstance();
		endSemester.set(2021, 0, 17);
		
		Calendar calendar = Calendar.getInstance();
		Calendar calendar2 = Calendar.getInstance();
		if(calendar.before(startSemester)) {
			 calendar = startSemester;
			 calendar2 = startSemester;
		}
		
		List<Date> oldScheduleDays = new ArrayList<>();
		List<Date> newScheduleDays = new ArrayList<>();
		
		calendar = getFirstDate(calendar, scheduleCourse.getDay());
		calendar2 = getFirstDate(calendar2, day);
		
		String [] time = scheduleCourse.getTimeStart().split(":");
		//list of day with old schedule
		while(calendar.before(endSemester)){
			calendar.set(Calendar.HOUR, 0);
		    calendar.set(Calendar.MINUTE, 0);
		    calendar.set(Calendar.SECOND, 0);
		    calendar.set(Calendar.MILLISECOND, 0);
		    
		    calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(time[0]));
		    calendar.set(Calendar.MINUTE, Integer.parseInt(time[1]));
		    
		    oldScheduleDays.add(calendar.getTime());
			calendar.add(Calendar.DATE, 7);	
		}
		
		String [] time2 = timeStart.split(":");
		//list of day with new schedule
		while(calendar2.before(endSemester)){
			calendar2.set(Calendar.HOUR, 0);
			calendar2.set(Calendar.MINUTE, 0);
			calendar2.set(Calendar.SECOND, 0);
			calendar2.set(Calendar.MILLISECOND, 0);
			
		    calendar2.set(Calendar.HOUR_OF_DAY, Integer.parseInt(time2[0]));
		    calendar2.set(Calendar.MINUTE, Integer.parseInt(time2[1]));
			newScheduleDays.add(calendar2.getTime());
			calendar2.add(Calendar.DATE, 7);
		}
		
	
		
		//delete lecture with old schedule and associated bookings
		List<Lecture> deletedLecture = lectureRepository.findAll().stream()
														.filter(l -> l.getCourse().getCode().compareTo(codeCourse)==0)
			                                            .filter(l ->{
			                                            	Calendar c = Calendar.getInstance();
			                                            	c.setTime(l.getDate());
			                                    			int i=0;
			                                    			for(i=0; i<oldScheduleDays.size();i++) {
			                                    				Date ca = oldScheduleDays.get(i);
			                                    				if(ca.equals(c.getTime()))
			                                    					return true;
			                                    			}
			                                    			return false;
			                                            })
			                                            .collect(Collectors.toList());
		for(Lecture l:deletedLecture) {
			List<Booking> deletedBooking = bookingRepository.findAll().stream()
					                       .filter(b -> b.getLecture().getLectureId().equals(l.getLectureId()))
					                       .collect(Collectors.toList());
			for(Booking b:deletedBooking) {
				bookingService.deleteBookingOfficer(b.getBookingId());
			}
			
			Lecture le = lectureRepository.findByLectureId(l.getLectureId());
			le.setDeleted(true);
			lectureRepository.save(le);
		}
		//add new lectures according schedule
		for(Date d: newScheduleDays) {
			  Integer lectureId = lectureRepository.findAll().stream()
	                .mapToInt(Lecture::getLectureId)
	                .max()
	                .orElse(0);
			  
			  Integer numberOfLesson = lectureRepository.findAll().stream()
					                   .filter(l -> l.getCourse().getCode().compareTo(codeCourse) == 0)
					                   .mapToInt(Lecture::getNumberOfLesson)
					                   .max()
					                   .orElse(0);
			  
			  Lecture newLecture = new Lecture();
			  newLecture.setLectureId(lectureId+1);
			  newLecture.setNumberOfLesson(numberOfLesson + 1); 
			  newLecture.setDeleted(false);
			  newLecture.setDuration(duration.intValue());
			  newLecture.setCourse(course);
			  newLecture.setBookedSeats(0);
			  
			  Calendar cc = Calendar.getInstance();
			  cc.setTime(d);
			  newLecture.setDate(cc.getTime());
			  
			  newLecture.setProfessor(professor);
			  newLecture.setRemotly(false);
			  newLecture.setProgramDetails("");
			  newLecture.setRoom(room);
			  lectureRepository.save(newLecture);
		}
		
		//inform all student of course
		List<Student> studentCourse = studentRepository.findAll().stream()
				                                       .filter(s -> s.getCourses().contains(course))
				                                       .collect(Collectors.toList());
		for(Student s: studentCourse) {
			String subject = "Schedule modification of" + course.getName() + " is changed";
			String text = "Dear student,\n"+ "The schedule of course " + course.getName() + " is changed." 
			               + " LAST SCHEDULE: " + scheduleCourse.getDay() + "," 
					                            + scheduleCourse.getTimeStart() + 
					                            " duration (minutes):" + scheduleCourse.getDuration() 
					                            + " room: " + scheduleCourse.getRoom().getName()
					       + "\n NEW SCHEDULE: " + day + "," 
					                            + timeStart + 
					                            " duration (minutes):" + duration
					                            + " room: " + room.getName()
					       +"\n\n\n Also all your booking is cancelled. Remember to booked if you are interested at some lessons of course!";
			try {
			  //commenta solo durante demo (o con mail preimpostate)
			  //studentService.sendEmail(StudentConverter.toDto(s), subject, text);
			}catch(Exception e) {
				System.out.print(e.getMessage());
			}
		}
		//modify schedule
		scheduleCourse.setDay(day);
		scheduleCourse.setDuration(duration);
		scheduleCourse.setTimeStart(timeStart);
		scheduleCourse.setRoom(room);
		return true;
	}
}
