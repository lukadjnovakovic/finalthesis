import React, { Component } from "react";
import axios from "axios";
import { Form, Button } from 'react-bootstrap';

export default class Login extends Component {
    constructor(props) {
        super(props);
        this.state = {
            token: '',
            username: '',
            password: ''
        }
    }

    handleClick(event, e) {
        var apiBaseUrl = "http://localhost:8081/api/auth";
        var payload = {
            "username": this.state.username,
            "password": this.state.password
        }
        axios.post(apiBaseUrl + '/login', payload)
            .then(function (response) {
                if (response.status === 200) {
                    this.props.setToken(response.data.accessToken);
                }
            }.bind(this))
            .catch(function (error) {
                alert(error)
            });
    }

    render() {
        return (
            <div>
                <Form onSubmit={e => { e.preventDefault(); }}>
                    <Form.Group >
                        <Form.Control
                            type="text"
                            onChange={(event) => this.setState({ username: event.target.value })}
                            placeholder="username"
                        >
                        </Form.Control>
                    </Form.Group>
                    <Form.Group>
                        <Form.Control
                            type="password"
                            onChange={(event) => this.setState({ password: event.target.value })}
                            placeholder="password"
                        >
                        </Form.Control>
                    </Form.Group>
                    <Button className="cbutton" variant="outline-info" onClick={(event) => this.handleClick(event)}> Login! </Button>
                </Form>
            </div>
        );
    }
}
