import React from 'react';
import { Bar } from "react-chartjs-2";

class TeacherStatistics extends React.Component {
    constructor(props){
        super(props)
        this.state={'choosenTab':'All Lectures','passedLectures':[]}
    }
    componentDidMount(){
        let passedLectures=this.props.allLectures.filter((l)=>{
            let now = new Date();
            let date = new Date(l.date);
            if(now>date){
                return true;
            }
            return false;
        });
        this.setState({'passedLectures': passedLectures})    
    }
    selectFilter=(filter)=>{
        this.setState({'choosenTab':filter});
    }
    render(){
        
        return  <div className="row">
                    <aside className="collapse d-sm-block col-sm-2 col-12 bg-light below-nav" id="left-sidebar">
                    <Aside choosenTab={this.state.choosenTab} selectFilter={this.selectFilter}/>
                    </aside>

                    <Main choosenTab={this.state.choosenTab} passedLectures={this.state.passedLectures} bookings={this.props.bookings}/>
                </div>
    }
}

function Main(props){
    return <div className="col-sm-10 pl-0">
                <h2 className="text-center text-success">HERE ARE YOUR STATS FOR {props.choosenTab}</h2>
                {props.choosenTab==='All Lectures'?
                <AllLecturesStats lectures={props.passedLectures} bookings={props.bookings}/>:
                props.choosenTab==='Per Week'?
                <PerWeekStats lectures={props.passedLectures} bookings={props.bookings}/>:
                <PerMonthStats lectures={props.passedLectures} bookings={props.bookings}/>}
            </div>
}

function getWeek(date){
    //gets the number of the week
    let now = new Date(date)	
    let onejan = new Date(now.getFullYear(),0,1);
    let today = new Date(now.getFullYear(),now.getMonth(),now.getDate());
    let dayOfYear = ((today - onejan + 86400000)/86400000);
    return Math.ceil(dayOfYear/7);
}

function getMonth(date){
    let now= new Date(date);
    return now.getMonth();
}

class PerMonthStats extends React.Component{
    constructor(props){
        super(props);
        this.state={'monthStats':null,'showList':true}
    }
    componentDidMount(){
        const lectsStats=this.props.lectures.map((p)=>{
            return {'month':getMonth(p.date),'bookedSeats':p.bookedSeats,'deleted':p.deleted,'remotly':p.remotly,'lectureId':p.lectureId}
        })
        const months=lectsStats.map((ls)=>ls.month);
        const monthsUnique= [...new Set(months)];
        const stats=monthsUnique.map((m)=>{
            let intLects=lectsStats.filter((l)=>l.month===m);
            let avgBookings=0;
            let nDeleted=0;
            let nRemote=0;
            let avgAttended=0;
        
            for (let le of intLects){
                
                const attended = this.props.bookings.filter(b=>{return b.bookingInfo==='ATTENDED'&&b.lectureId === le.lectureId});
                avgAttended = avgAttended+attended.length; 
                avgBookings=avgBookings+le.bookedSeats;
                le.deleted?nDeleted=nDeleted+1:nDeleted=nDeleted;
                le.remotly?nRemote=nRemote+1:nRemote=nRemote;
            }
            
            avgAttended=Math.ceil(avgAttended/intLects.length);
            
            avgBookings=Math.ceil(avgBookings/intLects.length);
            return {'month':m,'avgBookings':avgBookings,'nDeleted':nDeleted,'nRemote':nRemote,'totalLessons': intLects.length, 'avgAttended':avgAttended};
        })
        this.setState({'monthStats':stats});

    }
    showGraph=(value)=>{
        this.setState({"showList":value});
    }

