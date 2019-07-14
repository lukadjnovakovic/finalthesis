import React, { Component } from "react";
import MuiThemeProvider from 'material-ui/styles/MuiThemeProvider';
import AppBar from 'material-ui/AppBar';
import RaisedButton from 'material-ui/RaisedButton';
import TextField from 'material-ui/TextField';
import axios from "axios";
import Home from "./Home.js";

export default class Login extends Component {
    constructor(props){
        super(props);
        this.state={
            token:'',
            username:'',
            password:''
        }
    }

    handleClick(event,e){
        var apiBaseUrl = "http://localhost:8081/api/auth";
        var payload={
            "username":this.state.username,
            "password":this.state.password
        }
        // e.preventDefault();
        axios.post(apiBaseUrl+'/login', payload)
            .then(function (response) {
                console.log(response);
                if(response.status == 200){
                    console.log("login successfull");
                    console.log(response.accessToken);
                    this.props.setToken(response.data.accessToken);

                }
            }.bind(this))
            .catch(function (error) {
                console.log(error);
            });
    }

    render() {
        return (
            <div>
                <MuiThemeProvider>
                    <div>
                        <AppBar
                            title="Login"
                        />
                        <TextField
                            hintText="Enter your Username"
                            floatingLabelText="Username"
                            onChange = {(event,newValue) => this.setState({username:newValue})}
                        />
                        <br/>
                        <TextField
                            type="password"
                            hintText="Enter your Password"
                            floatingLabelText="Password"
                            onChange = {(event,newValue) => this.setState({password:newValue})}
                        />
                        <br/>
                        <RaisedButton label="Submit" primary={true} style={style} onClick={(event) => this.handleClick(event)}/>
                    </div>
                </MuiThemeProvider>
            </div>
        );
    }
}
const style = {
    margin: 15,
};
