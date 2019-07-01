import React, { Component } from "react";
import Form from 'react-bootstrap/Form'
import Button from 'react-bootstrap/Button'
import '../node_modules/bootstrap/dist/css/bootstrap.css';
import Home from "./Home";

export default class Login extends Component {
    constructor(props) {
        super(props);

        this.state = {
            username: "",
            password: "",
            token: "",
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

    // componentDidMount () {
    //     console.log('asdfadfsafd');
    //     fetch('/api/auth/login',
    //         {
    //             method : 'POST',
    //             headers : {
    //                 'Content-Type' : 'aplication/json'
    //             },
    //             body : JSON.stringify({username: "luka", password: "luka"})
    //         }
    //     ).then(res => console.log(res));
    // }

    componentDidMount() {
        console.log("TOKEN " + this.state.token);
    }

    handleSubmit () {
        console.log("token " + this.state.token);
        //console.log('sacu da fecujem sa post hehe to vi mislite bice GET ipak');
        fetch('/api/auth/login',
            {
                method : 'POST',
                headers : {
                    'Accept' : 'aplication/json',
                    'Content-Type' : 'aplication/json'
                },
                body : JSON.stringify({username: this.state.username, password: this.state.password})
            }
        ).then((res) => res.json()
        ).then(x => this.setState({token:x.body.accessToken}));
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