    render(){
            if(!this.state.monthStats){
                return <h2>LOADING</h2>
            }
            if(this.state.showList){
            return      <>
                        <ul className="list-group list-group-flush">
                            <li className="list-group-item bg-light">
                                <div className="d-flex w-100 justify-content-between">
                                <div className="col-2">
                                <h4>MONTH</h4>
                                </div>
                                <div className="col-2">
                                <h4>AVG BOOKINGS</h4>
                                </div>
                                <div className="col-2">
                                <h4>TOTAL LESSONS</h4>
                                </div>
                                <div className="col-2">
                                <h4>TOTAL REMOTE LESSONS</h4>
                                </div>
                                <div className="col-2">
                                <h4>TOTAL DELETED LESSONS</h4>
                                </div>
                                <div className="col-2">
                                <h4>AVG ATTENDENCY</h4>
                                </div>
                                </div>
                                </li>
                            {this.state.monthStats.map((l)=>
                            <MonthStats key={l.month} monthStats={l} />)}
                        </ul>
                        <button type="button" className="btn btn-success" onClick={(ev) => this.showGraph(false)}>Show Graph</button>
                        </>
            }else{
                let lects = this.state.monthStats.map((s)=>{return {'label': months[s.month], 'data': s.avgBookings }})
                let lects2 = this.state.monthStats.map((s)=>{return {'label': months[s.month], 'data': s.avgAttended }})
                return <>
                        <TeacherGraph showGraph={this.showGraph} lectures={lects} title={"Per Month Bookings"}/>
                        <TeacherGraph showGraph={this.showGraph} lectures={lects2} title={"Per Month Attendency"}/>
                    </>
            }
    }
}

const months = [ "January", "February", "March", "April", "May", "June", 
           "July", "August", "September", "October", "November", "December" ];

function MonthStats(props){
    
return  <li className="list-group-item" id = {props.monthStats.month}>
            <div className="d-flex w-100 justify-content-between">
                <div className="col-2">          
                <h5>{months[props.monthStats.month]}</h5>
                </div>
                <div className="col-2">
                <h5>{props.monthStats.avgBookings}</h5>
                </div>
                <div className="col-2">
                <h5>{props.monthStats.totalLessons}</h5>
                </div>
                <div className="col-2">
                <h5>
                {props.monthStats.nRemote}
                </h5>
                </div>
                <div className="col-2">
                <h5> {props.monthStats.nDeleted}</h5>
                </div>
                <div className="col-2">
                <h5> {props.monthStats.avgAttended}</h5>
                </div>
            </div>
        </li>       
}



class PerWeekStats extends React.Component{
    constructor(props){
        super(props);
        this.state={'weekStats':null,"showList":true}
    }
    showGraph=(value)=>{
        this.setState({"showList":value});
    }
    
    componentDidMount(){
        const lectsStats=this.props.lectures.map((p)=>{
            return {'week':getWeek(p.date),'bookedSeats':p.bookedSeats,'deleted':p.deleted,'remotly':p.remotly,'lectureId':p.lectureId}
        })
        const weeks=lectsStats.map((ls)=>ls.week);
        const weeksUnique= [...new Set(weeks)];
        const stats=weeksUnique.map((w)=>{
            let intLects=lectsStats.filter((l)=>l.week===w);
            let size = intLects.length;
            let avgAttended= 0;
            let avgBookings=0;
            let nDeleted=0;
            let nRemote=0;
            
            for (let le of intLects){
                const attended = this.props.bookings.filter(b=>{return b.bookingInfo==='ATTENDED'&&b.lectureId === le.lectureId});
                avgAttended = avgAttended+attended.length; 
                avgBookings=avgBookings+le.bookedSeats;
                le.deleted?nDeleted=nDeleted+1:nDeleted=nDeleted;
                le.remotly?nRemote=nRemote+1:nRemote=nRemote;
            }
            
           
            avgBookings=Math.ceil(avgBookings/size);
            avgAttended=Math.ceil(avgAttended/size);
            
            return {'week':w,'avgBookings':avgBookings,'nDeleted':nDeleted,'nRemote':nRemote,'totalLessons': intLects.length,'avgAttended':avgAttended};
        })
        
        this.setState({'weekStats':stats});
    }

