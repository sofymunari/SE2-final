import React from 'react';
import API from './API.js';
import AppComponents from './AppComponents';
import StudentBookingCalendar from './StudentBookingCalendar';
import {Route,Switch} from 'react-router-dom';

class StudentBooking extends React.Component{
    constructor(props){
        super(props);
        this.state={bookings:null,bookingsError:null}
    }
    componentDidMount(){
        API.getStudentBookings(this.props.student)
        .then((bookings)=>this.setState({bookings:bookings}))
        .catch((error)=>this.setState({bookingsError:error}))
    }
    showItem= (booking)=>{
        return <BookingItem key={booking.bookingId} booking={booking} cancelBooking={this.cancelBooking} />
    }
    cancelBooking= (bookingId)=>{
        API.cancelBooking(bookingId)
        .then(()=> API.getStudentBookings(this.props.student)
        .then((bookings)=>{this.props.updateLectures(); this.setState({bookings:bookings})})
        .catch((error)=>this.setState({bookingsError:error})))
        .catch((error)=>this.setState({bookingsError:error}));
    }
    
    render(){
        if(!this.state.bookings){
            return <h1>the page is loading</h1>
        }
        return  <Switch>
                    <Route exact path="/studentportal/bookings">
                        <AppComponents.AppNavbar logOut={this.props.logOut}/>
                        <div className="row">
                        <ul className="list-group list-group-flush col-12">
                            <li className="list-group-item bg-light">
                            <div className="d-flex w-100 justify-content-between">
                            <div className="col-2">
                            <h3>COURSE</h3>
                            </div>
                            <div className="col-2">
                            <h3>LESSON</h3>
                            </div>
                            <div className="col-2">
                            <h3>DATE</h3>
                            </div>
                            <div className="col-2">
                            <h3>REMOTLY</h3>
                            </div>
                            <div className="col-2">
                            <h3># SEATS</h3>
                            </div>
                            <div className="col-1">
                            <h3>CANCEL</h3>
                            </div>
                            </div>
                            </li>
                            {this.state.bookings.map(this.showItem)}
                        </ul>
                        </div>
                    </Route>
                    <Route exact path="/studentportal/bookings/calendar">
                        <StudentBookingCalendar bookings={this.state.bookings}/>
                    </Route>
                </Switch>
    }
}

function BookingItem(props){
    return  <li className="list-group-item" id = {props.booking.bookingId}>
            <div className="d-flex w-100 justify-content-between">
                <div className="col-2">
                <h4>{props.booking.courseDto.name}</h4>
                </div>
                <div className="col-2">
                <h4>{props.booking.lectureDto.numberOfLesson}</h4>
                </div>
                <div className="col-2">
                <h4>{props.booking.lectureDto.date}</h4>
                </div>
                <div className="col-2">
                <h4>{props.booking.lectureDto.remotly?'yes':'no'}</h4>
                </div>
                <div className="col-2">
                <h4>{props.booking.roomDto.numberOfSeat}</h4>
                </div>
                <div className="col-1">
                            <svg width="2em" height="2em" viewBox="0 0 16 16" className="bi bi-trash-fill" fill="red" xmlns="http://www.w3.org/2000/svg" onClick={(ev) => props.cancelBooking(props.booking.bookingId)}>
                                <path fillRule="evenodd" d="M2.5 1a1 1 0 0 0-1 1v1a1 1 0 0 0 1 1H3v9a2 2 0 0 0 2 2h6a2 2 0 0 0 2-2V4h.5a1 1 0 0 0 1-1V2a1 1 0 0 0-1-1H10a1 1 0 0 0-1-1H7a1 1 0 0 0-1 1H2.5zm3 4a.5.5 0 0 1 .5.5v7a.5.5 0 0 1-1 0v-7a.5.5 0 0 1 .5-.5zM8 5a.5.5 0 0 1 .5.5v7a.5.5 0 0 1-1 0v-7A.5.5 0 0 1 8 5zm3 .5a.5.5 0 0 0-1 0v7a.5.5 0 0 0 1 0v-7z"/>
                            </svg>
                </div>
            </div>
            </li>
}

export default StudentBooking;