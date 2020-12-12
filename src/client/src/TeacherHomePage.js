import React from 'react';
import TeacherNotification from './TeacherNotification';
import TeacherModifyLecture from './TecaherModifyLecture';
import TeacherStatistics from './TeacherStatistics';
import API from './API.js';
import { Route, Switch, Link } from 'react-router-dom';
import AppComponents from './AppComponents';
import Alert from 'react-bootstrap/Alert'

class TeacherHomePage extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            'teacher': null, 'errorTeacher': null, 'students': null, 'errorLectures': null,
            'bookings': null, 'lectures': null, 'modifylect': null, 'allLectures': null, 'lectureId': null
        }
        //teacher prop is the teacher username
        //teacher state is the teacher info

    }

    componentDidMount() {
        this.init();

    }

    async init() {
        const bookings = await API.getTeacherBookings(this.props.teacher);
        const teacher = await API.getTeacherInfo(this.props.teacher);
        const allLectures = await API.getTeacherLectures(this.props.teacher);
        //allLectures are all lectures of a teacher
        //lectures are the lecture of the teacher which are not passed nor deleted
        const lectures = allLectures.filter((l) => {
            if (l.deleted) {
                return false;
            }
            const now = new Date();
            const lectDate = new Date(l.date);
            if (lectDate < now) {
                return false;
            }
            return true;
        })
        this.setState({ teacher: teacher, bookings: bookings, lectures: lectures, allLectures: allLectures })
    }

    modifyLecture = (lectureId) => {
        const lecture = this.state.lectures.filter((l) => l.lectureId === lectureId).pop();
        this.setState({ modifylect: lecture });
    }

    showBookings = (lectureId) => {
        const students = this.state.bookings.filter(b => b.lectureId === lectureId);
        this.setState({ students: students, lectureId: lectureId });
    }

    deleteandback = (lectureId) => {
        const lectures = this.state.lectures.filter(l => l.lectureId !== lectureId);
        const allLectures = this.state.allLectures.map(l => {
            let lect = { ...l };
            if (lect.lectureId === lectureId) {
                lect.deleted = true;
            }
            return lect;
        })
        this.setState({ students: null, modifylect: null, lectures: lectures, allLectures: allLectures })

    }
    back = (lecture) => {
        const lectures = this.state.lectures.map(l => {
            if (l.lectureId === lecture.lectureId) {
                l.remotly = lecture.remotly;
            }
            let newL = { ...l }
            return newL;
        })
        const allLectures = this.state.allLectures.map(l => {
            if (l.lectureId === lecture.lectureId) {
                l.remotly = lecture.remotly;
            }
            let newL = { ...l }
            return newL;
        })

        this.setState({ students: null, modifylect: null, lectures: lectures, allLectures: allLectures })
    }

    backList = () => {
        this.setState({ students: null, modifylect: null });
    }

    render() {
        if (this.state.errorTeacher || this.state.errorLectures) {
            return <h2>we are sorry but an error just occurred</h2>
        }
        if (!this.state.teacher) {
            return <h1>page is loading</h1>
        }
        return <Switch >
            <Route exact path="/teacherportal">
                <AppComponents.AppNavbar logOut={this.props.logOut} />
                <div className="container-fluid">
                    <div className="row">
                        <div className="col-2 bg-success" id="sticky-sidebar">
                            <Aside teacher={this.state.teacher} />
                        </div>
                        <div className="col-10" id="main">
                            {this.state.modifylect ?
                                <TeacherModifyLecture back={this.back} deleteandback={this.deleteandback} lecture={this.state.modifylect} /> :
                                this.state.students ?
                                    <StudentBookingList students={this.state.students} back={this.backList} lectureId={this.state.lectureId} /> :
                                    <MainPage lectures={this.state.lectures} bookings={this.state.bookings} showBookings={this.showBookings} modifyLecture={this.modifyLecture} />}
                        </div>
                    </div>
                </div>
            </Route>
            <Route exact path="/teacherportal/statistics">
                <TeacherStatistics allLectures={this.state.allLectures} lectures={this.state.lectures} bookings={this.state.bookings} />
            </Route>
            <Route exact path="/teacherportal/notifications">
                <TeacherNotification teacher={this.state.teacher} />
            </Route>
        </Switch>

    }
}

