INSERT INTO STUDENT(USER_ID,MATRICOLA,NAME,SURNAME,ADDRESS,DATE_OF_BIRTH,EMAIL,PASSWORD) VALUES (0,'0000','Andrea', 'Rubiolo', 'Via Roma 3', '1996-12-25', 's0000@studenti.polito.it', 'password');
INSERT INTO STUDENT(USER_ID,MATRICOLA,NAME,SURNAME,ADDRESS,DATE_OF_BIRTH,EMAIL,PASSWORD) VALUES (1,'0001','Sofia', 'Munari', 'Corso Inghilterra 24', '1997-02-13', 's0001@studenti.polito.it', 'password');
INSERT INTO STUDENT(USER_ID,MATRICOLA,NAME,SURNAME,ADDRESS,DATE_OF_BIRTH,EMAIL,PASSWORD) VALUES (2,'0002','Fiorentino', 'Cairone', 'Corso Re Umberto 39', '1994-08-27', 'silviogiro@gmail.com', 'password');
INSERT INTO STUDENT(USER_ID,MATRICOLA,NAME,SURNAME,ADDRESS,DATE_OF_BIRTH,EMAIL,PASSWORD) VALUES (3,'0003','Peng', 'Cao', 'Corso Stati Uniti 1', '1995-01-08', 's0003@studenti.polito.it', 'password');
INSERT INTO STUDENT(USER_ID,MATRICOLA,NAME,SURNAME,ADDRESS,DATE_OF_BIRTH,EMAIL,PASSWORD) VALUES (4,'0004','Silvio', 'Girolami', 'Via Roma 38', '1997-10-30', 's0004@studenti.polito.it', 'password');
INSERT INTO STUDENT(USER_ID,MATRICOLA,NAME,SURNAME,ADDRESS,DATE_OF_BIRTH,EMAIL,PASSWORD) VALUES (5,'0005','Tony', 'Saliba', 'Corso Galileo Ferraris 19', '1995-04-01', 'tony.y.saliba00@gmail.com', 'password');


INSERT INTO PROFESSOR(USER_ID,CODE,NAME,SURNAME,ADDRESS,EMAIL,PASSWORD) VALUES (0, 'd00', 'Marco', 'Torchiano', 'Corso Duca degli Abruzzi 8', 'p0000@polito.it', 'password');
INSERT INTO PROFESSOR(USER_ID,CODE,NAME,SURNAME,ADDRESS,EMAIL,PASSWORD) VALUES (1, 'd01', 'Giorgio', 'Bruno', 'Corso Galileo Ferraris 89', 'p0001@polito.it', 'password');
INSERT INTO PROFESSOR(USER_ID,CODE,NAME,SURNAME,ADDRESS,EMAIL,PASSWORD) VALUES (2, 'd02', 'Gianpiero', 'Cabodi', 'Corso Inghilterra 16' , 'p0002@polito.it', 'password');
INSERT INTO PROFESSOR(USER_ID,CODE,NAME,SURNAME,ADDRESS,EMAIL,PASSWORD) VALUES (3, 'd03', 'Antonio', 'Lioy', 'Via Roma 45', 'p0003@polito.it', 'password');

INSERT INTO COURSE(COURSE_ID,CODE,NAME,YEAR,SEMESTER) VALUES (0,'Q', 'Software Engineering II',1,1 );
INSERT INTO COURSE(COURSE_ID,CODE,NAME,YEAR,SEMESTER) VALUES (1, 'W','Ingegneria del Software',1,1 );
INSERT INTO COURSE(COURSE_ID,CODE,NAME,YEAR,SEMESTER) VALUES (2,'X', 'Programmazione di Sistema',1,1 );
INSERT INTO COURSE(COURSE_ID,CODE,NAME,YEAR,SEMESTER) VALUES (3,'D', 'Information System Security',1,1 );
INSERT INTO COURSE(COURSE_ID,CODE,NAME,YEAR,SEMESTER) VALUES (4,'E', 'Algoritmi e Programmazione',1,1 );
INSERT INTO COURSE(COURSE_ID,CODE,NAME,YEAR,SEMESTER) VALUES (5,'F', 'Programmazione ad Oggetti',1,1 );
INSERT INTO COURSE(COURSE_ID,CODE,NAME,YEAR,SEMESTER) VALUES (6,'G', 'Cybersecurity', 1,1);


INSERT INTO COURSE_PROFESSOR(USER_ID, COURSE_ID) VALUES (0,0);
INSERT INTO COURSE_PROFESSOR(USER_ID, COURSE_ID) VALUES (1,1);
INSERT INTO COURSE_PROFESSOR(USER_ID, COURSE_ID) VALUES (2,2);
INSERT INTO COURSE_PROFESSOR(USER_ID, COURSE_ID) VALUES (3,3);
INSERT INTO COURSE_PROFESSOR(USER_ID, COURSE_ID) VALUES (2,4);
INSERT INTO COURSE_PROFESSOR(USER_ID, COURSE_ID) VALUES (0,5);
INSERT INTO COURSE_PROFESSOR(USER_ID, COURSE_ID) VALUES (3,6);

