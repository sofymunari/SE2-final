import React  from 'react';
import './App.css';
import StudentLogIn from './StudentLogIn';
import TeacherLogIn from './TeacherLogIn';
import ManagerLogIn from './ManagerLogIn';
import SupportOfficerLogIn from './SupportOfficerLogIn';
import StudentHomePage from './StudentHomePage';
import TeacherHomePage from './TeacherHomePage';
import AppComponents from './AppComponents';
import ManagerHomePage from './ManagerHomePage';
import SupportOfficerHomePage from'./SupportOfficerHomePage';
import { Switch,Route,Redirect } from 'react-router-dom';
import API from './API';
 

class App extends React.Component {
  constructor(props) {
    super(props);
    this.state={student:null, teacher:null, loginError:null, manager:null, supportOfficer:null};
  }

  loginStudent=(username,password)=>{
    API.loginStudent(username,password).then((usr)=>{
      this.setState({student:usr,loginError:null})})
      .catch((error)=>{this.setState({loginError:error})});
    
  }

  loginTeacher=(username,password)=>{
    API.loginTeacher(username,password).then((usr)=>{
      this.setState({teacher:usr,loginError:null})})
      .catch((error)=>{this.setState({loginError:error})});
  }

  loginManager=(username,password)=>{
    API.loginManager(username,password).then((usr)=>{
      this.setState({manager:usr,loginError:null})})
      .catch((error)=>{this.setState({loginError:error})});
  }

  loginSupportOfficer = (username,password)=>{
    API.loginSupportOfficer(username, password).then((usr) =>{
      this.setState({supportOfficer: usr, loginError: null})})
      .catch((error) => {this.setState({loginError: error})});
  }

  logOutStudent=()=>{
    this.setState({student:null});
  }

  logOutTeacher=()=>{
    this.setState({teacher:null});
  }

  logOutManager=()=>{
    this.setState({manager:null});
  }

  logoutSupportOfficer = ()=>{
    this.setState({supportOfficer: null});
  }

  render(){ 
   return (
      <div className="App">
        <AppComponents.AppTitle/>
        <Switch>
        <Route exact path="/" >
          <HomePage/>
        </Route>
        <Route exact path="/studentlogin">
          {this.state.student? 
          <Redirect to="/studentportal" />:
          <StudentLogIn loginStudent={this.loginStudent} loginError={this.state.loginError}/>
          }
        </Route>
        <Route exact path="/teacherlogin">
          {this.state.teacher? 
          <Redirect to="/teacherportal" />:
          <TeacherLogIn loginTeacher={this.loginTeacher} loginError={this.state.loginError}/>
          }
        </Route>
        <Route exact path="/managerlogin">
          {this.state.manager? 
          <Redirect to="/managerportal" />:
          <ManagerLogIn loginManager={this.loginManager} loginError={this.state.loginError}/>
          }
        </Route>
        <Route exact path="/supportOfficerlogin">
          {this.state.supportOfficer? 
          <Redirect to="/supportOfficerportal" />:
          <SupportOfficerLogIn loginSupportOfficer={this.loginSupportOfficer} loginError={this.state.loginError}/>
          }
        </Route>
        <Route path="/studentportal">
          {this.state.student?
          <StudentHomePage student={this.state.student}  logOut={this.logOutStudent}/>:
          <Redirect to="/"/>
          }
        </Route>
        <Route path="/teacherportal">
        {this.state.teacher?
          <TeacherHomePage teacher={this.state.teacher}  logOut={this.logOutTeacher}/>:
          <Redirect to="/"/>
        }
        </Route>
        <Route path="/managerportal">
          {this.state.manager?
          <ManagerHomePage manager={this.state.manager}  logOut={this.logOutManager}/>:
          <Redirect to="/"/>
          }
        </Route>
        <Route path="/supportOfficerportal">
          {this.state.supportOfficer?
          <SupportOfficerHomePage supportOfficer={this.state.supportOfficer}  logOut={this.logoutSupportOfficer}/>:
          <Redirect to="/"/>
          }
        </Route>
        </Switch>
        <AppComponents.AppFooter/>
      </div>  
      
  );
  }
}




function HomePage(props){
  return<>
        <AppComponents.AppNavbar/>
        <div className="container-fluid">
          <div className="row">
            <div className="col-sm-6">
              <h2>About the University</h2>
              <h5>this is the future</h5>
              <p>Some text about the university or whatever..</p>
              <h3>Useful Links</h3>
              <ul className="nav nav-pills flex-column">
                 <li className="nav-item">
                  <a target="_blank" href="https://www.youtube.com/watch?v=D2xTQ2kcV4w&t=14s/">STUDENT TUTORIAL</a>
                </li>
                <li className="nav-item">
                  <a target="_blank" href="https://www.youtube.com/watch?v=5_J66dQRUuM&t=10s/">PROFESSOR TUTORIAL</a>
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
