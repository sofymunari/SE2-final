
const BASE_URL = 'http://localhost:8080/'

const students=[{"name":'Pippo',"surname":'Baudo','address':'via monte napoleone 13','date_of_birth':'27/10/1996','email':'pippo@gmail.com'},{"name":'Gianni',"surname":'Ciano','address':'via monte napoleone 13','date_of_birth':'27/10/1996','email':'ciano@gmail.com'}]
let lectures=[{"lecture_id":1,"number_of_lesson":1,"course_name": "APA","professor_name":"Luca Bartolini","remotly":'no',"date":'08/11/2020',"number_of_seat":10},
{"lecture_id":2,"number_of_lesson":1,"course_name": "APA","professor_name":"Luca Bartolini","remotly":'no',"date":'11/23/2020',"number_of_seat":10},{"lecture_id":3,"number_of_lesson":2,"course_name": "Webb App","professor_name":"Fulvio Bisanzio","remotly":'no',"date":'08/11/2020',"number_of_seat":10}]
let lecturesBooked=[{"booking_id":0,"lecture_id":3,"number_of_lesson":4,"course_name": "APA1","professor_name":"Luca Bartolini","remotly":'no',"date":'11/23/2020 9:00',"number_of_seat":9}]
async function loginStudent(username,password){
    return new Promise((resolve,reject)=>{
        if(true){
            resolve(username);
        }
        reject ("error")});
    
}
async function loginTeacher(username,password){
    return "ok";
}

async function getStudentLectures(username){
    return new Promise((resolve,reject)=>{
        const lectTrial=[];
        if(true){
            resolve(lectures);
        }
        reject ("error")});
}

async function getStudentInfo(username){
    const student=students.filter((stud)=>{return stud.email===username})
    return student.pop();
}

async function addBooking(id){
    let lect=lectures.filter((lecture)=>{return lecture.lecture_id === id}).pop()
    lectures=lectures.filter((lecture)=>{return lecture.lecture_id!==id})
    let booking={"booking_id":1,"lecture_id":lect.lecture_id,"number_of_lesson":lect.number_of_lesson,"course_name": lect.course_name,"professor_name":"none","remotly":lect.remotly,"date":lect.date,"number_of_seat":lect.number_of_seat}
    lecturesBooked.push(booking)
    return "ok";
}

async function getStudentBookings(username){
    return new Promise((resolve,reject)=>{
        if(true){
            resolve(lecturesBooked);
        }
        reject ("error")});
}

async function cancelBooking(id){
    lecturesBooked=lecturesBooked.filter((booking)=>{return booking.booking_id !== id});
}

const API={loginStudent, loginTeacher, getStudentLectures, getStudentInfo, addBooking,getStudentBookings,cancelBooking}
export default API;