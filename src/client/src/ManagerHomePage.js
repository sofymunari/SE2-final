import React from 'react';
import API from './API.js';
import AppComponents from './AppComponents';
import {Route,Switch,Link} from 'react-router-dom';


class ManagerHomePage extends React.Component{
    constructor(props){
        super(props);
        this.state={'manager':null,'bookings':null,'error':null}
    }

    componentDidMount(){
        API.getManagerInfo(this.props.manager).then((manager)=>{
            API.getBookings().then((bookings)=>{
                this.setState({'bookings':bookings,'manager':manager});
            }).catch((error)=>this.setState({'error':error}));
        }).catch((error)=>this.setState({error:error}));
    }

    render(){
        if(this.state.error){
            return<h1>Connections problems</h1>
        }
        if(!this.state.manager){
            return <h1>LOADING</h1>
        }
        return <>
            <AppComponents.AppNavbar logOut={this.props.logOut}/>
            <h1>Welcome</h1>
            </>;
    }
}

export default ManagerHomePage;