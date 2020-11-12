import React from 'react';
import API from './API.js';
import StudentBooking from './StudentBooking';
import {Route,Switch,Link} from 'react-router-dom';
import AppComponents from './AppComponents';

class StudentHomePage extends React.Component {
    constructor(props){
        super(props);
        this.state={'student':null,'errorStudent':null,'errorLectures':null,'lectures':null}
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
        API.addBooking(lectureId, this.props.student)
        .then(()=>API.getStudentLectures(this.props.student)
        .then((lectures)=>{this.setState({lectures:lectures})})
        .catch((err)=>{this.setState({'errorLectures':err})}))
        .catch((err)=>{this.setState({'errorBooking':err})})
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
                <AppComponents.AppNavbar/>
                <div className="container-fluid">
                    <div className="row">
                    <div className="col-3 bg-success" id="sticky-sidebar">
                    <Aside student={this.state.student} />
                    </div>
                    <div className="col-9" id="main">
                    <MainPage lectures={this.state.lectures} addBooking={this.addBooking} student={this.props.student}/>
                    </div>
                    </div>
                </div>
                </Route>
                <Route path="/studentportal/bookings">
                    <StudentBooking student={this.props.student}/>
                </Route>
              </Switch>
        
    }
}

function Aside (props){
    return (
            <div className="container-fluid">
            <h1>Your student info:</h1>
            <h2>{props.student.name} {props.student.surname}</h2>
            <h3>{props.student.address}</h3>
            </div>
    )

}

class MainPage extends React.Component{
    constructor(props){
        super(props);

    }
    showItem= (lecture)=>{
        return <LectureItem key={lecture.lecture_id} lecture={lecture} addBooking={this.addBooking}/>
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
                    <div className="col-2">
                    <h3>LESSON</h3>
                    </div>
                    <div className="col-3">
                    <h3>PROFESSOR</h3>
                    </div>
                    <div className="col-2">
                    <h3>REMOTLY</h3>
                    </div>
                    <div className="col-2">
                    <h3># SEATS</h3>
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
    return (
        <li className="list-group-item" id = {props.lecture.lecture_id}>
        <div className="d-flex w-100 justify-content-between">
            <div className="col-2">
            <h4>{props.lecture.course_name}</h4>
            </div>
            <div className="col-2">
            <h4>{props.lecture.number_of_lesson}</h4>
            </div>
            <div className="col-3">
            <h4>{props.lecture.professor_name}</h4>
            </div>
            <div className="col-2">
            <h4>{props.lecture.remotly}</h4>
            </div>
            <div className="col-2">
            <h4>{props.lecture.number_of_seat}</h4>
            </div>
            <div className="col-1">

            <svg width="2em" height="2em" viewBox="0 0 16 16" className="bi bi-cart-plus-fill" fill="green" xmlns="http://www.w3.org/2000/svg" onClick={(ev) => props.addBooking(props.lecture.lecture_id)}>
                <path fillRule="evenodd" d="M.5 1a.5.5 0 0 0 0 1h1.11l.401 1.607 1.498 7.985A.5.5 0 0 0 4 12h1a2 2 0 1 0 0 4 2 2 0 0 0 0-4h7a2 2 0 1 0 0 4 2 2 0 0 0 0-4h1a.5.5 0 0 0 .491-.408l1.5-8A.5.5 0 0 0 14.5 3H2.89l-.405-1.621A.5.5 0 0 0 2 1H.5zM4 14a1 1 0 1 1 2 0 1 1 0 0 1-2 0zm7 0a1 1 0 1 1 2 0 1 1 0 0 1-2 0zM9 5.5a.5.5 0 0 0-1 0V7H6.5a.5.5 0 0 0 0 1H8v1.5a.5.5 0 0 0 1 0V8h1.5a.5.5 0 0 0 0-1H9V5.5z"/>
            </svg>
            </div>
        </div>
        </li>
        
        )
}


export default StudentHomePage;