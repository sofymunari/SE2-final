import React from 'react';
import API from './API.js';
import AppComponents from './AppComponents';
import {Route,Switch,Link} from 'react-router-dom';

class RegisterDetails extends React.Component {
    constructor(props){
        super(props);
        this.state={'student':null,'errorStudent':null,'errorLectures':null,'lectures':null}
    }
    render(){
        return <Switch >
                <Route exact path="/teacherportal/registerdetails">
                <AppComponents.AppNavbar/>
                <div className="container-fluid">
                    <div className="row">
                    <div className="col-3 bg-success" id="sticky-sidebar">
                    <Aside course={this.state.course} />
                    </div>
                    <div className="col-9" id="main">
                    <MainPage students={this.state.students} />
                    </div>
                    </div>
                </div>
                </Route>
              </Switch>
       
    }
}

class MainPage extends React.Component{
    constructor(props){
        super(props);
    }
    showItem= (student)=>{
        return <DetailItem key={student.student_id} student={student} />
    }

    render(){
        return  <ul className="list-group list-group-flush">
                    <li className="list-group-item bg-light">
                    <div className="d-flex w-100 justify-content-between">
                    <div className="col-2">
                    <h3>ID</h3>                  
                    </div>
                    <div className="col-2">                  
                    <h3>NAME</h3>
                    </div>
                    <div className="col-2">
                    <h3>SURNAME</h3>
                    </div>
                    <div className="col-2">
                    <h3>EMAIL</h3>
                    </div>
                    <div className="col-2">
                    <h3>DATE</h3>
                    </div>
                    </div>
                    </li>
                    {this.props.students.map(this.showItem)}
                </ul>
    }
}


function Aside (props){
    return (
            <div className="container-fluid">
            <h1>course info:</h1>
            <h2>{props.course.course_name} </h2>
            </div>
    )

}
function DetailItem (props){
    return (
        <li className="list-group-item" id = {props.lecture.lecture_id}>
        <div className="d-flex w-100 justify-content-between">
            <div className="col-2">
            <h4>1</h4>
            </div>
            <div className="col-2">
            <h4>{props.student.name}</h4>
            </div>
            <div className="col-2">
            <h4>{props.student.surname}</h4>
            </div>
            <div className="col-3">
            <h4>{props.student.email}</h4>
            </div>
            <div className="col-2">
            <h4>{props.lecture.date} </h4>
            </div>
        </div>
        </li>       
        )
}

export default RegisterDetails;





