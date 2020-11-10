import React from 'react';
import API from './API.js';
import AppComponents from './AppComponents';

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

    render(){
        if(!this.state.bookings){
            return <h1>the page is loading</h1>
        }
        return <h1>ok</h1>
    }
}

export default StudentBooking;