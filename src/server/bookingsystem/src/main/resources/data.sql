INSERT INTO MANAGER(USER_ID,NAME,SURNAME,ADDRESS,EMAIL,PASSWORD) VALUES (0, 'Giuseppe', 'Conte', 'Palazzo Chigi', 'manager@polito.it', 'password');


INSERT INTO OFFICER(USER_ID,NAME,SURNAME,ADDRESS,EMAIL,PASSWORD) VALUES (0, 'Mario', 'Rossi', 'Via quella 12', 'officer@polito.it', 'password');


INSERT INTO PROFESSOR(USER_ID,CODE,NAME,SURNAME,ADDRESS,EMAIL,PASSWORD) VALUES (0, 'd0000', 'Marco', 'Torchiano', 'Corso Duca degli Abruzzi 8', 's277922@studenti.polito.it', 'password');
INSERT INTO PROFESSOR(USER_ID,CODE,NAME,SURNAME,ADDRESS,EMAIL,PASSWORD) VALUES (1, 'd0001', 'Giorgio', 'Bruno', 'Corso Galileo Ferraris 89', 'd0002@polito.it', 'password');
INSERT INTO PROFESSOR(USER_ID,CODE,NAME,SURNAME,ADDRESS,EMAIL,PASSWORD) VALUES (2, 'd0002', 'Gianpiero', 'Cabodi', 'Corso Inghilterra 16' , 'd0002@polito.it', 'password');
INSERT INTO PROFESSOR(USER_ID,CODE,NAME,SURNAME,ADDRESS,EMAIL,PASSWORD) VALUES (3, 'd0003', 'Antonio', 'Lioy', 'Via Roma 45', 'd0003@polito.it', 'password');


INSERT INTO STUDENT(USER_ID,MATRICOLA,NAME,SURNAME,ADDRESS,DATE_OF_BIRTH,EMAIL,PASSWORD) VALUES (0,'s0000','Andrea', 'Rubiolo', 'Via Roma 3', '1996-12-25', 's277922@studenti.polito.it', 'password');
INSERT INTO STUDENT(USER_ID,MATRICOLA,NAME,SURNAME,ADDRESS,DATE_OF_BIRTH,EMAIL,PASSWORD) VALUES (1,'s0001','Tony', 'Saliba', 'Corso Galileo Ferraris 19', '1995-02-25', 'tony.y.saliba00@gmail.com', 'password');
INSERT INTO STUDENT(USER_ID,MATRICOLA,NAME,SURNAME,ADDRESS,DATE_OF_BIRTH,EMAIL,PASSWORD) VALUES (2,'s0002','Fiorentino', 'Cairone', 'Corso Re Umberto 39', '1994-08-27', 'silviogiro@gmail.com', 'password');
INSERT INTO STUDENT(USER_ID,MATRICOLA,NAME,SURNAME,ADDRESS,DATE_OF_BIRTH,EMAIL,PASSWORD) VALUES (3,'s0003','Peng', 'Cao', 'Corso Stati Uniti 1', '1995-01-08', 's0003@studenti.polito.it', 'password');


INSERT INTO COURSE(COURSE_ID,CODE,NAME,YEAR,SEMESTER) VALUES (0, 'XY0000', 'Object oriented programming', 1, 1);
INSERT INTO COURSE(COURSE_ID,CODE,NAME,YEAR,SEMESTER) VALUES (1, 'XY0001', 'System and Device Programming', 1, 1);
INSERT INTO COURSE(COURSE_ID,CODE,NAME,YEAR,SEMESTER) VALUES (2, 'XY0002', 'Information System Security', 1, 2);
INSERT INTO COURSE(COURSE_ID,CODE,NAME,YEAR,SEMESTER) VALUES (3, 'XY0003', 'Software Engineering', 2, 1);
INSERT INTO COURSE(COURSE_ID,CODE,NAME,YEAR,SEMESTER) VALUES (4, 'XY0004', 'Software Engineering II', 2, 2);
INSERT INTO COURSE(COURSE_ID,CODE,NAME,YEAR,SEMESTER) VALUES (5, 'XY0005', 'Cybersecurity', 2, 2);


INSERT INTO COURSE_PROFESSOR(USER_ID, COURSE_ID) VALUES (0, 0);
INSERT INTO COURSE_PROFESSOR(USER_ID, COURSE_ID) VALUES (2, 1);
INSERT INTO COURSE_PROFESSOR(USER_ID, COURSE_ID) VALUES (3, 2);
INSERT INTO COURSE_PROFESSOR(USER_ID, COURSE_ID) VALUES (1, 3);
INSERT INTO COURSE_PROFESSOR(USER_ID, COURSE_ID) VALUES (0, 4);
INSERT INTO COURSE_PROFESSOR(USER_ID, COURSE_ID) VALUES (3, 5);


