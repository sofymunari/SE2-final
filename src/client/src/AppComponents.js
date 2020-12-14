import React  from 'react';
import {Route,Switch,Link} from 'react-router-dom';

function AppFooter(props){
    return  <div className="jumbotron text-center  mt-3" >
              <p>Contacts</p>
            </div>
}
  
  

function AppTitle(props){
    return  <div className="jumbotron text-center  mb-0 pb-3">
                <img src="https://img.icons8.com/nolan/64/university.png" alt=""/>
                  <Switch>
                    <Route path="/studentlogin">
                      <h1>STUDENT PORTAL</h1>  
                      <p>Welcome to the Student Login Page</p>
                    </Route>
                    <Route path="/teacherlogin">
                      <h1>PROFESSOR PORTAL</h1>  
                      <p>Welcome to the Teacher Login Page</p>
                    </Route>
                    <Route path="/managerlogin">
                        <h1>MANAGER LOGIN</h1>
                        <p>Welcome to the Manager Login Page</p>
                    </Route>
                    <Route path="/supportOfficerlogin">
                        <h1>SUPPORT OFFICER LOGIN</h1>
                        <p>Welcome to the Support Officer Login Page</p>
                    </Route>
                    <Route path="/studentportal">
                        <h1>STUDENT PORTAL</h1>
                        <p>Welcome to the Student Portal Page</p>
                    </Route>
                    <Route path="/managerportal">
                        <h1>MANAGER PORTAL</h1>
                        <p>Welcome to the Manager Portal Page</p>
                    </Route>
                    <Route path="/teacherportal">
                        <h1>TEACHER PORTAL</h1>
                        <p>Welcome to the Teacher Portal Page</p>
                    </Route>
                    <Route exact path="/">
                      <h1>WESTERN UNIVERSITY</h1>  
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
                        <button className="btn btn-success text-dark font-weight-bold mr-4" >Student</button>
                        </Link>
                        </li>
                        <li className="nav-item pr-1">
                        <Link to="/teacherlogin">
                        <button className="btn btn-success text-dark font-weight-bold mr-4" >Teacher</button>
                        </Link>
                        </li>
                        <li className="nav-item pr-1">
                        <Link to="/managerlogin">
                        <button className="btn btn-success text-dark font-weight-bold" >Manager</button>
                        </Link>
                        </li>    
                        <li className="nav-item pr-1">
                        <Link to="/supportOfficerlogin">
                        <button className="btn btn-success text-dark font-weight-bold" >Support Officer</button>
                        </Link>
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
                        <button className="btn btn-lg btn-success text-dark font-weight-bold" >Bookings</button>
                        </Link>
                    </li>
                    
                    <li className="nav-item pr-1">
                        <button className="btn btn-lg btn-success text-dark font-weight-bold" onClick={ev=>props.logOut()} >LOG OUT</button>
                    </li>    
                    </ul>
                </div>  
                </nav>
            </Route>
            <Route exact path="/studentportal/bookings">
                <nav className="navbar navbar-expand-sm bg-success navbar-dark mb-3 justify-content-center">
                <div className="collapse navbar-collapse flex-grow-0 btn-lg" id="collapsibleNavbar">
                    <ul className="navbar-nav text-right">
                    <li className="nav-item pr-1">
                        <Link to="/studentportal/bookings/calendar">
                        <svg width="2em" height="2em" viewBox="0 0 16 16" className="bi bi-calendar-week-fill" fill="black" xmlns="http://www.w3.org/2000/svg">
                            <path fillRule="evenodd" d="M4 .5a.5.5 0 0 0-1 0V1H2a2 2 0 0 0-2 2v1h16V3a2 2 0 0 0-2-2h-1V.5a.5.5 0 0 0-1 0V1H4V.5zM16 14V5H0v9a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2zM9.5 7a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5h-1zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5h-1zM2 10.5a.5.5 0 0 1 .5-.5h1a.5.5 0 0 1 .5.5v1a.5.5 0 0 1-.5.5h-1a.5.5 0 0 1-.5-.5v-1zm3.5-.5a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5h-1z"/>
                        </svg>
                        </Link>
                    </li>
                    
                    <li className="nav-item pr-1">
                        <button className="btn btn-success text-dark font-weight-bold" onClick={(ev) => props.logOut()} >LOG OUT</button>
                    </li>     
                    </ul>
                </div>  
                </nav>
            </Route>
            <Route exact path="/teacherportal">
                <nav className="navbar navbar-expand-sm bg-success navbar-dark mb-3 justify-content-center">
                <div className="collapse navbar-collapse flex-grow-0 " id="collapsibleNavbar">
                    <ul className="navbar-nav text-right">
                    <li className="nav-item pr-1">       
                    <Link to="/teacherportal/notifications">             
                    <svg width="1.5em" height="1.5em" viewBox="0 0 16 16" className="bi bi-bell-fill mr-3 mt-2" fill="black" xmlns="http://www.w3.org/2000/svg">
                        <path d="M8 16a2 2 0 0 0 2-2H6a2 2 0 0 0 2 2zm.995-14.901a1 1 0 1 0-1.99 0A5.002 5.002 0 0 0 3 6c0 1.098-.5 6-2 7h14c-1.5-1-2-5.902-2-7 0-2.42-1.72-4.44-4.005-4.901z"/>
                    </svg>
                    </Link >
                    </li>
                    <Link to="/teacherportal/statistics">
                    <li className="nav-item pr-1">
                        <button className="btn btn-success text-dark font-weight-bold" >STATS</button>
                    </li>
                    </Link>
                    <li className="nav-item pr-1">
                        <button className="btn btn-success text-dark font-weight-bold" onClick={(ev) => props.logOut()} >LOG OUT</button>
                    </li>     
                    </ul>
                </div>  
                </nav>
            </Route> 
            <Route exact path="/managerportal">
                <nav className="navbar navbar-expand-sm bg-success navbar-dark mb-3 justify-content-center">
                <div className="collapse navbar-collapse flex-grow-0 " id="collapsibleNavbar">
                    <ul className="navbar-nav text-right">
                    <li className="nav-item pr-1">
                        <button className="btn btn-success text-dark font-weight-bold" onClick={(ev) => props.logOut()} >LOG OUT</button>
                    </li>     
                    </ul>
                </div>  
                </nav>
            </Route> 
            <Route path="/supportOfficerportal">
                <nav className="navbar navbar-expand-sm bg-success navbar-dark mb-3 justify-content-center">
                <div className="collapse navbar-collapse flex-grow-0 " id="collapsibleNavbar">
                    <ul className="navbar-nav text-right">
                    <Link to="/supportOfficerportal">
                    <li className="nav-item pr-1">
                        <button className="btn btn-success text-dark font-weight-bold" >UPLOAD FILE</button>
                    </li>
                    </Link>
                    <Link to="/supportOfficerportal/updatelectures">
                    <li className="nav-item pr-1">
                        <button className="btn btn-success text-dark font-weight-bold" >UPDATE LECTURES</button>
                    </li>
                    </Link>
                    <li className="nav-item pr-1">
                        <button className="btn btn-success text-dark font-weight-bold" onClick={(ev) => props.logOut()} >LOG OUT</button>
                    </li>     
                    </ul>
                </div>  
                </nav>
            </Route> 
            </Switch>
}
const AppComponents={AppFooter,AppTitle,AppNavbar}
export default AppComponents;