    render(){
        if(!this.state.weekStats){
            return <h2>LOADING</h2>
        }
        if(this.state.showList){
            return <>
                <ul className="list-group list-group-flush">
                    <li className="list-group-item bg-light">
                    <div className="d-flex w-100 justify-content-between">
                    <div className="col-2">
                    <h4>WEEK NUMBER</h4>
                    </div>
                    <div className="col-2">
                    <h4>AVG BOOKINGS</h4>
                    </div>
                    <div className="col-2">
                    <h4>TOTAL LESSONS</h4>
                    </div>
                    <div className="col-2">
                    <h4>TOTAL REMOTE LESSONS</h4>
                    </div>
                    <div className="col-2">
                    <h4>TOTAL DELETED LESSONS</h4>
                    </div>
                    <div className="col-2">
                    <h4>AVG ATTENDENCY</h4>
                    </div>
                    </div>
                    </li>
                {this.state.weekStats.map((l)=>
                <WeekStats key={l.week} weekStats={l} />)}
            </ul>
            <button type="button" className="btn btn-success" onClick={(ev) => this.showGraph(false)}>Show Graph</button>
            </>
        }else{
            let lectures=this.state.weekStats.map((w)=>{return {'label':"week:"+w.week,'data':w.avgBookings}})
            let lectures1=this.state.weekStats.map((w)=>{return {'label':"week:"+w.week,'data':w.avgAttended}})
            return  <>
                    <TeacherGraph lectures={lectures} showGraph={this.showGraph} title={"Per Week Bookings"}/>;
                    <TeacherGraph lectures={lectures1} showGraph={this.showGraph} title={"Per Week Attendency"}/>;
                    </>
        }
    }
}


function WeekStats(props){
    return  <li className="list-group-item" id = {props.weekStats.week}>
                <div className="d-flex w-100 justify-content-between">
                    <div className="col-2">          
                    <h5>{props.weekStats.week}</h5>
                    </div>
                    <div className="col-2">
                    <h5>{props.weekStats.avgBookings}</h5>
                    </div>
                    <div className="col-2">
                    <h5>{props.weekStats.totalLessons}</h5>
                    </div>
                    <div className="col-2">
                    <h5>
                    {props.weekStats.nRemote}
                    </h5>
                    </div>
                    <div className="col-2">
                    <h5> {props.weekStats.nDeleted}</h5>
                    </div>
                    <div className="col-2">
                    <h5> {props.weekStats.avgAttended}</h5>
                    </div>
                </div>
            </li>       
}



class AllLecturesStats extends React.Component {
    constructor(props){
        super(props);
        this.state={"showList":true}
    }
    showGraph=(value)=>{
        this.setState({"showList":value});
    }

    render(){
    let attendency = this.props.bookings.filter(b=>b.bookingInfo==='ATTENDED');
    if(this.state.showList){
    let cancellations = this.props.bookings.filter((b)=>b.bookingInfo==='CANCELED_BY_STUD');
    
    return  <>
            <ul className="list-group list-group-flush">
                <li className="list-group-item bg-light">
                    <div className="d-flex w-100 justify-content-between">
                    <div className="col-2">
                    <h4>COURSE NAME</h4>
                    </div>
                    <div className="col-2">
                    <h4>LECTURE</h4>
                    </div>
                    <div className="col-1">
                    <h4>DATE</h4>
                    </div>
                    <div className="col-2">
                    <h4>BOOKINGS CANCELLED</h4>
                    </div>
                    <div className="col-1">
                    <h4>BOOKED SEATS</h4>
                    </div>
                    <div className="col-2">
                    <h4>ATTENDED</h4>
                    </div>
                    <div className="col-1">
                    <h4>DEL</h4>
                    </div>
                    <div className="col-1">
                    <h4>REMOTE</h4>
                    </div>
                    </div>
                    </li>
                {this.props.lectures.map((l)=>
                <LectureStats key={l.lectureId} lecture={l} bookingCancelled={cancellations.filter((b)=>b.lectureId===l.lectureId).length} attendency={attendency.filter((b)=>b.lectureId===l.lectureId).length}/>)}
            </ul>
            <button type="button" className="btn btn-success" onClick={(ev) => this.showGraph(false)}>Show Graph</button>
            
            </>
        }else{
            let lects=this.props.lectures.map((l)=>{return {'label':l.courseDto.name+" "+l.numberOfLesson,'data':l.bookedSeats}})
            let lects1=this.props.lectures.map((l)=>{return {'label':l.courseDto.name+" "+l.numberOfLesson,'data':attendency.filter((b)=>b.lectureId===l.lectureId).length}})
            return  <>
                    <TeacherGraph lectures={lects} showGraph={this.showGraph} title={"Per Lecture Bookings"}/>
                    <TeacherGraph lectures={lects1} showGraph={this.showGraph} title={"Per Lecture Attendency"}/>
                    </>
        }
    }
}