function Aside(props) {
    return (
        <div className="container-fluid">
            <h1>teacher info:</h1>
            <h2>{props.teacher.name} {props.teacher.surname}</h2>
            <h3>{props.teacher.address}</h3>
        </div>
    )

}


class MainPage extends React.Component {


    constructor(props) {
        super(props);

    }
    showItem = (lecture) => {
        return <LectureItem key={lecture.lectureId} lecture={lecture} showBookings={this.props.showBookings} modifyLecture={this.props.modifyLecture} />
    }

    render() {
        return <ul className="list-group list-group-flush">
            <li className="list-group-item bg-light">
                <div className="d-flex w-100 justify-content-between">
                    <div className="col-2">
                        <h3>COURSE NAME</h3>
                    </div>
                    <div className="col-2">
                        <h3>LECTURE</h3>
                    </div>
                    <div className="col-2">
                        <h3>DATE</h3>
                    </div>
                    <div className="col-2">
                        <h3>ROOM</h3>
                    </div>
                    <div className="col-2">
                        <h3>BOOKED SEATS</h3>
                    </div>
                    <div className="col-1">
                        <h3>SEE LIST</h3>
                    </div>
                    <div className="col-1">
                        <h3></h3>
                    </div>
                </div>
            </li>
            {this.props.lectures.map(this.showItem)}
        </ul>
    }
}


function LectureItem(props) {
    /*extracting date (format dd/mm/yyyy) and time of lecture from props*/
    let date = new Date(props.lecture.date).toLocaleString().slice(0, -3);

    return (
        <li className="list-group-item" id={props.lecture.lectureId}>
            <div className="d-flex w-100 justify-content-between">
                <div className="col-2">
                    <h4>{props.lecture.courseDto.name}</h4>
                </div>
                <div className="col-2">
                    <h4>{props.lecture.numberOfLesson}</h4>
                </div>
                <div className="col-2">
                    <h4>{date}</h4>
                </div>
                <div className="col-2">
                    <h4>
                        {props.lecture.remotly ? "Remote" : props.lecture.roomDto.name}
                    </h4>
                </div>
                <div className="col-2">
                    <h4>{props.lecture.remotly ? "-" : props.lecture.bookedSeats}</h4>
                </div>
                <div className="col-1">
                    {props.lecture.bookedSeats > 0 ? <svg width="2em" height="2em" viewBox="0 0 16 16" className="bi bi-list-check" fill="green" xmlns="http://www.w3.org/2000/svg" onClick={(ev) => props.showBookings(props.lecture.lectureId)}>
                        <path fillRule="evenodd" d="M5 11.5a.5.5 0 0 1 .5-.5h9a.5.5 0 0 1 0 1h-9a.5.5 0 0 1-.5-.5zm0-4a.5.5 0 0 1 .5-.5h9a.5.5 0 0 1 0 1h-9a.5.5 0 0 1-.5-.5zm0-4a.5.5 0 0 1 .5-.5h9a.5.5 0 0 1 0 1h-9a.5.5 0 0 1-.5-.5zM3.854 2.146a.5.5 0 0 1 0 .708l-1.5 1.5a.5.5 0 0 1-.708 0l-.5-.5a.5.5 0 1 1 .708-.708L2 3.293l1.146-1.147a.5.5 0 0 1 .708 0zm0 4a.5.5 0 0 1 0 .708l-1.5 1.5a.5.5 0 0 1-.708 0l-.5-.5a.5.5 0 1 1 .708-.708L2 7.293l1.146-1.147a.5.5 0 0 1 .708 0zm0 4a.5.5 0 0 1 0 .708l-1.5 1.5a.5.5 0 0 1-.708 0l-.5-.5a.5.5 0 0 1 .708-.708l.146.147 1.146-1.147a.5.5 0 0 1 .708 0z" />
                    </svg> : <p></p>}

                </div>
                <div className="col-1">
                    <svg width="2em" height="2em" viewBox="0 0 16 16" className="bi bi-pencil-square" fill="green" xmlns="http://www.w3.org/2000/svg" onClick={(ev) => props.modifyLecture(props.lecture.lectureId)}>
                        <path d="M15.502 1.94a.5.5 0 0 1 0 .706L14.459 3.69l-2-2L13.502.646a.5.5 0 0 1 .707 0l1.293 1.293zm-1.75 2.456l-2-2L4.939 9.21a.5.5 0 0 0-.121.196l-.805 2.414a.25.25 0 0 0 .316.316l2.414-.805a.5.5 0 0 0 .196-.12l6.813-6.814z" />
                        <path fillRule="evenodd" d="M1 13.5A1.5 1.5 0 0 0 2.5 15h11a1.5 1.5 0 0 0 1.5-1.5v-6a.5.5 0 0 0-1 0v6a.5.5 0 0 1-.5.5h-11a.5.5 0 0 1-.5-.5v-11a.5.5 0 0 1 .5-.5H9a.5.5 0 0 0 0-1H2.5A1.5 1.5 0 0 0 1 2.5v11z" />
                    </svg>
                </div>

            </div>
        </li>
    )
}

