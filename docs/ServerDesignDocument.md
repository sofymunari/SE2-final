# Design Document 
# Low level design
```plantuml
@startuml
package "Backend" {
   package "com.polito.bookingsystem"{
      package "entity"{
	 class abstract "User"{
           - userId (String)
           - name (String)
           - surname (String)
           - address (String)
           - email (String)
           - password (String)
         }
         class "Student" extend "User"{
            - dateOfBirth (Date)
            - courses (List<Course>)
         }
         class "Professor" extends "User"{
            - courses (List<Course)
         }
         class "Officer" extends "User"{
            
         }
          class "Manager" extends "User"{
            
         }
         class "Course"{
            - courseId (String)
            - name (String)
            - description (String)
            - professors (List<Professor>)
            - students (List<Student>)
         }
         class "Lecture"{
            - lectureId (String)//L..
            - numberOfLesson (Integer)
            - courseId (String)
            - userId (String)
            - remotly (Bool)
            - date (Date)
            - programDetails (String)
            - numberOfSeat (Integer)
         }
         class "Booking"{
            - bookingId (Integer)
            - userId (String)
            - lectureId (String)
            - informationType (Enum BOOKING_INFO)
         }
      }
      package "controller"{
   
      }
      package "converter"{
         interface UserConverter{

         }
         interface StudentConverter extends UserConverter{

         }
         interface OfficerConverter extends OfficerConverter{

         }
         interface ManagerConverter extends ManagerConverter{

         }
         interface ProfessorConverter extends UserConverter{

         }
         interface LectureConverter {

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
         interface LectureDto{

         }
         interface CourseDto{

         }
         interface BookingDto{

         }
      }
      package "entity"{
         interface User{

         }
         interface Student extends User{

         }
         interface Officer extends User{

         }
         interface Manager extends User{

         }
         interface Professor extends User{

         }
         interface Lecture{

         }
         interface Course{

         }
         interface Booking{

         }
      }
      package "repository"{
         interface UserRepository{

         }
         interface StudentRepository extends UserRepository{

         }
         interface OfficerRepository extends UserRepository{

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
         interface LectureService{

         }
         interface CourseService{

         }
         interface BookingService{

         }
      }
      package "service.impl"{
	interface UserServiceImp{

         }
         interface StudentServiceImp extends UserServiceImp{

         }
         interface OfficerServiceImp extends UserServiceImp{

         }
         interface ManagerServiceImp extends UserServiceImp{

         }
         interface ProfessorServiceImp extends UserServiceImp{

         }
         interface LectureServiceImp{
 
         }
         interface CourseServiceImp{

         }
         interface BookingServiceImp{

         }
      }
   }
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
   table(CLASS) {
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
      PROGRAM_DETAILS
      NUMBER_OF_SEAT
   }
   table(BOOKING){
      primary_key(BOOKING_ID)
      USER_ID
      LECTURE_ID
      INFORMATION_TYPE
   }
   table(HOLIDAY){
     primary_key(HOLIDAY_ID)
     DATE
   }
   table(NOTIFICATION_PROF){
     primary_key(NOTIFICATION_ID)
     USER_ID
     DATE
     NOTIFICATION_TYPE
     DESCRIPTION
   }
   table(NOTIFICATION_STUD){
     primary_key(NOTIFICATION_ID)
     USER_ID
     DATE
     NOTIFICATION_TYPE
     DESCRIPTION
   }

   PROFESSOR -- PROFESSOR_COURSE
   COURSE -- PROFESSOR_COURSE
   USER "*"--"*" COURSE

   STUDENT -- CLASS
   COURSE -- CLASS
   STUDENT "*"--"*" COURSE

   PROFESSOR "1"--"*" LECTURE
   COURSE "1"--"*" LECTURE
   STUDENT "1" -- "*" BOOKING
   LECTURE "1" -- "*" BOOKING
   STUDENT "1" -- "*" NOTIFICATION_STUDENT
   PROFESSOR "1" -- "*" NOTIFICATION_PROFESSOR
}
}
@enduml
```


