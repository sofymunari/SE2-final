import React  from 'react';
import {Route,Switch,Link} from 'react-router-dom';

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

function AppNavbar(props){
    return <Switch>
            <Route exact path="/">
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
            </Route>
            <Route exact path="/studentportal">
                <nav className="navbar navbar-expand-sm bg-success navbar-dark mb-3 justify-content-center">
                <div className="collapse navbar-collapse flex-grow-0 " id="collapsibleNavbar">
                    <ul className="navbar-nav text-right">
                    <li className="nav-item pr-1">
                        <Link to="/studentportal/bookings">
                        <button className="btn btn-success text-dark font-weight-bold" >Bookings</button>
                        </Link>
                    </li>
                    <li className="nav-item pr-1">
                        <button className="btn btn-success text-dark font-weight-bold" >Something</button>
                    </li>
                    <li className="nav-item pr-1">
                        <button className="btn btn-success text-dark font-weight-bold" >Something</button>
                    </li>    
                    </ul>
                </div>  
                </nav>
            </Route>
            <Route exact path="/studentportal/bookings">
                <nav className="navbar navbar-expand-sm bg-success navbar-dark mb-3 justify-content-center">
                <div className="collapse navbar-collapse flex-grow-0 " id="collapsibleNavbar">
                    <ul className="navbar-nav text-right">
                    <li className="nav-item pr-1">
                        <button className="btn btn-success text-dark font-weight-bold" >Calendar</button>
                    </li>    
                    </ul>
                </div>  
                </nav>
            </Route>

            </Switch>
}
const AppComponents={AppFooter,AppTitle,AppNavbar}
export default AppComponents;
