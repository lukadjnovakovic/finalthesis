import React from 'react';
import 'bootstrap/dist/css/bootstrap.css';
import { Button } from 'react-bootstrap';
import { Link } from "react-router-dom";
import queryString from "query-string";
import axios from "axios";
import './Main.css';
const apiBaseUrl = 'http://localhost:8081/api';


export class Payment extends React.Component {

    constructor(props) {
        super(props);
        //console.log("PAYMENT CONST " + this.props.payload);
    }

    handlePayment() {

        let config = {
            headers: {
                "Authorization": "Bearer " + sessionStorage.getItem("token"),
            }
        };

        const values = queryString.parse(window.location.search);
        console.log(values);
        console.log(values.PayerID);
        console.log(values.paymentId);
        console.log('*************:' + sessionStorage.getItem("token"));

        axios.post(apiBaseUrl + '/complete/payment?paymentId=' + values.paymentId + '&PayerID=' + values.PayerID, config)
            .then(function (response) {
                console.log('!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!');
                console.log(response);
                if (response.data.done === 'true') {
                    console.log('payment completed!')

                    //this.clearTicket();
                    this.makeTicket();
                }
            }.bind(this))
            .catch(function (error) {
                console.log(error);
            });
    }

    makeTicket() {
        let config = {
            headers: {
                "Authorization": "Bearer " + sessionStorage.getItem("token"),
            }
        };
        let payload = JSON.parse(sessionStorage.getItem("payload"));
        console.log("VIDI GA JA " + payload);

        axios.post(apiBaseUrl + '/saveTicket', payload, config)
            .then(function (response) {
                console.log(response);
                if (response.status === 201) {
                    console.log("successfully made ticket");
                }
            })
            .catch(function (error) {
                console.log(error);
            });
    }

    render() {
        //let completePayment = <Button variant="outline-info" onClick={() => { this.handlePayment()}}>Confirm</Button>
        let makeTicket = <Link to="/"><Button variant="outline-info" onClick={() => { this.handlePayment() }}>Confirm</Button></Link>
        let cancel = <Link to="/"><Button variant="outline-info">Cancel</Button></Link>
        return (
            <div>
                <div className="center-div">
                    <div className="row login-div">
                        <div className="col-sm-4">
                        </div>
                        <div className="col-sm-4">
                            <div class="row">
                                <div className="col-sm">
                                    {makeTicket}
                                </div>
                                <div className="col-sm">
                                    {cancel}
                                </div>
                            </div>
                        </div>
                    </div>;
                </div>
            </div>
        );
    }
}

export default Payment;