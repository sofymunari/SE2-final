import React from 'react';
import API from './API.js';
import {Route,Switch,Link} from 'react-router-dom';
import AppComponents from './AppComponents';

class Notification extends React.Component {
    constructor(props){
        super(props);
        this.state={'notifications':null,'notificationError':null}
        //teacher prop is the teacher username
        //teacher state is the teacher info

    }
    componentDidMount(){
        API.getTeacherNotifications(this.props.teacher.username).then((notifications)=>{
            const notif_ordered=notifications.sort((a,b)=>a.date>b.date?-1:1)
            this.setState({notifications:notif_ordered})})
        .catch((err)=>this.setState({notificationError:err}));
        
    }
    showItem=(notification)=>{
        return <NotificationItem key={notification.notificationId}notification={notification} readNotification={this.readNotification}/>
    }
    readNotification=(notification)=>{
        let not=this.state.notifications.map((n)=>{
            if(n.notificationId===notification){
                return {notificationId:n.notificationId,description:n.description,date:n.date,status:'true',link:n.link}
            }
            return {notificationId:n.notificationId,description:n.description,date:n.date,status:n.status,link:n.link}
        })
        this.setState({notifications:not});
    }
    render(){
        if(this.state.notificationError){
            return <h2>we are sorry but an error just occurred</h2>
        }
        if(!this.state.notifications){
        return <h1>page is loading</h1>
        }
        return  <>
                <h1>Welcome To your Notification Page</h1>
                <div className="raw">
                    <div className="col-12">
                <ul className="list-group list-group-flush">
                {this.state.notifications.map(this.showItem)}
                </ul>
                </div>
                </div>
                </>     
    }
}




function NotificationItem (props){
    //const className= "d-flex w-100 justify-content-between "+ props.notification.status?"bg-danger":null
    let elem;
    props.notification.status?elem= <svg width="2em" height="2em" viewBox="0 0 16 16" className="bi bi-check" fill="green" xmlns="http://www.w3.org/2000/svg">
                                        <path fillRule="evenodd" d="M10.97 4.97a.75.75 0 0 1 1.071 1.05l-3.992 4.99a.75.75 0 0 1-1.08.02L4.324 8.384a.75.75 0 1 1 1.06-1.06l2.094 2.093 3.473-4.425a.236.236 0 0 1 .02-.022z"/>
                                    </svg>:
                              elem= <svg width="2em" height="2em" viewBox="0 0 16 16" className="bi bi-x" fill="red" xmlns="http://www.w3.org/2000/svg" onClick={(ev) => props.readNotification(props.notification.notificationId)}>
                                        <path fillRule="evenodd" d="M4.646 4.646a.5.5 0 0 1 .708 0L8 7.293l2.646-2.647a.5.5 0 0 1 .708.708L8.707 8l2.647 2.646a.5.5 0 0 1-.708.708L8 8.707l-2.646 2.647a.5.5 0 0 1-.708-.708L7.293 8 4.646 5.354a.5.5 0 0 1 0-.708z"/>
                                    </svg> 
    return (
        <li className="list-group-item" id = {props.notification.notificationId}>
        <div className="d-flex w-100 justify-content-between">
            <div className="col-1">
                {elem}
            </div>
            <div className="col-5">
            <h4>{props.notification.description}</h4>
            </div>

            <div className="col-3">
            <h4>{props.notification.date}</h4>
            </div>
            <div className="col-3">
            <h4>{props.notification.link }</h4>
            </div>
        </div>
        </li>       
        )
}
export default Notification;
