import React from 'react';
import './App.css';


export class App extends React.Component{
  render(){
    return(
        <div>Hello</div>
    );
  }

  componentDidMount() {
    fetch('/api/games').then(res => res.json()).then((data) => {
      console.log(data)
    })
  }
}

export default App;