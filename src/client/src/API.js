
const BASE_URL = 'http://localhost:8080/'

const students=[{"name":'Pippo',"surname":'Baudo','address':'via monte napoleone 13','date_of_birth':'27/10/1996','email':'pippo@gmail.com'},{"name":'Gianni',"surname":'Ciano','address':'via monte napoleone 13','date_of_birth':'27/10/1996','email':'ciano@gmail.com'}]
let lectures=[{"lecture_id":1,"number_of_lesson":1,"course_name": "APA","professor_name":"Luca Bartolini","remotly":'no',"date":'08/11/2020',"number_of_seat":10},
{"lecture_id":2,"number_of_lesson":1,"course_name": "APA","professor_name":"Luca Bartolini","remotly":'no',"date":'08/11/2020',"number_of_seat":10},{"lecture_id":3,"number_of_lesson":2,"course_name": "Webb App","professor_name":"Fulvio Bisanzio","remotly":'no',"date":'08/11/2020',"number_of_seat":10}]
let lecturesBooked=[{"lecture_id":3,"number_of_lesson":1,"course_name": "APA","professor_name":"Luca Bartolini","remotly":'no',"date":'08/11/2020',"number_of_seat":10}]
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
    lectures=lectures.filter((lecture)=>{return lecture.lecture_id!=id})
    return "ok";
}

async function getStudentBookings(username){
    return new Promise((resolve,reject)=>{
        if(true){
            resolve(lecturesBooked);
        }
        reject ("error")});
}

const API={loginStudent, loginTeacher, getStudentLectures, getStudentInfo, addBooking,getStudentBookings}
export default API;