import React from 'react';
import API from './API.js';
import AppComponents from './AppComponents';
import { Button, Form, Col, Modal } from 'react-bootstrap'
import { Redirect, Route, Switch, Link } from 'react-router-dom';
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

class SupportOfficerHomePage extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            supportOfficer: null, lecturesfile: false, coursesfile: false, studentsfile: false,
            teachersfile: false, enrollmentfile: false, holidaysfile: false, selectedFile: null,
            coursesPressed: false, studentsPressed: false, teachersPressed: false, enrollmentPressed: false,
            selectedCourse: '', courses: [], loaded: false, lecturesPressed: false, holidaysPressed: false,
            lectures: [], modifySchedule: false
        }
    }

    componentDidMount() {
        API.getSupportOfficerInfo(this.props.supportOfficer).then((officer) => {
            this.setState({ 'supportOfficer': officer });
        }).catch((error) => this.setState({ 'error': error }));
        API.getCourses().then((courses) => {
            this.setState({ courses: courses })
        }).catch((error) => this.setState({ 'error': error }));
        API.getLectures().then((lectures) => {
            this.setState({ 'lectures': lectures });
        }).catch((error) => this.setState({ 'error': error }));
    }

    checkMimeType = (event) => {
        let file = event.target.files[0];
        const types = ['application/vnd.ms-excel', 'text/csv'];

        if (types.every(type => file.type !== type)) {
            toast.error('upload fail, invalid file type');
            return false;
        }

        this.setState({ selectedFile: null });

        return true;
    }

    addFile = (event, name) => {
        event.preventDefault();
        if (event.target.files[0] == null) {
            this.setState({ selectedFile: null })
            return;
        }

        if (this.checkMimeType(event)) {
            this.setState({
                'lecturesfile': false,
                'coursesfile': false,
                'studentsfile': false,
                'teachersfile': false,
                'enrollmentfile': false,
                'holidaysfile': false
            });
            this.setState({
                'selectedFile': event.target.files[0],
                [name]: true
            });
        }
    }

    uploadFile = (event) => {
        event.preventDefault();
        if (this.state.selectedFile == null) {
            toast.error('upload fail, no file selected of invalid type');
            return;
        }

        const formData = new FormData();

        formData.append("file", this.state.selectedFile, this.state.selectedFile.name);

        if (this.state.enrollmentfile) {
            this.setState({ enrollmentPressed: true })
            API.uploadEnrollmentFile(formData).then(() => {
                this.setState({ enrollmentPressed: false, selectedFile: null });
                toast.success('upload success');
            }).catch((error) => {
                this.setState({ error: error, enrollmentPressed: false });
                toast.error('upload fail');
            });
        } else if (this.state.coursesfile) {
            this.setState({ coursesPressed: true })
            API.uploadCoursesFile(formData).then(() => {
                this.setState({ coursesPressed: false, selectedFile: null });
                toast.success('upload success');
            }).catch((error) => {
                this.setState({ error: error, coursesPressed: false });
                toast.error('upload fail');
            });
        } else if (this.state.lecturesfile) {
            this.setState({ lecturesPressed: true })
            API.uploadLecturesFile(formData).then(() => {
                this.setState({ lecturesPressed: false, selectedFile: null });
                toast.success('upload success');
            }).catch((error) => {
                this.setState({ error: error, lecturesPressed: false });
                toast.error('upload fail');
            });
        } else if (this.state.studentsfile) {
            this.setState({ studentsPressed: true })
            API.uploadStudentsFile(formData).then(() => {
                this.setState({ studentsPressed: false, selectedFile: null });
                toast.success('upload success');
            }).catch((error) => {
                this.setState({ error: error, studentsPressed: false });
                toast.error('upload fail');
            });
        } else if (this.state.teachersfile) {
            this.setState({ teachersPressed: true })
            API.uploadProfessorsFile(formData).then(() => {
                this.setState({ teachersPressed: false, selectedFile: null });
                toast.success('upload success');
            }).catch((error) => {
                this.setState({ error: error, teachersPressed: false });
                toast.error('upload fail');
            });
        } else if (this.state.holidaysfile) {
            this.setState({ holidaysPressed: true })
            API.uploadHolidaysFile(formData).then(() => {
                this.setState({ holidaysPressed: false, selectedFile: null });
                toast.success('upload success');
            }).catch((error) => {
                this.setState({ error: error, holidaysPressed: false });
                toast.error('upload fail');
            });
        }
    }


    onChangeHandler = (name, value) => {
        // every time a value changes check the validity
        this.setState({ [name]: value });
    }

    sendCourses = (event, string) => {
        event.preventDefault();
        if (string == null) {
            toast.error('update fail, no course selected');
            return;
        }
        //        let vet = this.state.courses.map((c) => c.courseName.toLowerCase());
        if (string === "first") {
            let first = this.state.courses.filter((c) => c.courseYear === 1).map((c) => c.courseCode);
            if (first.length > 0) {
                API.sendCourses(first)
                    .then(() => {
                        toast.success('update success');
                    })
                    .catch((error) => {
                        toast.error('update fail');
                        //  this.setState({ error: error});
                    });
            } else {
                toast.error('No course to update');
            }
        } else if (string === "second") {
            let second = this.state.courses.filter((c) => c.courseYear === 2).map((c) => c.courseCode);
            if (second.length > 0) {
                API.sendCourses(second)
                    .then(() => {
                        toast.success('update success');
                    })
                    .catch((error) => {
                        // this.setState({ error: error});
                        toast.error('update fail');
                    });
            } else {
                toast.error('No course to update');
            }
        } else if (string === "third") {
            let third = this.state.courses.filter((c) => c.courseYear === 3).map((c) => c.courseCode);
            if (third.length > 0) {
                API.sendCourses(third)
                    .then(() => {
                        toast.success('update success');
                    })
                    .catch((error) => {
                        //  this.setState({ error: error});
                        toast.error('update fail');
                    });
            } else {
                toast.error('No course to update');
            }
        } else {
            let id = string.split(",")[0];
            API.sendCourse(id)
                .then(() => {
                    toast.success('update success');
                })
                .catch((error) => {
                    // this.setState({ error: error}); 
                    toast.error('update fail');
                });
        }
    }

    showItem = (course) => {
        return (
            <li className="list-group-item" >
                <div className="d-flex w-100 justify-content-between">
                    <div className="col-2">
                        <h4>{course.courseName}</h4>
                    </div>
                    <div className="col-2">
                        <Button id={course.courseId} onClick={(ev) =>{}}> Update </Button>
                    </div>
                </div>
            </li>

        )

    }

    render() {
        if (this.state.error) {
            return <>
                <h1>Connections problems</h1>
                <a href="/"> HOME </a>
            </>
        }
        if (!this.state.supportOfficer) {
            return <h1>LOADING</h1>
        }
        return <>
            <AppComponents.AppNavbar logOut={this.props.logOut} />
            <div className="container-fluid">
                <div className="row">
                    <div className="col-2 bg-success" id="sticky-sidebar">
                        <Aside supportOfficer={this.state.supportOfficer} />
                    </div>
                    {/* {
                        this.state.loaded &&
                        <Alert transition={null} className='fixed-top col-2 mt-2 mx-auto'
                            onClose={() => this.setState({ loaded: false, reload: true })}
                            variant='success'
                            dismissible>
                            File successfully uploaded!
                        </Alert>
                    } */}
                    <div className="form-group">
                        <ToastContainer />
                    </div>
                    <Switch>
                        <Route exact path="/supportOfficerportal">
                            <div className="col-10 p-0" id="main">
                                <h4>Choose what type of file you want to upload (csv only)</h4>
                                <ul className="list-group list-group-flush">
                                    <li className="list-group-item bg-light" >
                                        <div className="d-flex w-100 ">
                                            <div className="col-2">
                                                <h4>STUDENTS</h4>
                                            </div>
                                            <div className="col-2">
                                                <form encType="multipart/form-data">
                                                    <input type="file" name="studentsfile" onChange={(event) => this.addFile(event, event.target.name)} />
                                                    <button type="button" className="btn btn-success btn-block" onClick={(event) => this.uploadFile(event)}>Upload</button>
                                                    <div className="form-group">
                                                        {
                                                            this.state.studentsPressed ?
                                                                <h6>Uploading</h6>
                                                                :
                                                                <> </>
                                                        }
                                                    </div>
                                                </form>
                                            </div>
                                        </div>
                                    </li>
                                    <li className="list-group-item bg-light" >
                                        <div className="d-flex w-100 ">
                                            <div className="col-2">
                                                <h4>TEACHERS</h4>
                                            </div>
                                            <div className="col-2">
                                                <form encType="multipart/form-data">
                                                    <input type="file" name="teachersfile" onChange={(event) => this.addFile(event, event.target.name)} />
                                                    <button type="button" className="btn btn-success btn-block" onClick={(event) => this.uploadFile(event)}>Upload</button>
                                                    <div className="form-group">
                                                        {
                                                            this.state.teachersPressed ?
                                                                <h6>Uploading</h6>
                                                                :
                                                                <> </>
                                                        }
                                                    </div>
                                                </form>
                                            </div>
                                        </div>
                                    </li>
                                    <li className="list-group-item bg-light" >
                                        <div className="d-flex w-100 ">
                                            <div className="col-2">
                                                <h4>COURSES</h4>
                                            </div>
                                            <div className="col-2">
                                                <form encType="multipart/form-data">
                                                    <input type="file" name="coursesfile" onChange={(event) => this.addFile(event, event.target.name)} />
                                                    <button type="button" className="btn btn-success btn-block" onClick={(event) => this.uploadFile(event)}>Upload</button>
                                                    <div className="form-group">
                                                        {
                                                            this.state.coursesPressed ?
                                                                <h6>Uploading</h6>
                                                                :
                                                                <> </>
                                                        }
                                                    </div>
                                                </form>
                                            </div>
                                        </div>
                                    </li>
                                    <li className="list-group-item bg-light">
                                        <div className="d-flex w-100">
                                            <div className="col-2">
                                                <h4>ENROLLMENT</h4>
                                            </div>
                                            <div className="col-2">
                                                <form encType="multipart/form-data">
                                                    <input type="file" name="enrollmentfile" onChange={(event) => this.addFile(event, event.target.name)} />
                                                    <button type="button" className="btn btn-success btn-block" onClick={(event) => this.uploadFile(event)}>Upload</button>
                                                    <div className="form-group">
                                                        {
                                                            this.state.enrollmentPressed ?
                                                                <h6>Uploading</h6>
                                                                :
                                                                <> </>
                                                        }
                                                    </div>
                                                </form>
                                            </div>
                                        </div>
                                    </li>
                                    <li className="list-group-item bg-light">
                                        <div className="d-flex w-100 ">
                                            <div className="col-2">
                                                <h4>LECTURES</h4>
                                            </div>
                                            <div className="col-2">
                                                <form encType="multipart/form-data">
                                                    <input type="file" name="lecturesfile" onChange={(event) => this.addFile(event, event.target.name)} />
                                                    <button type="button" className="btn btn-success btn-block" onClick={(event) => this.uploadFile(event)}>Upload</button>
                                                    <div className="form-group">
                                                        {
                                                            this.state.lecturesPressed ?
                                                                <h6>Uploading</h6>
                                                                :
                                                                <> </>
                                                        }
                                                    </div>
                                                </form>
                                            </div>
                                        </div>
                                    </li>
                                    <li className="list-group-item bg-light">
                                        <div className="d-flex w-100 ">
                                            <div className="col-2">
                                                <h4>HOLIDAYS (yyyy-mm-dd) </h4>
                                            </div>
                                            <div className="col-2">
                                                <form encType="multipart/form-data">
                                                    <input type="file" name="holidaysfile" onChange={(event) => this.addFile(event, event.target.name)} />
                                                    <button type="button" className="btn btn-success btn-block" onClick={(event) => this.uploadFile(event)}>Upload</button>
                                                    <div className="form-group">
                                                        {
                                                            this.state.holidaysPressed ?
                                                                <h6>Uploading</h6>
                                                                :
                                                                <> </>
                                                        }
                                                    </div>
                                                </form>
                                            </div>
                                        </div>
                                    </li>
                                </ul>
                            </div>
                        </Route>
                        <Route exact path="/supportOfficerportal/updatelectures">
                            <div className="col-10 p-0 " id="main">
                                <>
                                    <h3>Which lectures do you want to move remotely?</h3>
                                    <Form onSubmit={(event) => this.sendCourses(event, "first")}>
                                        <div className="form-group">
                                            <Button variant="primary" type="submit">
                                                First year
                                            </Button>
                                        </div>
                                    </Form>
                                    <Form onSubmit={(event) => this.sendCourses(event, "second")}>
                                        <div className="form-group">
                                            <Button variant="primary" type="submit">
                                                Second year
                                            </Button>
                                        </div>
                                    </Form>
                                    <Form onSubmit={(event) => this.sendCourses(event, "third")}>
                                        <div className="form-group">
                                            <Button variant="primary" type="submit">
                                                Third year
                                            </Button>
                                        </div>
                                    </Form>
                                    <h6> Or Select the Course you want to move remotly</h6>
                                    <Form onSubmit={(event) => this.sendCourses(event, this.state.selectedCourse)}>
                                        <Form.Row className="align-items-center">
                                            <Col className="my-1 ">
                                                <Form.Control as="select" name="selectedCourse" value={this.state.selectedCourse} onChange={(ev) => this.onChangeHandler(ev.target.name, ev.target.value)} >
                                                    {
                                                        this.state.courses.map((c) => <option key={c.courseId}> {c.courseCode}, {c.courseName} </option>)
                                                    }
                                                </Form.Control>
                                            </Col>
                                            <Col xs="auto" className="my-1">
                                                <Button type="submit">Submit</Button>
                                            </Col>
                                        </Form.Row>
                                    </Form>
                                </>
                            </div>
                        </Route>
                        <Route exact path="/supportOfficerportal/updateSchedule">
                            <CourseList />
                        </Route>
                        <Route exact path="/supportOfficerportal/updateSchedule/:courseCode" render={({ match }) => {
                            return <>
                                <ModifySchedule courseCode={match.params.courseCode} />
                            </>;
                        }} />
                    </Switch>
                </div>
            </div >
        </>;
    }
}

