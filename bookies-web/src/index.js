import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import Home from './Home';
import * as serviceWorker from './serviceWorker';
import Login from "./Login";

ReactDOM.render(<Login />, document.getElementById('root'));
{/*<Route path="/home" exact component={Home} />,*/}
{/*<Route path="/login" exact component={Login} />*/}

// If you want your app to work offline and load faster, you can change
// unregister() to register() below. Note this comes with some pitfalls.
// Learn more about service workers: https://bit.ly/CRA-PWA
serviceWorker.unregister();
