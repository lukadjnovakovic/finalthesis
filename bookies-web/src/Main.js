import React, { Component } from "react";
import Home from "./Home";
import Loginscreen from "./Loginscreen";
import { Navbar, Form, Button, Nav } from 'react-bootstrap';
import './Main.css';

export default class Main extends Component {

    constructor(props) {
        super(props);
        this.state = {
            token: null,
        }
    }

    componentDidMount() {
        //  localStorage.setItem("token", "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxIiwiaWF0IjoxNTYzMDMxMDA0LCJleHAiOjE1NjM2MzU4MDR9.XCit3M3gOAzEBt_y0BbqseH3ISl6HJfNRgKTInCr4N4ua_VqzkHWc2OLGcl2hDdMynD6XrOExaohFxq4n0aIJg");
        //  this.setState({token: "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxIiwiaWF0IjoxNTYzMDMxMDA0LCJleHAiOjE1NjM2MzU4MDR9.XCit3M3gOAzEBt_y0BbqseH3ISl6HJfNRgKTInCr4N4ua_VqzkHWc2OLGcl2hDdMynD6XrOExaohFxq4n0aIJg"})
    }

    setToken(token) {
        //this.setState({token:token});
        sessionStorage.setItem("token", token);
        this.forceUpdate();
        //console.log("FORCE UPDATE!")
    }

    isTokenMissing() {
        return sessionStorage.getItem("token") === null;
    }

    getToken() {
        return sessionStorage.getItem("token");
    }

    render() {
        var page = <div>Hello main.</div>;
        if (!this.isTokenMissing()) {
            page = <Home token={this.getToken()}></Home>;
        } else {
            page =
                <div className="center-div">
                    <div className="row login-div">
                        <div className="col-sm-4"></div>
                        <div className="col-sm-4">
                            <Loginscreen setToken={((token) => this.setToken(token))} isTokenMissing={() => this.isTokenMissing()} />

                        </div>
                    </div>
                </div>;
        }
        return (
            <div>
                <Navbar bg="dark" variant="dark">
                    <Navbar.Brand href="#home">
                        <img
                            alt=""
                            src={require('./logo.png')}

                            height="50"
                            className="d-inline-block align-top"
                        />
                    </Navbar.Brand>
                    <Nav className="mr-auto">
                        <div className="brand">Zverkan Bets</div>
                    </Nav>
                    <Nav className="mr-auto"></Nav>
                    <Form inline>
                        <Button variant="outline-info" onClick={() => { sessionStorage.removeItem("token"); this.forceUpdate(); }}>Logout</Button>
                    </Form>
                </Navbar>
                <br />
                {page}


            </div>
        );
    }

}
