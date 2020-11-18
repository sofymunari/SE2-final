
import React from 'react';
import RegisterDetails from './RegisterDetails';
import API from './API.js';
import {Route,Switch,Link} from 'react-router-dom';
import AppComponents from './AppComponents';

class Notification extends React.Component {
    constructor(props){
        super(props);
        this.state={'teacher':null,'errorTeacher':null,'errorLectures':null,'lectures':null}
        //teacher prop is the teacher username
        //teacher state is the teacher info

    }
    componentDidMount(){
        API.getNotification(this.props.teacher).then((lectures)=>{
        API.getTeacherInfo(this.props.teacher)
        .then((teacher)=>{this.setState({teacher:teacher,lectures:lectures})})
        .catch((err)=>{this.setState({'errorTeacher':err})})})
        .catch((err)=>{this.setState({'errorLectures':err})})
    }
    render(){
        if(this.state.errorTeacher||this.state.errorLectures){
            return <h2>we are sorry but an error just occurred</h2>
        }
        if(!this.state.teacher || !this.state.lectures){
        return <h1>page is loading</h1>
        }
        return <Switch >
                <Route exact path="/teacherportal/notification">
                <AppComponents.AppNavbar/>
                <div className="container-fluid">
                    <div className="row">
                    <div className="col-3 bg-success" id="sticky-sidebar">
                    <Aside teacher={this.state.teacher} />
                    </div>
                    <div className="col-9" id="main">
                    <MainPage lectures={this.state.lectures} />
                    </div>
                    </div>
                </div>
                </Route>             
              </Switch>        
    }
}

function Aside (props){
    return (
            <div className="container-fluid">
            <h1>teacher info:</h1>
            <h2>{props.teacher.name} {props.teacher.surname}</h2>
            <h3>{props.teacher.address}</h3>
            </div>
    )

}

class MainPage extends React.Component{
    constructor(props){
        super(props);

    }
    showItem= (lecture)=>{
        return <LectureItem key={lecture.lecture_id} lecture={lecture} />
    }
    render(){
        return  <ul className="list-group list-group-flush">
                    <li className="list-group-item bg-light">
                    <div className="d-flex w-100 justify-content-between">
                    <div className="col-3">
                    <h3>COURSE_NAME</h3>
                    </div>
                    <div className="col-2">
                    <h3>#REGISTER</h3>
                    </div>
                    <div className="col-2">
                    <h3>DATE</h3>
                    </div>
                    <div className="col-2">
                    <h3>REMOTLY</h3>
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
            <h4>     
            {props.lecture.number_of_lesson}</h4>
            </div>
            <div className="col-2">
            <h4>{props.lecture.date}</h4>
            </div>
            <div className="col-2">
            <h4>{props.lecture.remotly }</h4>
            </div>
        </div>
        </li>       
        )
}


export default Notification;