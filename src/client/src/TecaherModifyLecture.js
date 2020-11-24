import React from 'react';
import API from './API';

class TeacherModifyLecture extends React.Component{
    constructor(props){
        super(props);
        this.state={"deleteError":null}
    }
    deleteLecture=()=>{
        API.teacherDeleteLecture(this.props.lecture.lectureId)
        .then((resp)=>{
            if(resp===true){
                this.props.deleteandback(this.props.lecture.lectureId)
            }else{
                this.setState({'deleteError':true})
            }

        }).catch((err)=>{
            this.setState({'deleteError':err})
        })
    }
    render(){
        const now=new Date();
        const dateshow = new Date(this.props.lecture.date).toLocaleString().slice(0,-3);
        const lectDate=new Date(this.props.lecture.date);
        let diff=0;
        let expired=false;
        lectDate>now?diff=lectDate-now:diff=now-lectDate;
        diff<3600000?expired=true:expired=false;
        return  <>
                <h3>MANAGE YOUR LECTURES</h3>
                    <div className="container border border-success">
                    <h4>Course: {this.props.lecture.courseDto.name}</h4>
                    <h4>Lecture: {this.props.lecture.numberOfLesson}</h4>
                    <h4>Course details: {this.props.lecture.courseDto.description}</h4>
                    <h4>Lecture program: {this.props.lecture.programDetails}</h4>
                    <h4>Lecture duration: {this.props.lecture.duration}</h4>
                    <h4>Lecture schedule date: {dateshow} </h4>
                    {!this.props.lecture.remotly?<><h4>Booked seats for the lesson: {this.props.lecture.bookedSeats}</h4>
                    <h4>Room: {this.props.lecture.roomDto.name}</h4></>
                    :
                    <h4>Lesson Remote</h4>}
                    <h3>DELETE THE LECTURE HERE:</h3>
                    {expired?
                    <h4>We are sorry but you can delete your lesson up to one hour before schedule time</h4>:<>
                    <h4>delete your lesson: <svg width="2em" height="2em" viewBox="0 0 16 16" className="bi bi-trash-fill" fill="red" xmlns="http://www.w3.org/2000/svg" onClick={(ev) => this.deleteLecture()}>
                                                <path fillRule="evenodd" d="M2.5 1a1 1 0 0 0-1 1v1a1 1 0 0 0 1 1H3v9a2 2 0 0 0 2 2h6a2 2 0 0 0 2-2V4h.5a1 1 0 0 0 1-1V2a1 1 0 0 0-1-1H10a1 1 0 0 0-1-1H7a1 1 0 0 0-1 1H2.5zm3 4a.5.5 0 0 1 .5.5v7a.5.5 0 0 1-1 0v-7a.5.5 0 0 1 .5-.5zM8 5a.5.5 0 0 1 .5.5v7a.5.5 0 0 1-1 0v-7A.5.5 0 0 1 8 5zm3 .5a.5.5 0 0 0-1 0v7a.5.5 0 0 0 1 0v-7z"/>
                                            </svg></h4></>}
                    </div>
                    <button type="button" className="btn btn-success" onClick={(ev) => this.props.back()} >BACK</button>
                </>
    }
}

export default TeacherModifyLecture;