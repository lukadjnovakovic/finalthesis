import React, { Component } from "react";
import Home from "./Home";

export default class Main extends Component {

    constructor(props) {
        super(props);
        this.state = {
            token : null,
        }
    }

    componentDidMount() {
        this.setState({token: "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxIiwiaWF0IjoxNTYzMDMxMDA0LCJleHAiOjE1NjM2MzU4MDR9.XCit3M3gOAzEBt_y0BbqseH3ISl6HJfNRgKTInCr4N4ua_VqzkHWc2OLGcl2hDdMynD6XrOExaohFxq4n0aIJg"})
    }

    setToken(token){
        this.setState({token:token});
    }

    render(){
        var page = <div>Hello main.</div>;
        if (this.state.token) {
            page = <Home token={this.state.token}></Home>;
        } else {
            //page = <Login setToken={((token) => this.setToken(token)).bind(this)}/>;
        }
        return page;
    }

}
