import React from 'react';
import {Route,Switch,Link} from 'react-router-dom'; 
import FullCalendar from "@fullcalendar/react";  
import dayGridPlugin from "@fullcalendar/daygrid";  
import timeGridPlugin from "@fullcalendar/daygrid";  





function StudentBookingCalendar (props) {
    const events=props.bookings.map((b)=>{let title=b.courseDto.name+" "+b.lectureDto.numberOfLessons;let date=new Date(b.lectureDto.date);return {title:title, date:date}});
    return(
            <div className="container">  
            <FullCalendar defaultView="dayGridMonth"  header={{ left: "prev,next", center: "title",  
                right: "dayGridMonth,timeGridWeek,timeGridDay" }}  
                plugins={[dayGridPlugin, timeGridPlugin]}  
                events={events}  
            />  
            </div> ) 
        
    
}

export default StudentBookingCalendar