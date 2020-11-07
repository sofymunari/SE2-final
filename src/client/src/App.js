import React  from 'react';
import './App.css';
import StudentLogIn from './StudentLogIn';
import TeacherLogIn from './TeacherLogIn';
import { Switch,Route,Link,Redirect } from 'react-router-dom';
import API from './API';

class App extends React.Component {
  constructor(props) {
    super(props);
    this.state={student:null, teacher:null};
  }

  loginStudent=(username,password)=>{
    API.loginStudent(username,password).then(()=>{
      this.setState({"student":username})}).catch((error)=>console.log("error logging in"));
    
  }
  loginTeacher=(username,password)=>{
    API.loginTeacher(username,password).then(()=>{
      this.setState({"teacher":username})}).catch((error)=>console.log("error logging in"));
  }
  
  render(){
   return (
      <div className="App">
        <AppTitle/>
        <Switch>
        <Route exact path="/" >
          <HomePage/>
        </Route>
        <Route exact path="/studentlogin">
          {this.state.student? 
          <Redirect to="/studentportal" />:
          <StudentLogIn loginStudent={this.loginStudent}/>
          }
        </Route>
        <Route exact path="/teacherlogin">
          {this.state.teacher? 
          <Redirect to="/teacherportal" />:
          <TeacherLogIn loginTeacher={this.loginTeacher}/>
          }
        </Route>
        <Route exact path="/studentportal">
          <h1>logged</h1>
        </Route>
        <Route exact path="/teacherportal">
          <h1>logged</h1>
        </Route>
        </Switch>
        <AppFooter/>
      </div>  
      
  );
  }
}

function AppFooter(props){
  return  <div className="jumbotron text-center  mt-3" >
            <p>Contacts</p>
          </div>
}


function AppTitle(props){
  return  <div className="jumbotron text-center  mb-0 pb-3">
              <img src="https://img.icons8.com/nolan/64/university.png"/>
              <h1>TEACHING PORTAL</h1>
                <Switch>
                  <Route exact path="/studentlogin">
                    <p>Welcome to the Student Login Page</p>
                  </Route>
                  <Route exact path="/teacherlogin">
                    <p>Welcome to the Teacher Login Page</p>
                  </Route>
                  <Route path="/">
                    <p>Welcome to the Home Page</p> 
                  </Route>
                </Switch>
                
          </div>
}


function HomePage(props){
  return<>
        <nav className="navbar navbar-expand-sm bg-success navbar-dark mb-3 justify-content-center">
          <div className="collapse navbar-collapse flex-grow-0 " id="collapsibleNavbar">
              <ul className="navbar-nav text-right">
                <li className="nav-item pr-1">
                  <Link to="/studentlogin">
                  <button className="btn btn-success text-dark font-weight-bold" >StudentLogin</button>
                  </Link>
                </li>
                <li className="nav-item pr-1">
                  <Link to="/teacherlogin">
                  <button className="btn btn-success text-dark font-weight-bold" >TeacherLogin</button>
                  </Link>
                </li>
                <li className="nav-item pr-1">
                  <button className="btn btn-success text-dark font-weight-bold" >OfficerLogin</button>
                </li>    
            </ul>
          </div>  
        </nav>

        <div className="container-fluid">
          <div className="row">
            <div className="col-sm-6">
              <h2>About the University</h2>
              <h5>this is the future</h5>
              <p>Some text about the university or whatever..</p>
              <h3>Useful Links</h3>
              <p>navigate to our affiliated universities</p>
              <ul className="nav nav-pills flex-column">
                <li className="nav-item">
                  <a href="https://www.polito.it/">polito web site</a>
                </li>
              </ul>
            </div>
            <div className="col-sm-6">
              <h2>Our Goals</h2>
              <h5>We have wonderful goals</h5>
              
              <p>We want to do something..</p>
              <p>Possibly useful.</p>
              <h2>Our Mission</h2>
              <h5>We have a great ethic</h5>
              <p>we believe in..</p>
              <p>Very cool things</p>
            </div>
          </div>
        </div>
        </>

}

export default App;