function LectureStats(props){
    let date = new Date(props.lecture.date).toLocaleString().slice(0,-3);
    return  <li className="list-group-item" id = {props.lecture.lectureId}>
                <div className="d-flex w-100 justify-content-between">
                    <div className="col-2">          
                    <h5>{props.lecture.courseDto.name}</h5>
                    </div>
                    <div className="col-2">
                    <h5>{props.lecture.numberOfLesson}</h5>
                    </div>
                    <div className="col-1">
                    <h5>{date}</h5>
                    </div>
                    <div className="col-2">
                    <h5>
                    {props.bookingCancelled}
                    </h5>
                    </div>
                    <div className="col-1">
                    <h5>{props.lecture.bookedSeats}</h5>
                    </div>
                    <div className="col-2">
                    <h5>{props.attendency}</h5>
                    </div>
                    <div className="col-1">
                    <h5>{props.lecture.deleted?'yes':'no'}</h5>
                    </div>
                    <div className="col-1">
                    <h5>{props.lecture.remotly?'yes':'no'}</h5>
                    </div>

    </div>
    </li>       
}

function Aside(props) {
    let options=['All Lectures', 'Per Week', 'Per Month']
    return  <div className="list-group bg-success">
                { options.map((f)=><Filter key={f} name={f} cliked={props.choosenTab===f ? true:false} selectFilter={props.selectFilter}/>)}
            </div>

}

function Filter(props){
    return <button  data-id ={props.name} id={props.name} className=' list-group-item list-group-item-action bg-success font-weight-bold' onClick={(ev) => props.selectFilter(props.name)}>{props.name}</button>;
}


class TeacherGraph extends React.Component{
    constructor(props){
        super(props);
        this.state={ dataBar: {
            labels: [...this.props.lectures.map((l)=>{return l.label;})],
            datasets: [{label: this.props.title,
                data: [...this.props.lectures.map((l)=>{return l.data;})],
                backgroundColor: [
                  "rgba(255, 0,0,0.4)",
                  "rgba(0, 0,255,0.4)",
                  "rgba(60, 179,113,0.4)",
                  "rgba(106, 90,205,0.4)"
                ],
                borderWidth: 2,
                borderColor: [
                  "rgba(255, 0, 0, 1)",
                  "rgba(0, 0,255,1)",
                  "rgba(60, 179,113,1)",
                  "rgba(106, 90,205,1)"
                ]}]},
          barChartOptions: {scales: {xAxes: [{
                  barPercentage: 0.5,
                  gridLines: {
                    display: true,
                    color: "rgba(0, 0, 0, 0.1)"
                  }}],yAxes: [{
                  gridLines: {
                    display: true,
                    color: "rgba(0, 0, 0, 0.1)"
                  },
                  ticks: {
                    beginAtZero: true
                  }}]}}
                }
    }
    render(){
        return (
        <div className="container">
        
            <h3 className="mt-5">Bookings {this.props.title}</h3>
            <Bar data={this.state.dataBar} options={this.state.barChartOptions} />
        
        <button type="button" className="btn btn-success" onClick={(ev) => this.props.showGraph(true)}>Show List</button>
        </div>
        )
    }
}


export default TeacherStatistics;