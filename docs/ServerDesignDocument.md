# Design Document 
# Low level design
```plantuml
@startuml
   package "com.polito.officequeue"{
      package "entity"{
	 class  User{
           - Integer userId 
           - String name
           - String surname 
           - String address
           - String email
           - String password 
           - login()
           - logout()
         }
         class Student extends User{
            - Date dateOfBirth
            - List<Course> courses
            - methods()
         }
         class Professor extends User{
            - List<Course> courses
            - methods()
         }
         class Officer extends User{
            - methods()...
         }
          class Manager extends User{
            - methods()...
         }
         class Course{
            - Integer courseId
            - String name
            - String description
            - List<Professor> professors
            - List<Student> students
         }
         class Lecture{
            - Integer lectureId
            - Integer numberOfLesson
            - Course course
            - Professor professor
            - Boolean remotly
            - Date date
			- Integer duration
            - String programDetails
            - Room room
         }
         class Booking{
            - Integer bookingId
            - Student student
            - Lecture lecture
            - enum BookingInfo informationType
         }
         class Notification{
	       - Integer notificationId
	       - Date date
           - String description
	     }
	     class NotificationStudent extends Notification{
           - Student student	    
	     }
	     class NotificationProfessor extends Notification{
		   - Professor professor
	     }
         class Room{
          - Integer roomId
          - String name
          - Integer numberOfSeat
        }
   }

      package "controller"{
   
      }
      package "converter"{
         interface UserConverter{

         }
         interface StudentConverter extends UserConverter{

         }
         interface OfficerConverter extends UserConverter{

         }
         interface ManagerConverter extends UserConverter{

         }
         interface ProfessorConverter extends UserConverter{

         }
         interface LectureConverter {

         }
         interface ClassConverter {

         }
         
         interface CourseConverter{

         }
         interface BookingConverter{

         }
      }
      package "dto"{
         interface UserDto{

         }
         interface StudentDto extends UserDto{

         }
	 interface OfficerDto extends UserDto{

         }
         interface ManagerDto extends UserDto{

         } 
         interface ProfessorDto extends UserDto{

         }
         interface ClassDto extends UserDto{

         }
         interface LectureDto{

         }
         interface CourseDto{

         }
         interface BookingDto{

         }
      }
      package "repository"{
         interface UserRepository{

         }
         interface StudentRepository extends UserRepository{

         }
         interface OfficerRepository extends UserRepository{

         }
         interface RoomRepository{

         }
         interface ManagerRepository extends UserRepository{

         }
         interface ProfessorRepository extends UserRepository{

         }
         interface LectureRepository{

         }
         interface CourseRepository{

         }
         interface BookingRepository{

         }

      }
      package "service"{
         interface UserService{

         }
         interface StudentService extends UserService{

         }
         interface OfficerService extends UserService{

         }
         interface ManagerService extends UserService{

         }
         interface ProfessorService extends UserService{

         }
         interface RoomService{

         }
         interface LectureService{

         }
         interface CourseService{

         }
         interface BookingService{

         }
      }
      package "service.impl"{
         interface StudentServiceImp{

         }
         interface OfficerServiceImp{

         }
         interface ManagerServiceImp{

         }
         interface ProfessorServiceImp{

         }
         interface RoomServiceImp{

         }
         interface LectureServiceImp{
 
         }
         interface CourseServiceImp{

         }
         interface BookingServiceImp{

         }
      }
   }
@enduml
```


```plantuml
@startuml
   package DataBase{
   !define table(x) class x << (T,#FFAAAA) >>
   !define primary_key(x) <u>x</u>
   hide methods
   hide stereotypes
   table(STUDENT) {
      primary_key(USER_ID)
      NAME
      SURNAME
      ADDRESS
      DATE_OF_BIRTH
      EMAIL
      PASSWORD
   }
   table(PROFESSOR) {
      primary_key(USER_ID)
      NAME
      SURNAME
      ADDRESS
      EMAIL
      PASSWORD
   }
   table(OFFICER) {
      primary_key(USER_ID)
      NAME
      SURNAME
      ADDRESS
      EMAIL
      PASSWORD
   }
   table(MANAGER){
      primary_key(USER_ID)
      NAME
      SURNAME
      ADDRESS
      EMAIL
      PASSWORD
   }
   table(PROFESSOR_COURSE) {
      USER_ID
      COURSE_ID
   }
   table(STUDENT_COURSE) {
      COURSE_ID
      USER_ID
   }
   table(LECTURE) {
      primary_key(LECTURE_ID)
      NUMBER_OF_LESSON
      COURSE_ID
      USER_ID
      REMOTLY
      DATE
	  DURATION
      PROGRAM_DETAILS
      ROOM_ID
   }
   table(ROOM){
     primary_key(ROOM_ID)
     NUMBER_OF_SEAT
     NAME
   }
   table(COURSE){
       primary_key(COURSE_ID)
       NAME
       DESCRIPTION
   }
   table(BOOKING){
      primary_key(BOOKING_ID)
      USER_ID
      LECTURE_ID
      BOOKING_TYPE
   }
   table(HOLIDAY){
     primary_key(HOLIDAY_ID)
     DATE
   }
   table(NOTIFICATION_PROFESSOR){
     primary_key(NOTIFICATION_ID)
     USER_ID
     DATE
     DESCRIPTION
   }
   table(NOTIFICATION_STUDENT){
     primary_key(NOTIFICATION_ID)
     USER_ID
     DATE
     DESCRIPTION
   }

   PROFESSOR -- PROFESSOR_COURSE
   COURSE -- PROFESSOR_COURSE

   STUDENT -- STUDENT_COURSE
   COURSE -- STUDENT_COURSE
   ROOM "1" --"*" LECTURE
   PROFESSOR "1"--"*" LECTURE
   COURSE "1"--"*" LECTURE
   STUDENT "1" -- "*" BOOKING
   LECTURE "1" -- "*" BOOKING
   STUDENT "1" -- "*" NOTIFICATION_STUDENT
   PROFESSOR "1" -- "*" NOTIFICATION_PROFESSOR
}
@enduml
```