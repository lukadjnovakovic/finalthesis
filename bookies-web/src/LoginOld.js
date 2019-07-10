import React, { Component } from "react";
import Form from 'react-bootstrap/Form'
import Button from 'react-bootstrap/Button'
import '../node_modules/bootstrap/dist/css/bootstrap.css';

export default class LoginOld extends Component {
    constructor(props) {
        super(props);

        this.state = {
            username: "",
            password: "",
            message: "",
            error: ""
        };
    }

    validateForm() {
        return this.state.email.length > 0 && this.state.password.length > 0;
    }

    handleChange = event => {
        this.setState({
            [event.target.name]: event.target.value
        });
    }

    componentDidMount() {
        //console.log("TOKEN " + this.state.token);
    }

    handleSubmit (e) {
        e.preventDefault();
        try {
            fetch('http://localhost:8081/api/auth/login',
                {
                    method: 'POST',
                    headers: {
                        // 'Accept': 'aplication/json',
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify({username: this.state.username, password: this.state.password})
                }
            ).then((res) => res.json()
            ).then(
                x=>this.props.setToken(x.accessToken)
                //x=>console.log(x)
            );

            // ).then(x => this.setState({token: x.body.accessToken}));

        }catch(e){
            console.log(e);
        }
    }

    render() {
        return (
            <Form>
                <Form.Group controlId="formBasicEmail">
                    <Form.Label>Username</Form.Label>
                    <Form.Control name="username" onChange={this.handleChange} type="username"
                                  placeholder="Enter username"/>
                </Form.Group>

                <Form.Group controlId="formBasicPassword">
                    <Form.Label>Password</Form.Label>
                    <Form.Control name="password" onChange={this.handleChange} type="password" placeholder="Password"/>
                </Form.Group>
                <Button onClick={this.handleSubmit.bind(this)} variant="primary" type="submit">
                    Submit
                </Button>
            </Form>
        );
    }
}