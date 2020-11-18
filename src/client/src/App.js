import React  from 'react';
import './App.css';
import StudentLogIn from './StudentLogIn';
import TeacherLogIn from './TeacherLogIn';
import StudentHomePage from './StudentHomePage';
import TeacherHomePage from './TeacherHomePage';
import AppComponents from './AppComponents';
import { Switch,Route,Link,Redirect } from 'react-router-dom';
import API from './API';

class App extends React.Component {
  constructor(props) {
    super(props);
    this.state={student:null, teacher:null, loginError:null};
  }

  loginStudent=(username,password)=>{
    API.loginStudent(username,password).then((usr)=>{
      this.setState({student:usr})}).catch((error)=>this.setState({loginError:error}));
    
  }
  loginTeacher=(username,password)=>{
    API.loginTeacher(username,password).then(()=>{
      this.setState({teacher:username})}).catch((error)=>this.setState({loginError:error}));
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
        <Route path="/studentportal">
          <StudentHomePage student={this.state.student}/>
        </Route>
        <Route exact path="/teacherportal">
        <TeacherHomePage teacher={this.state.teacher}/>
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
