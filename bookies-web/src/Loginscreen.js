import React, { Component } from 'react';
//import MuiThemeProvider from 'material-ui/styles/MuiThemeProvider';
//import RaisedButton from 'material-ui/RaisedButton';
import Login from './Login';
import { Button } from 'react-bootstrap';
import Register from './Register';
import './Ticket.css';

export default class Loginscreen extends Component {
    constructor(props) {
        super(props);
        this.state = {
            username: '',
            password: '',
            loginscreen: [],
            loginmessage: '',
            buttonLabel: 'To Register',
            isLogin: true
        }
    }

    handleClick(event) {
        // console.log("event",event);
        var loginmessage;
        var loginscreen = [];
        if (this.state.isLogin) {
            loginscreen.push(<Register parentContext={this} setToken={this.props.setToken} />);
            loginmessage = "Already registered? Go to Login!";
            this.setState({
                loginscreen: loginscreen,
                loginmessage: loginmessage,
                buttonLabel: "To Login",
                isLogin: false
            })
        }
        else {
            loginscreen.push(<Login parentContext={this} appContext={this.props.parentContext} setToken={this.props.setToken} />);
            loginmessage = "Not Registered yet? Go to registration!";
            this.setState({
                loginscreen: loginscreen,
                loginmessage: loginmessage,
                buttonLabel: " To Register",
                isLogin: true
            })
        }
    }

    componentWillMount() {
        var loginscreen = [];
        loginscreen.push(<Login key="inital" parentContext={this} appContext={this.props.parentContext} setToken={this.props.setToken} />);
        var loginmessage = "Not registered yet? Register Now!";
        this.setState({
            loginscreen: loginscreen,
            loginmessage: loginmessage
        })
    }

    render() {
        return (
            <div className="loginscreen">
                {this.state.loginscreen}
                <div>
                    <div className="ctext">
                     {this.state.loginmessage}
                    </div>
                    <hr />
                    <div>
                        <Button className="cbutton" variant="outline-info" onClick={(event) => this.handleClick(event)}> {this.state.buttonLabel} </Button>
                    </div>
                </div>
            </div>
        );
    }
}