INSERT INTO COURSE_STUDENT(USER_ID,COURSE_ID) VALUES (0,0);
INSERT INTO COURSE_STUDENT(USER_ID,COURSE_ID) VALUES (0,1);
INSERT INTO COURSE_STUDENT(USER_ID,COURSE_ID) VALUES (0,2);
INSERT INTO COURSE_STUDENT(USER_ID,COURSE_ID) VALUES (0,3);
INSERT INTO COURSE_STUDENT(USER_ID,COURSE_ID) VALUES (0,4);
INSERT INTO COURSE_STUDENT(USER_ID,COURSE_ID) VALUES (0,5);
INSERT INTO COURSE_STUDENT(USER_ID,COURSE_ID) VALUES (1,0);
INSERT INTO COURSE_STUDENT(USER_ID,COURSE_ID) VALUES (1,1);
INSERT INTO COURSE_STUDENT(USER_ID,COURSE_ID) VALUES (1,2);
INSERT INTO COURSE_STUDENT(USER_ID,COURSE_ID) VALUES (1,3);
INSERT INTO COURSE_STUDENT(USER_ID,COURSE_ID) VALUES (1,4);
INSERT INTO COURSE_STUDENT(USER_ID,COURSE_ID) VALUES (1,5);
INSERT INTO COURSE_STUDENT(USER_ID,COURSE_ID) VALUES (2,0);
INSERT INTO COURSE_STUDENT(USER_ID,COURSE_ID) VALUES (2,1);
INSERT INTO COURSE_STUDENT(USER_ID,COURSE_ID) VALUES (2,2);
INSERT INTO COURSE_STUDENT(USER_ID,COURSE_ID) VALUES (2,3);
INSERT INTO COURSE_STUDENT(USER_ID,COURSE_ID) VALUES (2,4);
INSERT INTO COURSE_STUDENT(USER_ID,COURSE_ID) VALUES (2,5);
INSERT INTO COURSE_STUDENT(USER_ID,COURSE_ID) VALUES (3,0);
INSERT INTO COURSE_STUDENT(USER_ID,COURSE_ID) VALUES (3,1);
INSERT INTO COURSE_STUDENT(USER_ID,COURSE_ID) VALUES (3,2);
INSERT INTO COURSE_STUDENT(USER_ID,COURSE_ID) VALUES (3,3);
INSERT INTO COURSE_STUDENT(USER_ID,COURSE_ID) VALUES (3,4);
INSERT INTO COURSE_STUDENT(USER_ID,COURSE_ID) VALUES (3,5);


INSERT INTO ROOM(ROOM_ID,NUMBER_OF_SEAT,NAME) VALUES (0,150,'Room 1');
INSERT INTO ROOM(ROOM_ID,NUMBER_OF_SEAT,NAME) VALUES (1,35,'Room 2');


INSERT INTO LECTURE(LECTURE_ID,NUMBER_OF_LESSON,COURSE_ID,USER_ID,REMOTLY,DATE,DURATION,PROGRAM_DETAILS,ROOM_ID,BOOKED_SEATS,DELETED) VALUES (0, 1, 0, 0, FALSE, '2021-01-05 10:00', 180, '', 0, 2, FALSE);
INSERT INTO LECTURE(LECTURE_ID,NUMBER_OF_LESSON,COURSE_ID,USER_ID,REMOTLY,DATE,DURATION,PROGRAM_DETAILS,ROOM_ID,BOOKED_SEATS,DELETED) VALUES (1, 2, 0, 0, FALSE, '2021-01-06 10:00', 180, '', 0, 1, FALSE);


INSERT INTO BOOKING(BOOKING_ID, BOOKING_INFO, LECTURE_ID, USER_ID) VALUES (0, 'ATTENDED', 0, 0);
INSERT INTO BOOKING(BOOKING_ID, BOOKING_INFO, LECTURE_ID, USER_ID) VALUES (1, 'CANCELED_BY_STUD', 0, 1);
INSERT INTO BOOKING(BOOKING_ID, BOOKING_INFO, LECTURE_ID, USER_ID) VALUES (2, 'BOOKED', 0, 2);
INSERT INTO BOOKING(BOOKING_ID, BOOKING_INFO, LECTURE_ID, USER_ID) VALUES (3, 'ATTENDED', 1, 2);

