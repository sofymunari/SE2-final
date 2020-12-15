import React from 'react';
import API from './API.js';
import StudentBooking from './StudentBooking';
import {Route,Switch} from 'react-router-dom';
import AppComponents from './AppComponents';

class StudentHomePage extends React.Component {
    constructor(props){
        super(props);
        this.state={'student':null,'errorStudent':null,'errorLectures':null,'lectures':null,'loading':false}
        //student prop is the student username
        //student state is the student info
    }
    componentDidMount(){
        API.getStudentLectures(this.props.student).then((lectures)=>{
        API.getStudentInfo(this.props.student)
        .then((student)=>{this.setState({student:student,lectures:lectures})})
        .catch((err)=>{this.setState({'errorStudent':err})})})
        .catch((err)=>{this.setState({'errorLectures':err})})
    }
    addBooking=(lectureId)=>{
        this.setState({loading:lectureId})
        API.addBooking(lectureId, this.props.student)
        .then(()=>API.getStudentLectures(this.props.student)
        .then((lectures)=>{this.setState({'lectures':lectures,'loading':false})})
        .catch((err)=>{this.setState({'errorLectures':err})}))
        .catch((err)=>{this.setState({'errorBooking':err})})
    }
    updateLectures=()=>{
        API.getStudentLectures(this.props.student).then((lectures)=>{
            this.setState({lectures:lectures})});
    }
    render(){
        if(this.state.errorStudent||this.state.errorLectures){
            return <h2>we are sorry but an error just occurred</h2>
        }
        if(!this.state.student || !this.state.lectures){
        return <h1>page is loading</h1>
        }
        return <Switch >
                <Route exact path="/studentportal">
                <AppComponents.AppNavbar logOut={this.props.logOut}/>
                <div className="container-fluid">
                    <div className="row">
                    <div className="col-2 bg-success" id="sticky-sidebar">
                    <Aside student={this.state.student} />
                    </div>
                    <div className="col-10 p-0" id="main">
                    <MainPage lectures={this.state.lectures} addBooking={this.addBooking} student={this.props.student} loading={this.state.loading}/>
                    </div>
                    </div>
                </div>
                </Route>
                <Route path="/studentportal/bookings">
                    <StudentBooking lectures={this.state.lectures} student={this.props.student} logOut={this.props.logOut} updateLectures={this.updateLectures}/>
                </Route>
              </Switch>
        
    }
}

function Aside (props){
    return (
            <div className="container-fluid">
            <h3>Student:</h3>
            <h4 id="matricola">{props.student.matricola}</h4>
            <h4>{props.student.name} {props.student.surname}</h4>
            <h4>{props.student.address}</h4>
            </div>
    )

}

class MainPage extends React.Component{
    constructor(props){
        super(props);

    }
    showItem= (lecture)=>{
        return <LectureItem key={lecture.lectureId} lecture={lecture} addBooking={this.addBooking} loading={this.props.loading}/>
    }
    addBooking=(lectureId)=>{
        this.props.addBooking(lectureId,this.props.student);
    }
    render(){
        return  <ul className="list-group list-group-flush">
                    <li className="list-group-item bg-light">
                    <div className="d-flex w-100 justify-content-between">
                    <div className="col-2">
                    <h3>COURSE</h3>
                    </div>
                    <div className="col-1">
                    <h3>LESSON</h3>
                    </div>
                    <div className="col-2">
                    <h3>PROFESSOR</h3>
                    </div>
                    <div className="col-2">
                    <h3>REMOTLY</h3>
                    </div>
                    <div className="col-1">
                    <h3>DATE</h3>
                    </div>
                    <div className="col-2">
                    <h3>AVAILABLE SEATS </h3>
                    </div>
                    <div className="col-1">
                    <h3>BOOK</h3>
                    </div>
                    </div>
                    </li>
                    {this.props.lectures.map(this.showItem)}
                </ul>
    }
}

function LectureItem (props){
    var lectureDate = new Date(props.lecture.date);
    var date = lectureDate.toLocaleString().slice(0,-3);
    var today = new Date();

    /*If a lecture is in the past (before today) it is not shown*/
    lectureDate.setHours(0,0,0,0);
    today.setHours(0,0,0,0);
    if(lectureDate.getTime() < today.getTime()) return <></>;
    
    /*If a lecture is cancelled (deleted=false) it is not shown*/
    if(props.lecture.deleted) return <></>;

    return (
        <li className="list-group-item" id = {props.lecture.lectureId}>
        <div className="d-flex w-100 justify-content-between">
            <div className="col-2">
            <h4>{props.lecture.courseDto.name}</h4>
            </div>
            <div className="col-1">
            <h4>{props.lecture.numberOfLesson}</h4>
            </div>
            <div className="col-2">
            <h4>{props.lecture.professorDto.name} {props.lecture.professorDto.surname}</h4>
            </div>
            <div className="col-2">
            <h4>{props.lecture.remotly?'yes':'no'}</h4>
            </div>
            <div className="col-1">
            <h4>{date}</h4>
            </div>
            <div className="col-2">
            <h4>{props.lecture.remotly?"-":props.lecture.roomDto.numberOfSeat-props.lecture.bookedSeats}</h4>
            </div>
            <div className="col-1">
            {
                props.lecture.remotly?
                   <p></p>:
                   props.loading===props.lecture.lectureId?
                    <div className="spinner-border text-success" role="status">
                    <span className="sr-only">Loading...</span>
                    </div>
                   :
                   <svg width="2em" height="2em" viewBox="0 0 16 16" className="bi bi-bookmark-plus" fill="green" xmlns="http://www.w3.org/2000/svg" onClick={(ev) => props.addBooking(props.lecture.lectureId)}>
                   <path fillRule="evenodd" d="M2 2a2 2 0 0 1 2-2h8a2 2 0 0 1 2 2v13.5a.5.5 0 0 1-.777.416L8 13.101l-5.223 2.815A.5.5 0 0 1 2 15.5V2zm2-1a1 1 0 0 0-1 1v12.566l4.723-2.482a.5.5 0 0 1 .554 0L13 14.566V2a1 1 0 0 0-1-1H4z"/>
                    <path fillRule="evenodd" d="M8 4a.5.5 0 0 1 .5.5V6H10a.5.5 0 0 1 0 1H8.5v1.5a.5.5 0 0 1-1 0V7H6a.5.5 0 0 1 0-1h1.5V4.5A.5.5 0 0 1 8 4z"/>
                   </svg>   
            }   
            </div>
        </div>
        </li>
        
        )
}


export default StudentHomePage;