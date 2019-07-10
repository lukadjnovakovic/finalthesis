import React, { Component } from "react";
import LoginOld from "./LoginOld";
import Home from "./Home";
import LoginRegister from "./Login";
import ReactDOM from "react-dom";

export default class Main extends Component {

    constructor(props) {
        super(props);
        this.state = {
            token : null,
        }
    }

    setToken(token){
        this.setState({token:token});
    }

    render(){
        var page = <div>Hello main.</div>;
        if (this.state.token) {
            page = <Home token={this.state.token}></Home>;
        } else {
            page = <LoginRegister setToken={((token) => this.setToken(token)).bind(this)}/>;
        }
        return page;
    }

}
