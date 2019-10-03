import React, { Component } from 'react';
//import MuiThemeProvider from 'material-ui/styles/MuiThemeProvider';
//import AppBar from 'material-ui/AppBar';
//import RaisedButton from 'material-ui/RaisedButton';
//import TextField from 'material-ui/TextField';
import axios from 'axios';
import Login from './Login';
import { Form, Button } from 'react-bootstrap';
import './Ticket.css';

class Register extends Component {
    constructor(props) {
        super(props);
        this.state = {
            first_name: '',
            last_name: '',
            username: '',
            password: ''
        }
    }

    handleClick(event) {
        var apiBaseUrl = "http://localhost:8081/api/auth";
        //To be done:check for empty values before hitting submit
        var self = this;

        if(!this.state.first_name || !this.state.last_name || !this.state.username || !this.state.password){
            alert("Please fill all fields.");
            return;
        }
        var payload = {
            "name": this.state.first_name,
            "surname": this.state.last_name,
            "username": this.state.username,
            "password": this.state.password
        }
        console.log(payload);
        axios.post(apiBaseUrl + '/signup', payload)
            .then(function (response) {
                console.log(response);
                if (response.data.success === true) {
                    console.log(response.data);
                    var loginscreen = [];
                    loginscreen.push(<Login parentContext={this} setToken={this.props.setToken} />);
                    var loginmessage = "Successful registration!";
                    self.props.parentContext.setState({
                        loginscreen: loginscreen,
                        loginmessage: loginmessage,
                        buttonLabel: "To Register",
                        isLogin: true
                    });
                }
            }.bind(this))
            .catch(function (error) {

                    alert("Username taken.")

            });
    }

    render() {
        return (
            <div>
                <div>
                    <Form onSubmit={e => { e.preventDefault(); }}>
                        <Form.Group>
                            <Form.Control
                                type="text"
                                value={this.state.first_name}
                                onChange={(event) => this.setState({ first_name: event.target.value })}
                                placeholder="First name"
                            >
                            </Form.Control>
                        </Form.Group>
                        <Form.Group>
                            <Form.Control
                                type="text"
                                value={this.state.last_name}
                                onChange={(event) => this.setState({ last_name: event.target.value })}
                                placeholder="Last name"
                            >
                            </Form.Control>
                        </Form.Group>
                        <Form.Group>
                            <Form.Control
                                type="text"
                                value={this.state.username}
                                onChange={(event) => this.setState({ username: event.target.value })}
                                placeholder="Username"
                            >
                            </Form.Control>
                        </Form.Group>
                        <Form.Group>
                            <Form.Control
                                type="password"
                                value={this.state.password}
                                onChange={(event) => this.setState({ password: event.target.value })}
                                placeholder="Password"
                            >
                            </Form.Control>
                        </Form.Group>
                    </Form>
                    <Button className="cbutton" variant="outline-info" onClick={(event) => this.handleClick(event)}> Register! </Button>
                </div>
            </div>
        );
    }
}
export default Register;