INSERT INTO COURSE_STUDENT(USER_ID,COURSE_ID) VALUES (0,0);
INSERT INTO COURSE_STUDENT(USER_ID,COURSE_ID) VALUES (0,2);
INSERT INTO COURSE_STUDENT(USER_ID,COURSE_ID) VALUES (0,3);
INSERT INTO COURSE_STUDENT(USER_ID,COURSE_ID) VALUES (1,4);
INSERT INTO COURSE_STUDENT(USER_ID,COURSE_ID) VALUES (1,6);
INSERT INTO COURSE_STUDENT(USER_ID,COURSE_ID) VALUES (2,5);
INSERT INTO COURSE_STUDENT(USER_ID,COURSE_ID) VALUES (3,1);
INSERT INTO COURSE_STUDENT(USER_ID,COURSE_ID) VALUES (3,0);
INSERT INTO COURSE_STUDENT(USER_ID,COURSE_ID) VALUES (4,3);
INSERT INTO COURSE_STUDENT(USER_ID,COURSE_ID) VALUES (4,2);
INSERT INTO COURSE_STUDENT(USER_ID,COURSE_ID) VALUES (4,4);
INSERT INTO COURSE_STUDENT(USER_ID,COURSE_ID) VALUES (5,6);
INSERT INTO COURSE_STUDENT(USER_ID,COURSE_ID) VALUES (5,5);


INSERT INTO ROOM(ROOM_ID,NUMBER_OF_SEAT,NAME) VALUES (0,150,'Room 1');
INSERT INTO ROOM(ROOM_ID,NUMBER_OF_SEAT,NAME) VALUES (1,35,'Room 3s');
INSERT INTO ROOM(ROOM_ID,NUMBER_OF_SEAT,NAME) VALUES (2,250,'Aula Magna');
INSERT INTO ROOM(ROOM_ID,NUMBER_OF_SEAT,NAME) VALUES (3,2,'Room 5');
INSERT INTO ROOM(ROOM_ID,NUMBER_OF_SEAT,NAME) VALUES (4,70,'Room 7i');

INSERT INTO LECTURE(LECTURE_ID,NUMBER_OF_LESSON,COURSE_ID,USER_ID,REMOTLY,DATE,DURATION,PROGRAM_DETAILS,ROOM_ID,BOOKED_SEATS,DELETED) VALUES (16,5,0,0,FALSE,'2020-11-20 10:00',180,'',0,2, FALSE);
INSERT INTO LECTURE(LECTURE_ID,NUMBER_OF_LESSON,COURSE_ID,USER_ID,REMOTLY,DATE,DURATION,PROGRAM_DETAILS,ROOM_ID,BOOKED_SEATS,DELETED) VALUES (17,6,0,0,FALSE,'2020-11-19 12:30',180,'',0,2, FALSE);

