
const BASE_URL = 'http://localhost:8080/'

async function loginStudent(username,password){
    let url=BASE_URL+"studentlogin"
    const response= await fetch(url,{
        method: 'POST',
        headers : {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({username:username,password:password})
    })
    const restext=await response.text();
    if(response.ok){
        if(restext){
            return restext;
        }
        throw "error";
    }
}


async function loginTeacher(username,password){
    let url=BASE_URL+"professorlogin"
    const response= await fetch(url,{
        method: 'POST',
        headers : {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({username:username,password:password})
    })
    const restext=await response.text();
    if(response.ok){
        if(restext){
            return restext;
        }
        throw "error";
    }

}

async function loginManager(username,password){
    let url=BASE_URL+"managerlogin"
    const response= await fetch(url,{
        method: 'POST',
        headers : {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({username:username,password:password})
    })
    const restext=await response.text();
    if(response.ok){
        if(restext){
            return restext;
        }
        throw "error";
    }
}

async function loginSupportOfficer(username, password){
    let url = BASE_URL + "supportOfficerlogin"
    const response = await fetch(url, {
        method : 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({username:username,password:password})
    })
    const restext = await response.text();
    if(response.ok){
        if(restext){
            return restext;
        }
        throw "error";
    }
}

async function uploadStudentsFile(file){
    let url = BASE_URL+"uploadStudents"
    const response = await fetch(url, {
        method: "POST",
        body: file
    })
    const restext = await response.text();
    if(response.ok){
        if(restext){
            return restext;
        }
        throw "error";
    }
}

async function uploadProfessorsFile(file){
    let url = BASE_URL+"uploadProfessors"
    const response = await fetch(url, {
        method: "POST",
        body: file
    })
    const restext = await response.text();
    if(response.ok){
        if(restext){
            return restext;
        }
        throw "error";
    }
}

async function uploadEnrollmentFile(file){
    let url = BASE_URL+"uploadEnrollments"
    const response = await fetch(url, {
        method: "POST",
        body: file
    })
    const restext = await response.text();
    if(response.ok){
        if(restext){
            return restext;
        }
        throw "error";
    }
}async function uploadLecturesFile(file){
    let url = BASE_URL+"uploadLectures"
    const response = await fetch(url, {
        method: "POST",
        body: file
    })
    const restext = await response.text();
    if(response.ok){
        if(restext){
            return restext;
        }
        throw "error";
    }
}

async function uploadCoursesFile(file){
    let url = BASE_URL+"uploadCourses"
    const response = await fetch(url, {
        method: "POST",
        body: file
    })
    const restext = await response.text();
    if(response.ok){
        if(restext){
            return restext;
        }
        throw "error";
    }
}


async function uploadHolidaysFile(file){
    let url = BASE_URL+"uploadHolidays"
    const response = await fetch(url, {
        method: "POST",
        body: file
    })
    const restext = await response.text();
    if(response.ok){
        if(restext){
            return restext;
        }
        throw "error";
    }
}

async function sendCourses(courses){
    let url = BASE_URL+"sendCourses"
    const response = await fetch(url, {
        method: "POST",
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({courses:courses})
    })
    const restext = await response.text();
    if(response.ok)
            return restext;
        throw "error";
}

async function sendCourse(course){
    let url = BASE_URL+"sendCourse"
    const response = await fetch(url, {
        method: "POST",
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({course:course})
    })
    const restext = await response.text();
    console.log(response)
    if(response.ok)
        return restext;

    throw "error";
}

async function getStudentLectures(username){
    const url= BASE_URL+"studentlectures/"+username;
    const response= await fetch(url);
    const lects_json= await response.json();
    if(response.ok){
        return lects_json.map((l)=>{
            return {lectureId:l.lectureId,numberOfLesson:l.numberOfLesson,deleted:l.deleted,
            courseDto:{courseId:l.courseDto.courseId,name:l.courseDto.name,code:l.courseDto.code},
            professorDto:{userId:l.professorDto.userId,name:l.professorDto.name,surname:l.professorDto.surname,address:l.professorDto.address,
            email:l.professorDto.email},remotly:l.remotly,date:l.date,programDetails:l.programDetails,duration:l.duration,roomDto:{roomId:l.roomDto.roomId,name:l.roomDto.name,numberOfSeat:l.roomDto.numberOfSeat}, bookedSeats: l.bookedSeats}
        })
    }else{
        throw lects_json;
    }
}

async function getCourses(){
    const url = BASE_URL + "listcourses";
    const response = await fetch(url);
    const courses_json = await response.json();
    if(response.ok){
        return courses_json.map((c) => {
            return{courseId:c.courseId, courseName: c.name, courseCode: c.code, courseYear: c.year, courseSemester: c.semester}
        })
    }else{
        throw courses_json;
    }
}

async function getStudentInfo(username){
    let url=BASE_URL+"studentinfo/"+username;
    const response= await fetch(url);
    const studInfo=await response.json();
    if(response.ok){
    return {userId:studInfo.userId,name:studInfo.name, surname:studInfo.surname,
        address:studInfo.address,username:studInfo.email, dateOfBirth:studInfo.dateOfBirth, matricola: studInfo.matricola}
    }else{
        throw studInfo;
    }
}

async function getManagerInfo(username){
    let url=BASE_URL+"managerinfo/"+username;
    const response= await fetch(url);
    const managInfo=await response.json();
    if(response.ok){
    return {userId:managInfo.userId,name:managInfo.name, surname:managInfo.surname,
        address:managInfo.address,username:managInfo.email}
    }else{
        throw managInfo;
    }
}

async function getSupportOfficerInfo(username){
    let url=BASE_URL+"officerinfo/"+username;
    const response= await fetch(url);
    const officerInfo=await response.json();
    if(response.ok){
    return {userId:officerInfo.userId,name:officerInfo.name, surname:officerInfo.surname,
        address:officerInfo.address,username:officerInfo.email}
    }else{
        throw officerInfo;
    }}

async function addBooking(id,username){
    let url=BASE_URL+"addbooking"
    return new Promise((resolve,reject)=>{
        fetch(url,{
            method: 'POST',
            headers : {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({lectureId:id,email:username})
        }).then((response)=>{
            if(response.ok){
                if(!response.json()){
                    reject("error:adding a new booking")
                }
                resolve();
            }
            reject("connection error");
        }).catch((err)=>reject(err));
    })
}

async function setAttendences(idList, lectureId){
    let url = BASE_URL+ "lecture/"+ lectureId+"/attendance"

    return new Promise((resolve,reject) =>{
        fetch(url, {
            method: 'POST',
            headers : {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({studentIds: idList})
        }).then((response)=>{
            if(response.ok){
                if(!response.json()){
                    reject("error:setting attendences")
                }
                resolve();
            }
            reject("connection error");
        }).catch((err)=>reject(err));
    })
}


async function getStudentBookings(username){
    let url=BASE_URL+"studentbookings/"+username;
    const response=await fetch(url);
    const bks=await response.json();
    if(response.ok){
        return bks.map((b)=>{return {bookingId:b.bookingId,lectureDto:{lectureId:b.lectureDto.lectureId,
            numberOfLesson:b.lectureDto.numberOfLesson,remotly:b.lectureDto.remotly,
            date:b.lectureDto.date,programDetails:b.lectureDto.programDetails,duration:b.lectureDto.duration},
            courseDto:{courseId:b.lectureDto.courseDto.courseId,name:b.lectureDto.courseDto.name,
            code:b.lectureDto.courseDto.code},professorDto:{userId:b.lectureDto.professorDto.userId,
            name:b.lectureDto.professorDto.name,surname:b.lectureDto.professorDto.surname,
            address:b.lectureDto.professorDto.address},roomDto:{roomId:b.lectureDto.roomDto.roomId,
            name:b.lectureDto.roomDto.name,numberOfSeat:b.lectureDto.roomDto.numberOfSeat},bookingInfo:b.bookingInfo}});
    }else{
        throw response;
    }
}


async function getBookings(){
    let url=BASE_URL+"listbookings";
    const response=await fetch(url);
    const bks=await response.json();
    if(response.ok){
        return bks.map((b)=>{return {bookingId:b.bookingId,lectureDto:{lectureId:b.lectureDto.lectureId,
            numberOfLesson:b.lectureDto.numberOfLesson,remotly:b.lectureDto.remotly,
            date:b.lectureDto.date,programDetails:b.lectureDto.programDetails,duration:b.lectureDto.duration},
            courseDto:{courseId:b.lectureDto.courseDto.courseId,name:b.lectureDto.courseDto.name,
            code:b.lectureDto.courseDto.code},professorDto:{userId:b.lectureDto.professorDto.userId,
            name:b.lectureDto.professorDto.name,surname:b.lectureDto.professorDto.surname,
            address:b.lectureDto.professorDto.address},roomDto:{roomId:b.lectureDto.roomDto.roomId,
            name:b.lectureDto.roomDto.name,numberOfSeat:b.lectureDto.roomDto.numberOfSeat},bookingInfo:b.bookingInfo}});
    }else{
        throw response;
    }
}

async function getLectures(){
    const url= BASE_URL+"listlectures";
    const response= await fetch(url);
    const lects_json= await response.json();
    if(response.ok){
        return lects_json.map((l)=>{
            return {lectureId:l.lectureId,numberOfLesson:l.numberOfLesson,deleted:l.deleted,
            courseDto:{courseId:l.courseDto.courseId,name:l.courseDto.name,code:l.courseDto.code},
            professorDto:{userId:l.professorDto.userId,name:l.professorDto.name,surname:l.professorDto.surname,address:l.professorDto.address,
            email:l.professorDto.email},remotly:l.remotly,date:l.date,programDetails:l.programDetails,duration:l.duration,roomDto:{roomId:l.roomDto.roomId,name:l.roomDto.name,numberOfSeat:l.roomDto.numberOfSeat}, bookedSeats: l.bookedSeats}
        })
    }else{
        throw lects_json;
    }
}



async function cancelBooking(id){
    let url=BASE_URL+"deletebooking/"+id;
    return new Promise((resolve,reject)=>{
        fetch(url,{
            method: 'DELETE',
        }).then((response)=>{
            if(response.ok){
            resolve()
            }else{
                reject("an erroro occurred");
            }
        }).catch((err)=>reject(err));
    })
}


async function getTeacherInfo(username){
        
        let url=BASE_URL+"professorinfo/"+username;
        const response= await fetch(url);
        const teacInfo=await response.json();
        if(response.ok){
            return {userId:teacInfo.userId,name:teacInfo.name, surname:teacInfo.surname,
            address:teacInfo.address,username:teacInfo.email}
        }else{
        throw teacInfo;
		}
}

async function getTeacherBookings(username){
    const url= BASE_URL+"professorbookings/"+username;
    const response= await fetch(url);
    const lects_json= await response.json();
    if(response.ok){
        return lects_json.map((l)=>{
            
            return {lectureId:l.lectureId,lectureNumber:l.lectureNumber,
            course:{courseId:l.course.courseId,name:l.course.name,code:l.course.code},bookingId:l.bookingId,bookingInfo:l.bookingInfo,studentName:l.studentName,
            lectureDate:l.lectureDate,studentEmail:l.studentEmail,studentSurname:l.studentSurname}
        })
    }else{
        throw lects_json;
    }
}

async function getTeacherLectures(username){
    const url= BASE_URL+"professorlectures/"+username;
    const response= await fetch(url);
    const lects_json= await response.json();
    if(response.ok){
        return lects_json.map((l)=>{
            return {lectureId:l.lectureId,numberOfLesson:l.numberOfLesson,
            courseDto:{courseId:l.courseDto.courseId,name:l.courseDto.name,code:l.courseDto.code},roomDto:{name:l.roomDto.name},
            date:l.date,remotly:l.remotly,programDetails:l.programDetails,duration:l.duration,bookedSeats:l.bookedSeats,deleted:l.deleted}
        })
    }else{
        throw lects_json;
    }
}

async function getTeacherNotifications(username){
    const url= BASE_URL+"professor/notification/list/"+username;
    const response= await fetch(url);
    const notifications_json= await response.json();
    if(response.ok){
        return notifications_json.map((n)=>{
            return {notificationId:n.notificationId,code:n.code,date:n.date,status:n.status,link:n.link}
        })
    }else{
        throw notifications_json;
    }
}

async function teacherDeleteLecture(lectureId){
    const url= BASE_URL+"professor/deletelecture/"+lectureId;
    const response= await fetch(url,{
        method: 'DELETE'
    })
    const restext=await response.text();
    if(response.ok){
        if(restext){
            if(restext==='true'){
                return true;
            } 
            return false;
        }
        throw "error";
    }
}

async function teacherRemoteLecture(lectureId){
    const url= BASE_URL+"lecture/toremote/"+lectureId;
    const response= await fetch(url,{
        method: 'PUT'
    })
    const restext=await response.text();
    if(response.ok){
        if(restext){
            if(restext==='true'){
                return true;
            } 
            return false;
        }
        throw "error";
    }
}

async function downloadReportCsv(email, date){
    const url = BASE_URL + "managerportal/file/tracereport/studet/" + email + "/" + date; 
    const response = await fetch(url);
    const restext = await response.text();
    if(response.ok){
        if(restext){
            if(restext==='true'){
                return true;
            } 
            return false;
        }
        throw "error";
    }
}

async function downloadReportPdf(email, date){
    const url = BASE_URL + "managerportal/file/tracereport/studet/" + email + "/" + date; 
    const response = await fetch(url);
    const restext = await response.text();
    if(response.ok){
        if(restext){
            if(restext==='true'){
                return true;
            } 
            return false;
        }
        throw "error";
    }
}

async function getSchedules(courseId){
    const url = BASE_URL + "getScheduleCourse/" + courseId;
    const response = await fetch(url);
    const schedules_json= await response.json();
        if(response.ok){
            console.log(schedules_json);
        return schedules_json.map((n)=>{
            return {scheduleId:n.id, day:n.day, duration:n.duration, timeStart:n.timeStart, roomId:n.room.roomId, roomName: n.room.name}
        })
    }else{
        throw schedules_json;
    }
}

async function getRooms(){
    const url = BASE_URL + "getRooms/";
    const response = await fetch(url);
    const rooms_json= await response.json();
        if(response.ok){
        return rooms_json.map((n)=>{
            return {roomId:n.roomId, name:n.name, numberOfSeat:n.numberOfSeat}
        })
    }else{
        throw rooms_json;
    }
}

async function editSchedule(day, duration, roomId, courseCode, timeStart, scheduleId){
    const url = BASE_URL + "modifySchedule/"+ courseCode + "/" + scheduleId;
    return new Promise((resolve,reject) =>{
        fetch(url, {
            method: 'POST',
            headers : {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({day: day, duration: duration, timeStart: timeStart, roomId: roomId})
        }).then((response)=>{
            if(response.ok){
                resolve();
            }
            reject("connection error");
        }).catch((err)=>reject(err));
    })
}



const API={loginStudent, loginTeacher, loginManager, loginSupportOfficer, getStudentLectures, getStudentInfo, addBooking,
           getStudentBookings,cancelBooking,getTeacherInfo,getTeacherBookings,getTeacherLectures,getTeacherNotifications,
           teacherDeleteLecture,getManagerInfo,getSupportOfficerInfo, getBookings,teacherRemoteLecture,getLectures, uploadStudentsFile,
           uploadProfessorsFile, uploadEnrollmentFile, uploadLecturesFile, uploadCoursesFile,downloadReportCsv, setAttendences,
           downloadReportPdf, getCourses, sendCourse, sendCourses, getSchedules, getRooms, editSchedule, uploadHolidaysFile}
export default API;
