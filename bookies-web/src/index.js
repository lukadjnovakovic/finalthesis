import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import * as serviceWorker from './serviceWorker';
import Main from "./Main";
import { transitions, positions, Provider as AlertProvider } from 'react-alert'
import AlertTemplate from 'react-alert-template-basic';

const options = {
    // you can also just use 'bottom center'
    position: positions.BOTTOM_CENTER,
    timeout: 100000,
    offset: '30px',
    // you can also just use 'scale'
    transition: transitions.SCALE
  }

  const Root = () => (
    <AlertProvider template={AlertTemplate} {...options}>
      <Main />
    </AlertProvider>
  )

ReactDOM.render(<Root />, document.getElementById('root'));


// If you want your app to work offline and load faster, you can change
// unregister() to register() below. Note this comes with some pitfalls.
// Learn more about service workers: https://bit.ly/CRA-PWA
serviceWorker.unregister();