INSERT INTO LECTURE(LECTURE_ID,NUMBER_OF_LESSON,COURSE_ID,USER_ID,REMOTLY,DATE,DURATION,PROGRAM_DETAILS,ROOM_ID,BOOKED_SEATS,DELETED) VALUES (0,1,0,0,FALSE,'2020-11-22 10:00',180,'',0,20, FALSE);
INSERT INTO LECTURE(LECTURE_ID,NUMBER_OF_LESSON,COURSE_ID,USER_ID,REMOTLY,DATE,DURATION,PROGRAM_DETAILS,ROOM_ID,BOOKED_SEATS,DELETED) VALUES (1,2,0,0,FALSE,'2020-11-23 12:30',180,'',0,5, FALSE);
INSERT INTO LECTURE(LECTURE_ID,NUMBER_OF_LESSON,COURSE_ID,USER_ID,REMOTLY,DATE,DURATION,PROGRAM_DETAILS,ROOM_ID,BOOKED_SEATS,DELETED) VALUES (2,1,1,1,FALSE,'2020-10-19 14:00',180,'',1,4, TRUE);
INSERT INTO LECTURE(LECTURE_ID,NUMBER_OF_LESSON,COURSE_ID,USER_ID,REMOTLY,DATE,DURATION,PROGRAM_DETAILS,ROOM_ID,BOOKED_SEATS,DELETED) VALUES (3,1,2,2,FALSE,'2020-12-29 10:00',180,'',2,250, FALSE);
INSERT INTO LECTURE(LECTURE_ID,NUMBER_OF_LESSON,COURSE_ID,USER_ID,REMOTLY,DATE,DURATION,PROGRAM_DETAILS,ROOM_ID,BOOKED_SEATS,DELETED) VALUES (4,2,2,2,TRUE,'2020-12-30 08:00',90,'',3,2, FALSE);
INSERT INTO LECTURE(LECTURE_ID,NUMBER_OF_LESSON,COURSE_ID,USER_ID,REMOTLY,DATE,DURATION,PROGRAM_DETAILS,ROOM_ID,BOOKED_SEATS,DELETED) VALUES (5,1,3,3,FALSE,'2021-01-19 16:30',90,'',4,0, FALSE);
INSERT INTO LECTURE(LECTURE_ID,NUMBER_OF_LESSON,COURSE_ID,USER_ID,REMOTLY,DATE,DURATION,PROGRAM_DETAILS,ROOM_ID,BOOKED_SEATS,DELETED) VALUES (6,2,3,3,FALSE,'2021-01-21 14:00',90,'',1,0, FALSE);
INSERT INTO LECTURE(LECTURE_ID,NUMBER_OF_LESSON,COURSE_ID,USER_ID,REMOTLY,DATE,DURATION,PROGRAM_DETAILS,ROOM_ID,BOOKED_SEATS,DELETED) VALUES (7,1,4,2,FALSE,'2020-12-30 14:00',90,'',2,0, FALSE);
INSERT INTO LECTURE(LECTURE_ID,NUMBER_OF_LESSON,COURSE_ID,USER_ID,REMOTLY,DATE,DURATION,PROGRAM_DETAILS,ROOM_ID,BOOKED_SEATS,DELETED) VALUES (8,1,5,0,TRUE,'2020-10-20 10:00',180,'',2,15, FALSE);
INSERT INTO LECTURE(LECTURE_ID,NUMBER_OF_LESSON,COURSE_ID,USER_ID,REMOTLY,DATE,DURATION,PROGRAM_DETAILS,ROOM_ID,BOOKED_SEATS,DELETED) VALUES (9,2,5,0,FALSE,'2020-12-28 14:30',90,'',3,1, FALSE);
INSERT INTO LECTURE(LECTURE_ID,NUMBER_OF_LESSON,COURSE_ID,USER_ID,REMOTLY,DATE,DURATION,PROGRAM_DETAILS,ROOM_ID,BOOKED_SEATS,DELETED) VALUES (10,3,5,0,FALSE,'2020-12-23 14:00',90,'',4,0, FALSE);
INSERT INTO LECTURE(LECTURE_ID,NUMBER_OF_LESSON,COURSE_ID,USER_ID,REMOTLY,DATE,DURATION,PROGRAM_DETAILS,ROOM_ID,BOOKED_SEATS,DELETED) VALUES (11,1,6,3,TRUE,'2020-12-20 14:00',90,'',1,0, FALSE);
INSERT INTO LECTURE(LECTURE_ID,NUMBER_OF_LESSON,COURSE_ID,USER_ID,REMOTLY,DATE,DURATION,PROGRAM_DETAILS,ROOM_ID,BOOKED_SEATS,DELETED) VALUES (12,3,0,0,FALSE,'2020-12-10 12:30',90,'',0,2, FALSE);
INSERT INTO LECTURE(LECTURE_ID,NUMBER_OF_LESSON,COURSE_ID,USER_ID,REMOTLY,DATE,DURATION,PROGRAM_DETAILS,ROOM_ID,BOOKED_SEATS,DELETED) VALUES (13,4,0,0,FALSE,'2020-12-20 12:30',180,'',0,0, FALSE);
INSERT INTO LECTURE(LECTURE_ID,NUMBER_OF_LESSON,COURSE_ID,USER_ID,REMOTLY,DATE,DURATION,PROGRAM_DETAILS,ROOM_ID,BOOKED_SEATS,DELETED) VALUES (14,2,1,1,FALSE,'2020-12-19 14:00',180,'',1,0, FALSE);
INSERT INTO LECTURE(LECTURE_ID,NUMBER_OF_LESSON,COURSE_ID,USER_ID,REMOTLY,DATE,DURATION,PROGRAM_DETAILS,ROOM_ID,BOOKED_SEATS,DELETED) VALUES (15,4,5,0,TRUE,'2020-12-20 10:00',180,'',2,0, FALSE);

INSERT INTO BOOKING(BOOKING_ID,USER_ID,LECTURE_ID,BOOKING_INFO) VALUES (0,1,9,'BOOKED');
INSERT INTO BOOKING(BOOKING_ID,USER_ID,LECTURE_ID,BOOKING_INFO) VALUES (90,0,12,'ATTENDED');
INSERT INTO BOOKING(BOOKING_ID,USER_ID,LECTURE_ID,BOOKING_INFO) VALUES (91,3,12,'ATTENDED');
INSERT INTO HOLIDAY(HOLIDAY_ID,DATE) VALUES (0, '2020-12-08 00:00');

INSERT INTO NOTIFICATION_PROFESSOR(NOTIFICATION_ID,DATE,DESCRIPTION,LINK,STATUS,USER_ID) VALUES (0,'2020-12-10 10:00','You have a new upcoming lesson','link',FALSE,0);

INSERT INTO MANAGER(USER_ID,NAME,SURNAME,ADDRESS,EMAIL,PASSWORD) VALUES (0, 'Giuseppe', 'Conte', 'Palazzo Chigi', 'manager@polito.it', 'password');

INSERT INTO OFFICER(USER_ID,NAME,SURNAME,ADDRESS,EMAIL,PASSWORD) VALUES (0, 'Mario', 'Rossi', 'Via quella 12', 'officer@polito.it', 'password');
