import React from 'react';
import API from './API.js';
import AppComponents from './AppComponents';
import { Alert } from 'react-bootstrap';
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

class SupportOfficerHomePage extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            supportOfficer: null, lecturesfile: false, coursesfile: false, studentsfile: false,
            teachersfile: false, classesfile: false, selectedFile: null
        }
    }

    componentDidMount() {
        API.getSupportOfficerInfo(this.props.supportOfficer).then((officer) => {
            this.setState({ 'supportOfficer': officer });
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
        if (event.target.files[0] == null)
            return

        console.log(event.target.files[0].type);
        if (this.checkMimeType(event)) {
            this.setState({
                'lecturesfile': false,
                'coursesfile': false,
                'studentsfile': false,
                'teachersfile': false,
                'classesfile': false
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

        if (this.state.classesfile) {
            API.uploadClassesFile(formData).then(() => {
                this.setState({ /*loaded: true, */selectedFile: null });
                toast.success('upload success');
            }).catch((error) => {
                this.setState({ error: error });
                toast.error('upload fail');
            });
        } else if (this.state.coursesfile) {
            API.uploadCoursesFile(formData).then(() => {
                this.setState({ /*loaded: true,*/ selectedFile: null });
                toast.success('upload success');
            }).catch((error) => {
                this.setState({ error: error });
                toast.error('upload fail');
            });
        } else if (this.state.lecturesfile) {
            API.uploadLecturesFile(formData).then(() => {
                this.setState({ /*loaded: true, */selectedFile: null });
                toast.success('upload success');
            }).catch((error) => {
                this.setState({ error: error });
                toast.error('upload fail');
            });
        } else if (this.state.studentsfile) {
            API.uploadStudentsFile(formData).then(() => {
                this.setState({ /*loaded: true, */selectedFile: null });
                toast.success('upload success');
            }).catch((error) => {
                this.setState({ error: error });
                toast.error('upload fail');
            });
        } else if (this.state.teachersfile) {
            API.uploadProfessorsFile(formData).then(() => {
                this.setState({ /*loaded: true, */selectedFile: null });
                toast.success('upload success');
            }).catch((error) => {
                this.setState({ error: error });
                toast.error('upload fail');
            });
        }
    }

    render() {
        if (this.state.error) {
            return <h1>Connections problems</h1>
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
                    <div class="form-group">
                        <ToastContainer />
                    </div>
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
                                        </form>
                                    </div>
                                </div>
                            </li>
                            <li className="list-group-item" id="files">
                                <div className="d-flex w-100">
                                    <div className="col-2">
                                        <h4>CLASSES</h4>
                                    </div>
                                    <div className="col-2">
                                        <form encType="multipart/form-data">
                                            <input type="file" name="classesfile" onChange={(event) => this.addFile(event, event.target.name)} />
                                            <button type="button" className="btn btn-success btn-block" onClick={(event) => this.uploadFile(event)}>Upload</button>
                                        </form>
                                    </div>
                                </div>
                            </li>
                            <li className="list-group-item" id="files">
                                <div className="d-flex w-100 ">
                                    <div className="col-2">
                                        <h4>LECTURES</h4>
                                    </div>
                                    <div className="col-2">
                                        <form encType="multipart/form-data">
                                            <input type="file" name="lecturesfile" onChange={(event) => this.addFile(event, event.target.name)} />
                                            <button type="button" className="btn btn-success btn-block" onClick={(event) => this.uploadFile(event)}>Upload</button>
                                        </form>
                                    </div>
                                </div>
                            </li>
                        </ul>
                    </div>
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

export default SupportOfficerHomePage;