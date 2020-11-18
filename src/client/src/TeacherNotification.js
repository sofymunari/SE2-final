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
        return <NotificationItem key={notification.notificationId}notification={notification}/>
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
                {this.state.notifications.map(this.showItem)}
                </>     
    }
}




function NotificationItem (props){
    const className= "d-flex w-100 justify-content-between "+ props.notification.status?"bg-danger":null
    return (
        <li className="list-group-item" id = {props.notification.notificationId}>
        <div className={className}>
            <div className="col-7">
            <h4>{props.notification.description}</h4>
            </div>

            <div className="col-3">
            <h4>{props.notification.date}</h4>
            </div>
            <div className="col-2">
            <h4>{props.notification.link }</h4>
            </div>
        </div>
        </li>       
        )
}
export default Notification;