class StudentBookingList extends React.Component {
    constructor(props) {
        super(props);
        this.state = { 'attendencesSet': false, 'studentsChecked': [], 'studentsNumber': 0 };
    }

    handleCheck = (ev, studentEmail) => {
        // every time a value changes check the validity
        let vet = this.state.studentsChecked;
        let n = this.state.studentsNumber;
        if (ev.target.checked) {
            vet.push(studentEmail);
            n += 1;
            this.setState({ studentsChecked: vet, studentsNumber: n });
        } else {
            let index = vet.indexOf(studentEmail);
            vet.splice(index, 1);
            n -= 1;
            this.setState({ studentsChecked: vet, studentsNumber: n });
        }
    }

    sendAttendences = () => {
        if (this.state.studentsChecked.length > 0) {
            API.setAttendences(this.state.studentsChecked, this.props.lectureId)
                .then(() => {
                    this.setState({ attendencesSet: true, studentsChecked: [] });
                })
                .catch((error) => this.setState({ error: error }));
        }

    }


    showStudent = (student) => {
        return <StudentItem key={student.bookingId} student={student} handleCheck={this.handleCheck} />
    }

    render() {
        return <>
            {
                this.state.attendencesSet &&
                <Alert transition={null} className='col-6 mt-2 mx-auto'
                    onClose={() => this.setState({ attendencesSet: false })}
                    variant='success'
                    dismissible>
                    Attendences set!
        </Alert>

            }
            <h1>STUDENT LIST FOR COURSE {this.props.students[0].course.name} LESSON NUMBER {this.props.students[0].lectureNumber}</h1>
            <ul className="list-group list-group-flush">
                <li className="list-group-item bg-light">
                    <div className="d-flex w-100 justify-content-between">
                        <div className="col-2">
                            <h3>STUDENT NAME</h3>
                        </div>
                        <div className="col-2">
                            <h3>STUDENT SURNAME</h3>
                        </div>
                        <div className="col-2">
                            <h3>STUDENT EMAIL</h3>
                        </div>
                        <div className="col-2">
                            <h3>BOOKING STATUS</h3>
                        </div>
                        <div className="col-2">
                            <h3>ADD PRESENCE</h3>
                        </div>
                    </div>
                </li>
                {this.props.students.map(this.showStudent)}
            </ul>
            <button type="button" className="btn btn-success" onClick={(ev) => this.props.back()} >BACK</button>
            <button type="button" className="btn btn-success" onClick={(ev) => this.sendAttendences()} > SEND ATTENDENCES</button>
        </>
    }
}

function StudentItem(props) {

    return (
        <li className="list-group-item" id={props.student.lectureId}>
            <div className="d-flex w-100 justify-content-between">
                <div className="col-2">
                    <h4>{props.student.studentName}</h4>
                </div>
                <div className="col-2">
                    <h4>{props.student.studentSurname}</h4>
                </div>
                <div className="col-2">
                    <h4>{props.student.studentEmail}</h4>
                </div>
                <div className="col-2">
                    <h4>{props.student.bookingInfo}</h4>
                </div>
                {
                    props.student.bookingInfo === "ATTENDED" ?
                        <div className="col-2">
                            <input name="presente" type="checkbox" disabled checked/>
                        </div>
                        :
                        <div className="col-2">
                            <input name="presente" type="checkbox" onChange={(ev) => props.handleCheck(ev, props.student.studentEmail)} />
                        </div>
                }
            </div>
        </li>
    )
}


export default TeacherHomePage;