function Aside(props) {
    return (
        <div className="container-fluid">
            <h3>Support Officer</h3>
            <h4>{props.supportOfficer.name} {props.supportOfficer.surname}</h4>
            <h4>{props.supportOfficer.address}</h4>
        </div>
    )

}

class CourseList extends React.Component {
    constructor(props) {
        super(props);
        this.state = { courses: [] };
    }
    componentDidMount() {
        API.getCourses().then((courses) => {
            this.setState({ courses: courses })
        }).catch((error) => this.setState({ 'error': error }));
    }

    render() {
        return (
            <div className="col-10 p-0 " id="main">
                <h3>Choose the course you want to update</h3>
                <ul className="list-group list-group-flush">
                    <li className="list-group-item bg-light">
                        <div className="d-flex w-100 justify-content-between">
                            <div className="col-2">
                                <h3>COURSE ID</h3>
                            </div>
                            <div className="col-2">
                                <h3>COURSE NAME</h3>
                            </div>
                            <div className="col-2">
                                <h3></h3>
                            </div>
                        </div>
                    </li>
                    {
                        this.state.courses.map((c) =>
                            <CourseItem key={c.courseId} course={c} />
                        )
                    }
                </ul>
            </div>
        )
    }
}

function CourseItem(props) {
    return (
        <li className="list-group-item" >
            <div className="d-flex w-100 justify-content-between">
                <div className="col-2">
                    <h4>{props.course.courseCode}</h4>
                </div>
                <div className="col-2">
                    <h4>{props.course.courseName}</h4>
                </div>
                <div className="col-2">
                    <Link to={`/supportOfficerportal/updateSchedule/${props.course.courseCode}`}> Edit </Link>
                </div>
            </div>
        </li>

    )

}

