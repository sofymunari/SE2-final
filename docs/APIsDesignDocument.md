# **API DESIGN DOCUMENT**

## Student Log In
- method: POST
- url: '/studentlogin'
- params: username, password
- returned value: username of student or null(if authentication fails)

## Student Lectures
- method: GET
- url: '/studentlectures'
- params: username (=email of the student)
- returned value: **list** of objects with following attributes: lecture_id, number_of_lessons, course_name, professor_name, remotly(yes,no), date(String with date and hour), number_of_seat
- some info: the lectures are only the available lectures for the student (not already booked by the student). Also lectures with no seats available are **included**. list can be eventually empty [].

## Student info
- method: GET
- url: '/studentinfo'
- params: username (=email of student)
- returned value: object describing a student with following attributes: name, surname, address, username(email), date_of_birth.

## Student addBooking
- method: POST
- url: '/addbooking'
- params: id_lecture, username

## Student Bookings
- method: GET
- url: '/studentbookings'
- params: username
- returned value: **list** of bookings of a student. a booking is an oject with: booking_id, lecture_id, number_of_lesson, course_name, professor_name, remotly, date (String with date and hour), number_of_seat

## Student cancelBooking
- method: DELETE
- url: '/deletebooking'
- params: booking_id, username(=email of student)