# Design Document 
# Low level design
```plantuml
@startuml
package "Backend" {
   package "com.polito.BookingSystem"{
      package "entity"{
         class "Student"{
            - studentId
            - name
            - surname 
            - address
            - dateOfBirth
            - email
            - password
            - courses
         }
         class "Professor"{
            - professorId
            - name
            - surname
            - address
            - email
            - password
            - courses
         }
         class "Course"{
            - courseId
            - name
            - description
            - professors
            - students
         }
         class "Lecture"{
            - lectureId
            - numberOfLesson
            - courseId
            - professorId
            - remotly
            - date
            - programDetails
            - numberOfSeat
         }
         class "Booking"{
            - bookingId
            - studentId 
            - lectureId
            - informationType
         }
      }
      package "controller"{
   
      }
      package "converter"{
         interface StudentConverter{

         }
         interface LectureConverter{

         }
         interface ProfessorConverter{

         }
         interface CourseConverter{

         }
         interface BookingConverter{

         }
      }
      package "dto"{
        interface StudentDto{

         }
         interface LectureDto{

         }
         interface ProfessorDto{

         }
         interface CourseDto{

         }
         interface BookingDto{

         }
      }
      package "entity"{
        interface Student{

         }
         interface Lecture{

         }
         interface Professor{

         }
         interface Course{

         }
         interface Booking{

         }
      }
      package "repository"{
         interface StudentRepository{

         }
         interface LectureRepository{

         }
         interface ProfessorRepository{

         }
         interface CourseRepository{

         }
         interface BookingRepository{

         }

      }
      package "service"{
         interface StudentService{

         }
         interface LectureService{

         }
         interface ProfessorService{

         }
         interface CourseService{

         }
         interface BookingService{

         }
      }
      package "service.impl"{

      }
   }
   package DataBase{
   !define table(x) class x << (T,#FFAAAA) >>
   !define primary_key(x) <u>x</u>
   hide methods
   hide stereotypes
   table(STUDENT) {
      primary_key(STUDENT_ID)
      NAME
      SURNAME
      ADDRESS
      DATE_OF_BIRTH
      EMAIL
      PASSWORD
   }
   table(PROFESSOR) {
      primary_key(PROFESSOR_ID)
      NAME
      SURNAME
      ADDRESS
      EMAIL
      PASSWORD
   }
   table(COURSE) {
      primary_key(COURSE_ID)
      NAME
      DESCRIPTION
   }
   table(PROFESSOR_COURSE) {
      PROFESSOR_ID
      STUDENT_ID
   }
   table(STUDENT_COURSE) {
      PROFESSOR_ID
      STUDENT_ID
   }
   table(LECTURE) {
      primary_key(LECTURE_ID)
      NUMBER_OF_LESSON
      COURSE_ID
      PROFESSOR_ID
      REMOTLY
      DATE
      PROGRAM_DETAILS
      NUMBER_OF_SEAT
   }
   table(BOOKING){
      primary_key(BOOKING_ID)
      STUDENT_ID
      LECTURE_ID
      INFORMATION_TYPE
   }
   PROFESSOR -- PROFESSOR_COURSE
   COURSE -- PROFESSOR_COURSE
   PROFESSOR "*"--"*" COURSE
   STUDENT -- STUDENT_COURSE
   COURSE -- STUDENT_COURSE
   STUDENT "*"--"*" COURSE
   PROFESSOR "1"--"*" LECTURE
   COURSE "1"--"*" LECTURE
   STUDENT "1" -- "*" BOOKING
   LECTURE "1" -- "*" BOOKING
}
}
@enduml
```


