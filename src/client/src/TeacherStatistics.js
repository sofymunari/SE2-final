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
    console.log(props.passedLectures);
    return <div className="col-sm-10 pl-0">
                <h2 className="text-center text-success">HERE ARE YOUR STATS FOR {props.choosenTab}</h2>
                {props.choosenTab==='All Lectures'?
                <AllLecturesStats lectures={props.passedLectures} bookings={props.bookings}/>:
                props.choosenTab==='Per Week'?
                <PerWeekStats lectures={props.passedLectures} bookings={props.bookings}/>:
                <PerMonthStats lectures={props.passedLectures}/>}
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
            return {'month':getMonth(p.date),'bookedSeats':p.bookedSeats,'deleted':p.deleted,'remotly':p.remotly}
        })
        const months=lectsStats.map((ls)=>ls.month);
        const monthsUnique= [... new Set(months)];
        const stats=monthsUnique.map((m)=>{
            let intLects=lectsStats.filter((l)=>l.month===m);
            let avgBookings=0;
            let nDeleted=0;
            let nRemote=0;
            for (let le of intLects){
                avgBookings=avgBookings+le.bookedSeats;
                le.deleted?nDeleted=nDeleted+1:nDeleted=nDeleted;
                le.remotly?nRemote=nRemote+1:nRemote=nRemote;
            }
            avgBookings=Math.ceil(avgBookings/intLects.length);
            return {'month':m,'avgBookings':avgBookings,'nDeleted':nDeleted,'nRemote':nRemote,'totalLessons': intLects.length};
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
                                <div className="col-3">
                                <h4>TOTAL REMOTE LESSONS</h4>
                                </div>
                                <div className="col-3">
                                <h4>TOTAL DELETED LESSONS</h4>
                                </div>
                                </div>
                                </li>
                            {this.state.monthStats.map((l)=>
                            <MonthStats key={l.month} monthStats={l} />)}
                        </ul>
                        <button type="button" class="btn btn-success" onClick={(ev) => this.showGraph(false)}>Show Graph</button>
                        </>
            }else{
                let lects = this.state.monthStats.map((s)=>{return {'label': months[s.month], 'data': s.avgBookings }})
                return <TeacherGraph showGraph={this.showGraph} lectures={lects} title={"Per Month"}/>
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
                <div className="col-3">
                <h5>
                {props.monthStats.nRemote}
                </h5>
                </div>
                <div className="col-3">
                <h5> {props.monthStats.nDeleted}</h5>
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
            return {'week':getWeek(p.date),'bookedSeats':p.bookedSeats,'deleted':p.deleted,'remotly':p.remotly}
        })
        const weeks=lectsStats.map((ls)=>ls.week);
        const weeksUnique= [... new Set(weeks)];
        const stats=weeksUnique.map((w)=>{
            let intLects=lectsStats.filter((l)=>l.week===w);
            let avgBookings=0;
            let nDeleted=0;
            let nRemote=0;
            for (let le of intLects){
                avgBookings=avgBookings+le.bookedSeats;
                le.deleted?nDeleted=nDeleted+1:nDeleted=nDeleted;
                le.remotly?nRemote=nRemote+1:nRemote=nRemote;
            }
            avgBookings=Math.ceil(avgBookings/intLects.length);
            return {'week':w,'avgBookings':avgBookings,'nDeleted':nDeleted,'nRemote':nRemote,'totalLessons': intLects.length};
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
                    <div className="col-3">
                    <h4>TOTAL REMOTE LESSONS</h4>
                    </div>
                    <div className="col-3">
                    <h4>TOTAL DELETED LESSONS</h4>
                    </div>
                    </div>
                    </li>
                {this.state.weekStats.map((l)=>
                <WeekStats key={l.week} weekStats={l} />)}
            </ul>
            <button type="button" class="btn btn-success" onClick={(ev) => this.showGraph(false)}>Show Graph</button>
            </>
        }else{
            let lectures=this.state.weekStats.map((w)=>{return {'label':"week:"+w.week,'data':w.avgBookings}})
            return <TeacherGraph lectures={lectures} showGraph={this.showGraph} title={"Per Week"}/>;
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
                    <div className="col-3">
                    <h5>
                    {props.weekStats.nRemote}
                    </h5>
                    </div>
                    <div className="col-3">
                    <h5> {props.weekStats.nDeleted}</h5>
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
        if(this.state.showList){
    let cancellations = this.props.bookings.filter((b)=>b.bookingInfo==='CANCELLED_BY_STUD');
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
                    <div className="col-2">
                    <h4>DATE</h4>
                    </div>
                    <div className="col-2">
                    <h4>BOOKINGS CANCELLED</h4>
                    </div>
                    <div className="col-2">
                    <h4>BOOKED SEATS</h4>
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
                <LectureStats key={l.lectureId} lecture={l} bookingCancelled={cancellations.filter((b)=>b.lectureId===l.lectureId).length}/>)}
            </ul>
            <button type="button" class="btn btn-success" onClick={(ev) => this.showGraph(false)}>Show Graph</button>
            
            </>
        }else{
            let lects=this.props.lectures.map((l)=>{return {'label':l.courseDto.name+" "+l.numberOfLesson,'data':l.bookedSeats}})
            return <TeacherGraph lectures={lects} showGraph={this.showGraph} title={"Per Lecture"}/>
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
                    <div className="col-2">
                    <h5>{date}</h5>
                    </div>
                    <div className="col-2">
                    <h5>
                    {props.bookingCancelled}
                    </h5>
                    </div>
                    <div className="col-2">
                    <h5>{props.lecture.bookedSeats}</h5>
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
    return <button  data-id ={props.name} className=' list-group-item list-group-item-action bg-success font-weight-bold' onClick={(ev) => props.selectFilter(props.name)}>{props.name}</button>;
}


class TeacherGraph extends React.Component{
    constructor(props){
        super(props);
        this.state={ dataBar: {
            labels: [... this.props.lectures.map((l)=>{return l.label;})],
            datasets: [{label: "Number of bookings "+this.props.title,
                data: [... this.props.lectures.map((l)=>{return l.data;})],
                backgroundColor: [
                  "rgba(255, 134,159,0.4)"
                ],
                borderWidth: 2,
                borderColor: [
                  "rgba(255, 134, 159, 1)",
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

function TeacherGraphPerMonth(props){
    return <button type="button" className="btn btn-success" onClick={(ev) => props.showGraph(true)}>Show List</button>
}

export default TeacherStatistics;