import React from 'react';
import {Route,Switch} from 'react-router-dom';


class TeacherLogin extends React.Component {
    constructor(props) {
        super(props);
        this.state = {username: '', password: ''};
    }

    updateField = (name, value) => {
        
        this.setState({[name]: value});
    }
    
    handleSubmit = () => {
        if (!this.form.checkValidity()) {
            this.form.reportValidity();
        } else {
            this.props.loginTeacher(this.state.username,this.state.password);
            
        }
    }

    render() {
        return( <div className="row" >

                <div className="col-3"></div>
                <div className="col-6">
                <div className="container bg-success p-5 mt-3" >
                <form method="POST" onSubmit={(event) => event.preventDefault()} action="" id="addForm" ref={form => this.form = form}>  
                <div className="form-group">  
                    <div className="col">
                        <label htmlFor="form_description">Username</label>
                        <input type="text" className="form-control input-lg" name="username" 
                            placeholder="Type your username..." id="form_description" 
                            value = {this.state.username}
                            onChange={(ev) => this.updateField(ev.target.name, ev.target.value)}
                            required/>
                    </div>
                    <div className="col">
                        <label htmlFor="form_project">Password</label>
                        <input type="password" className="form-control input-lg" name="password" 
                            placeholder="Type your password..." id="form_project"
                            value = {this.state.password}
                            onChange={(ev) => this.updateField(ev.target.name, ev.target.value)}/>
                    </div>
                </div>                
                <div className="form-group">
                    <div>
                        <button type="submit" className="btn btn-primary border-0 bg-success text-dark" onClick = {() => this.handleSubmit()}>LOGIN</button>
                    </div>
                </div>
                </form>
                </div>
                {this.props.loginError?<h2><span className="badge badge-secondary bg-danger">wrong username or password</span></h2>:null}
                </div>
                </div>
                ); 
    }


}

export default TeacherLogin;