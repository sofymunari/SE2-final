import React from 'react';
import API from './API';

class TeacherModifyLecture extends React.Component{
    constructor(props){
        super(props);
        this.state={"deleteError":null,"remotlyError":null,'lecture':this.props.lecture, 'loading': false};
    }
    deleteLecture=()=>{
        this.setState({'loading':this.state.lecture.lectureId});
        API.teacherDeleteLecture(this.state.lecture.lectureId)
        .then((resp)=>{
            if(resp===true){
                this.props.deleteandback(this.state.lecture.lectureId)
            }else{
                this.setState({'deleteError':true})
            }
        })
        .then(() => {this.setState({'loading':false})}) 
        .catch((err)=>{
            this.setState({'deleteError':err})
        })
    }
    remoteLecture=()=>{
        API.teacherRemoteLecture(this.props.lecture.lectureId)
        .then((resp)=>{
            if(resp===true){
                let lecture = {...this.state.lecture}
                lecture.remotly=true;
                this.setState({'lecture':lecture})
            }else{
                this.setState({'remotlyError':true})
            }

        }).catch((err)=>{
            this.setState({'remotlyError':err})
        })
    }

    render(){
        const now=new Date();
        const dateshow = new Date(this.state.lecture.date).toLocaleString().slice(0,-3);
        const lectDate=new Date(this.state.lecture.date);
        let diff=0;
        let expired=false;
        let expiredremotly=false;
        lectDate>now?diff=lectDate-now:diff=now-lectDate;
        diff<3600000?expired=true:expired=false;
        diff<1800000?expiredremotly=true:expiredremotly=false;
        return  <>
                <h3>MANAGE YOUR LECTURES</h3>
                    <div className="container border border-success">
                    <h4>Course: {this.state.lecture.courseDto.name}</h4>
                    <h4>Lecture: {this.state.lecture.numberOfLesson}</h4>
                    <h4>Course Code: {this.state.lecture.courseDto.code}</h4>
                    <h4>Lecture program: {this.state.lecture.programDetails}</h4>
                    <h4>Lecture duration: {this.state.lecture.duration}</h4>
                    <h4>Lecture schedule date: {dateshow} </h4>
                    {!this.state.lecture.remotly?<><h4>Booked seats for the lesson: {this.state.lecture.bookedSeats}</h4>
                    <h4>Room: {this.state.lecture.roomDto.name}</h4>
                   
                    <h4>TURN YOUR LECTURE TO REMOTE:</h4>
                    {expiredremotly?
                    <h4>We are sorry but you can change your lesson up to  half an hour before schedule time</h4>:<>
                    <h4> make remote: 
                    <svg width="2em" height="2em" viewBox="0 0 16 16" class="bi bi-house-fill" fill="orange" xmlns="http://www.w3.org/2000/svg" onClick={(ev) => this.remoteLecture()}>
                        <path fill-rule="evenodd" d="M8 3.293l6 6V13.5a1.5 1.5 0 0 1-1.5 1.5h-9A1.5 1.5 0 0 1 2 13.5V9.293l6-6zm5-.793V6l-2-2V2.5a.5.5 0 0 1 .5-.5h1a.5.5 0 0 1 .5.5z"/>
                        <path fill-rule="evenodd" d="M7.293 1.5a1 1 0 0 1 1.414 0l6.647 6.646a.5.5 0 0 1-.708.708L8 2.207 1.354 8.854a.5.5 0 1 1-.708-.708L7.293 1.5z"/>
                    </svg>
                                            </h4></>}
                    </>
                    :
                    <h4>Lesson Remote</h4>}
                    <h3>DELETE THE LECTURE HERE:</h3>
                    {expired?
                    <h4>We are sorry but you can delete your lesson up to one hour before schedule time</h4>:<>
                    <h4>delete your lesson: {this.state.loading===this.state.lecture.lectureId?
                    <div className="spinner-border text-success" role="status">
                    <span className="sr-only">Loading...</span>
                    </div>
                    :
                        <svg width="2em" height="2em" viewBox="0 0 16 16" className="bi bi-trash-fill" fill="red" xmlns="http://www.w3.org/2000/svg" onClick={(ev) => this.deleteLecture()}>
                                                <path fillRule="evenodd" d="M2.5 1a1 1 0 0 0-1 1v1a1 1 0 0 0 1 1H3v9a2 2 0 0 0 2 2h6a2 2 0 0 0 2-2V4h.5a1 1 0 0 0 1-1V2a1 1 0 0 0-1-1H10a1 1 0 0 0-1-1H7a1 1 0 0 0-1 1H2.5zm3 4a.5.5 0 0 1 .5.5v7a.5.5 0 0 1-1 0v-7a.5.5 0 0 1 .5-.5zM8 5a.5.5 0 0 1 .5.5v7a.5.5 0 0 1-1 0v-7A.5.5 0 0 1 8 5zm3 .5a.5.5 0 0 0-1 0v7a.5.5 0 0 0 1 0v-7z"/>
                                            </svg>
                    }
                    </h4></>}
                    </div>
                    <button type="button" className="btn btn-success" onClick={(ev) => this.props.back(this.state.lecture)} >BACK</button>
                </>
    }
}

export default TeacherModifyLecture;