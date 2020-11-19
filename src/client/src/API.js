
const BASE_URL = 'http://localhost:8080/'

async function loginStudent(username,password){
    let url=BASE_URL+"studentlogin"
    return new Promise((resolve,reject)=>{
        fetch(url,{
            method: 'POST',
            headers : {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({username:username,password:password})
        }).then((response)=>{
            if(response.ok){
                resolve(username);
            }
        }).catch((err)=>{
            reject(err);
        })
    });
    //return 's0000@studenti.polito.it';
}

async function loginTeacher(username,password){
    let url=BASE_URL+"professorlogin"
    return new Promise((resolve,reject)=>{
        fetch(url,{
            method: 'POST',
            headers : {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({username:username,password:password})
        }).then((response)=>{
            if(response.ok){
                resolve(username);
            }
        }).catch((err)=>{
            reject(err);
        })
    });

}


async function getStudentLectures(username){
    const url= BASE_URL+"studentlectures/"+username;
    const response= await fetch(url);
    const lects_json= await response.json();
    if(response.ok){
        return lects_json.map((l)=>{
            return {lectureId:l.lectureId,numberOfLesson:l.numberOfLesson,
            courseDto:{courseId:l.courseDto.courseId,name:l.courseDto.name,descriptions:l.courseDto.descriptions},
            professorDto:{userId:l.professorDto.userId,name:l.professorDto.name,surname:l.professorDto.surname,address:l.professorDto.address,
            email:l.professorDto.email},remotly:l.remotly,date:l.date,programDetails:l.programDetails,duration:l.duration,roomDto:{roomId:l.roomDto.roomId,name:l.roomDto.name,numberOfSeat:l.roomDto.numberOfSeat}}
        })
    }else{
        throw lects_json;
    }
}


async function getStudentInfo(username){
    let url=BASE_URL+"studentinfo/"+username;
    const response= await fetch(url);
    const studInfo=await response.json();
    if(response.ok){
    return {userId:studInfo.userId,name:studInfo.name, surname:studInfo.surname,
        address:studInfo.address,username:studInfo.email, dateOfBirth:studInfo.dateOfBirth}
    }else{
        throw studInfo;
    }
}

async function addBooking(id,username){
    console.log(id)
    console.log(username)
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


async function getStudentBookings(username){
    let url=BASE_URL+"studentbookings/"+username;
    const response=await fetch(url);
    const bks=await response.json();
    if(response.ok){
        return bks.map((b)=>{return {bookingId:b.bookingId,lectureDto:{lectureId:b.lectureDto.lectureId,
            numberOfLesson:b.lectureDto.numberOfLesson,remotly:b.lectureDto.remotly,
            date:b.lectureDto.date,programDetails:b.lectureDto.programDetails,duration:b.lectureDto.duration},
            courseDto:{courseId:b.lectureDto.courseDto.courseId,name:b.lectureDto.courseDto.name,
            decsriptions:b.lectureDto.courseDto.descriptions},professorDto:{userId:b.lectureDto.professorDto.userId,
            name:b.lectureDto.professorDto.name,surname:b.lectureDto.professorDto.surname,
            address:b.lectureDto.professorDto.address},roomDto:{roomId:b.lectureDto.roomDto.roomId,
            name:b.lectureDto.roomDto.name,numberOfSeat:b.lectureDto.roomDto.numberOfSeat}}});
    }else{
        throw response;
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
        let url=BASE_URL+"teacherinfo/"+username;
        const response= await fetch(url);
        const teacInfo=await response.json();
        if(response.ok){
        return {userId:teacInfo.userId,name:teacInfo.name, surname:teacInfo.surname,
        address:teacInfo.address,username:teacInfo.email, dateOfBirth:teacInfo.dateOfBirth}
        }else{
        throw teacInfo;
		}
}

async function getTeacherLectures(username){
    const url= BASE_URL+"bookings/"+username;
    const response= await fetch(url);
    const lects_json= await response.json();
    if(response.ok){
        return lects_json.map((l)=>{
            return {lectureId:l.lectureId,numberOfLesson:l.numberOfLesson,
            courseDto:{courseId:l.courseDto.courseId,name:l.courseDto.name,descriptions:l.courseDto.descriptions},
            professorDto:{userId:l.professorDto.userId,name:l.professorDto.name,surname:l.professorDto.surname,address:l.professorDto.address,
            email:l.professorDto.email},remotly:l.remotly,date:l.date,programDetails:l.programDetails,duration:l.duration,roomDto:{roomId:l.roomDto.roomId,name:l.roomDto.name,numberOfSeat:l.roomDto.numberOfSeat}}
        })
    }else{
        throw lects_json;
    }
}


const API={loginStudent, loginTeacher, getStudentLectures, getStudentInfo, addBooking,getStudentBookings,cancelBooking,getTeacherInfo,getTeacherLectures}
export default API;
