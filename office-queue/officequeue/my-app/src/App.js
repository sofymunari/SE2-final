import React from 'react';
import {Route,Switch} from 'react-router-dom';
import './App.css';
import Employee from './Employee.js'; 
import Customer from './Customer.js';

class App extends React.Component{
	constructor(props){
		super(props);
	}
	
	render(){
		return <Switch>
				<Route exact path="/employee">
					<Employee/>
				</Route>	
				<Route exact path="/customer">
					<Customer/>
				</Route>	
				<h1>hello 2</h1>
				</Switch>
					
	}
}

export default App;
