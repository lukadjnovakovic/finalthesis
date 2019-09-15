import React, { Component } from "react";
import Home from "./Home";
import Loginscreen from "./Loginscreen";
import Payment from "./Payment";
import { Navbar, Form, Button, Nav } from 'react-bootstrap';
import './Main.css';
import MyTickets from "./MyTickets";
import { Router, Route, Link } from 'react-router-dom';
import createHistory from 'history/createBrowserHistory';
const history = createHistory();

export default class Main extends Component {

    constructor(props) {
        super(props);
        this.state = {
            token: null,

        }
    }
    
    setToken(token) {
        sessionStorage.setItem("token", token);
        this.forceUpdate();
    }

    isTokenMissing() {
        return sessionStorage.getItem("token") === null;
    }

    getToken() {
        return sessionStorage.getItem("token");
    }

    render() {
        var page = <div>Hello main.</div>;
        var menu = sessionStorage.getItem("token") ?
            <div>
                <Link to="/"><Button variant="outline-info">Home</Button></Link>
                <Link to="/mytickets/"><Button variant="outline-info">My Tickets</Button></Link>
            </div>
            : null;
        if (!this.isTokenMissing()) {
            page = <Home token={this.getToken()} ></Home>;
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
        var logout = sessionStorage.getItem("token") ? <Link to="/"><Button variant="outline-info" onClick={() => { sessionStorage.removeItem("token"); this.forceUpdate(); }}>Logout</Button></Link> : null;
        return (
            <Router history={history}>
                <div>
                    <Navbar expand="lg" bg="dark" variant="dark">
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
                            {menu}
                            {logout}
                        </Form>
                    </Navbar>
                    <br />

                </div>
                <Route exact path="/" render={() => <div>{page}</div>}/>
                <Route exact path="/mytickets" render={() => <MyTickets token={sessionStorage.getItem("token")}></MyTickets>}/>
                <Route exact path="/payment" render={()=><Payment/>}/>
            </Router>
        );
    }

}