class ModifySchedule extends React.Component {
    constructor(props) {
        super(props);
        this.state = { schedules: [], rooms:[], courseCode: props.courseCode, showModal: false,
                    day: 'Mon', duration: '60', roomId:'0', timeStart: '8:30', scheduleId: null};
    }


    componentDidMount() {
        API.getSchedules(this.state.courseCode)
            .then((schedules) => {
                this.setState({ schedules: schedules });
            }).catch((error) => {
                this.setState({ 'error': error })
            });
        API.getRooms()
            .then((rooms) => {
                this.setState({ rooms: rooms});
            }).catch((error) => {
                this.setState({ 'error': error })
            });
    }

    onChangeHandler = (name, value) => {
        this.setState({ [name]: value });
    }

    showModal = (id) => {
        this.setState({ showModal: true, scheduleId: id});
    }

    closeModal = () => {
        this.setState({ showModal: false, scheduleId: null });
    }

    submitHandle = (ev) =>{
        ev.preventDefault();
        API.editSchedule(this.state.day, this.state.duration, this.state.roomId, this.state.courseCode, this.state.timeStart, this.state.scheduleId)
        .then(() =>{
            toast.success('edit success');
            this.setState({showModal: false, scheduleId: null, day: 'Mon', duration: '60', roomId:'0', timeStart: '8:30'});
        }).catch((error) => {
            toast.error('edit error');
            this.setState({ 'error': error })
        });

   }

