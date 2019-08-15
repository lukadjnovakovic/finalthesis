import React, { Component } from "react";
import Home from "./Home";
import Loginscreen from "./Loginscreen";

export default class Main extends Component {

    constructor(props) {
        super(props);
        this.state = {
            token : null,
        }
    }

    componentDidMount() {
    //  localStorage.setItem("token", "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxIiwiaWF0IjoxNTYzMDMxMDA0LCJleHAiOjE1NjM2MzU4MDR9.XCit3M3gOAzEBt_y0BbqseH3ISl6HJfNRgKTInCr4N4ua_VqzkHWc2OLGcl2hDdMynD6XrOExaohFxq4n0aIJg");
    //  this.setState({token: "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxIiwiaWF0IjoxNTYzMDMxMDA0LCJleHAiOjE1NjM2MzU4MDR9.XCit3M3gOAzEBt_y0BbqseH3ISl6HJfNRgKTInCr4N4ua_VqzkHWc2OLGcl2hDdMynD6XrOExaohFxq4n0aIJg"})
    }

    setToken(token){
        //this.setState({token:token});
        sessionStorage.setItem("token", token);
        this.forceUpdate();
        //console.log("FORCE UPDATE!")
    }

    isTokenMissing(){
        return sessionStorage.getItem("token") === null;
    }

    getToken(){
        return sessionStorage.getItem("token");
    }

    render(){
        var page = <div>Hello main.</div>;
        if (!this.isTokenMissing()) {
            page = <Home token={this.getToken()}></Home>;
        } else {
            page = <Loginscreen setToken={((token) => this.setToken(token)).bind(this)} isTokenMissing={()=>this.isTokenMissing()}/>;
        }
        return page;
    }

}
