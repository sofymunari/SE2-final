import React from 'react';
import {Route,Switch} from 'react-router-dom';

class Employee extends React.Component{
	constructor(props){
		super(props);
	}
	get_next_Ticket() {
	    fetch('/nextTicket/2')
	      .then(response => response.json())
	      .then(data => {return data} )
	      .catch((response) => {throw response});
 	}
	render(){
		return <h1>YOUR NEXT CUSTOMER IS {this.get_next_Ticket()} </h1>
	}

}

export default Employee;