import React, { Component } from "react";
import Home from "./Home";
import Loginscreen from "./Loginscreen";
// import ReactDOM from "react-dom";

export default class Main extends Component {

    constructor(props) {
        super(props);
        this.state = {
            token : null,
        }
    }

    setToken(token){
        this.setState({token:token});
        this.forceUpdate();
    }

    render(){
        var page = <div>Hello main.</div>;
        if (this.state.token) {
            page = <Home token={this.state.token}></Home>;
        } else {
            page = <Loginscreen setToken={((token) => this.setToken(token)).bind(this)}/>;
        }
        return page;
    }

}
