import React from 'react';

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
                <PerWeekStats/>:
                <PerMonthStats/>}
            </div>
}
function PerWeekStats(props){
    return <h1>Hello</h1>
}
function PerMonthStats(props){
    return <h1>Hello</h1>
}

function AllLecturesStats (props){
    let cancellations = props.bookings.filter((b)=>b.bookingInfo==='CANCELLED_BY_STUD');
    return  <ul className="list-group list-group-flush">
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
                {props.lectures.map((l)=>
                <LectureStats key={l.lectureId} lecture={l} bookingCancelled={cancellations.filter((b)=>b.lectureId===l.lectureId).length}/>)}
            </ul>
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



export default TeacherStatistics;