    render() {

        return (
            <div className="col-10 p-0 " id="main">
                <h3>Choose the schedule you want to edit for the course</h3>
                <ul className="list-group list-group-flush">
                    <li className="list-group-item bg-light">
                        <div className="d-flex w-100 justify-content-between">
                            <div className="col-2">
                                <h3>DAY OF THE WEEK</h3>
                            </div>
                            <div className="col-2">
                                <h3>DURATION</h3>
                            </div>
                            <div className="col-2">
                                <h3>STARTING TIME</h3>
                            </div>
                            <div className="col-2">
                                <h3>ROOM</h3>
                            </div>
                            <div className="col-2">
                                <h3></h3>
                            </div>
                        </div>
                    </li>
                    {
                        this.state.schedules.map((s) =>
                            <ScheduleList key={s.day} schedule={s} showModal={this.showModal} />
                        )
                    }
                </ul>
                <Modal show={this.state.showModal} onHide={this.closeModal}>
                    <Modal.Header closeButton>
                        <Modal.Title>Edit the schedule</Modal.Title>
                    </Modal.Header>
                    <Modal.Body>
                        <Form onSubmit={this.submitHandle}>
                            <Form.Row className="align-items-center">
                                <Col  className="my-1">
                                    <Form.Label className="mr-sm-2" htmlFor="inlineFormCustomSelect" srOnly>
                                        Day
                                    </Form.Label>
                                    <Form.Control
                                        as="select"
                                        name="day"
                                        required
                                        value={this.state.day}
                                        custom
                                        onChange={(ev) => this.onChangeHandler(ev.target.name, ev.target.value)}
                                    >
                                        <option>Mon</option>
                                        <option>Tue</option>
                                        <option>Wed</option>
                                        <option>Thu</option>
                                        <option>Fri</option>
                                    </Form.Control>
                                </Col>
                                <Col className="my-1">
                                    <Form.Label className="mr-sm-2" htmlFor="inlineFormCustomSelect" srOnly>
                                        Duration
                                    </Form.Label>
                                    <Form.Control
                                        as="select"
                                        required
                                        name="duration"
                                        value={this.state.duration}
                                        onChange={(ev) => this.onChangeHandler(ev.target.name, ev.target.value)}
                                        custom
                                    >
                                        {/* <option>60</option> */}
                                        <option>90</option>
                                        {/* <option>120</option>
                                        <option>150</option> */}
                                        <option>180</option>
                                    </Form.Control>
                                </Col>
                                <Col  className="my-1">
                                    <Form.Label className="mr-sm-2" htmlFor="inlineFormCustomSelect" srOnly>
                                        StartingTime
                                    </Form.Label>
                                    <Form.Control
                                        as="select"
                                        value={this.state.timeStart}
                                        required
                                        name="timeStart"
                                        custom
                                        onChange={(ev) => this.onChangeHandler(ev.target.name, ev.target.value)}
                                    >
                                        <option >8:30</option>
                                        <option >10:00</option>
                                        <option >11:30</option>
                                        <option >13:00</option>
                                        <option >14:30</option>
                                        <option >16:00</option>
                                        <option >17:30</option>
                                    </Form.Control>
                                </Col>
                                <Col  className="my-1">
                                    <Form.Label className="mr-sm-2" htmlFor="inlineFormCustomSelect" srOnly>
                                        Room
                                    </Form.Label>
                                    <Form.Control
                                        as="select"
                                        value={this.state.roomId}
                                        required
                                        name="roomId"
                                        custom
                                        onChange={(ev) => this.onChangeHandler(ev.target.name, ev.target.value)}
                                    >
                                        {
                                            this.state.rooms.map((r) => <option value={r.roomId} key={r.roomId}>{r.name}</option>)
                                        }
                                    </Form.Control>
                                </Col>
                                <Col className="my-1">
                                    <Button type="submit">Submit</Button>
                                </Col>
                            </Form.Row>        
                        </Form>
                    </Modal.Body>
                </Modal>
            </div>
        )

    }
}

function ScheduleList(props) {
    return (
        <li className="list-group-item" >
            <div className="d-flex w-100 justify-content-between">
                <div className="col-2">
                    <h4>{props.schedule.day}</h4>
                </div>
                <div className="col-2">
                    <h4>{props.schedule.duration}</h4>
                </div>
                <div className="col-2">
                    <h4>{props.schedule.timeStart}</h4>
                </div>
                <div className="col-2">
                    <h4>{props.schedule.roomName}</h4>
                </div>
                <div className="col-2">
                    <Button onClick={() => props.showModal(props.schedule.scheduleId)}> Edit </Button>
                </div>
            </div>
        </li>
    )
}

export default SupportOfficerHomePage;