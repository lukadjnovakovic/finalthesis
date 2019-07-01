import React from 'react';
import './Home.css';
import  ReactTable from 'react-table'
import  'react-table/react-table.css'

export class Home extends React.Component{

  constructor(props){
    super(props);
    this.state = {
      games:null,
      tips:null
    }
  }

  render(){
      if (this.state.tips != null && this.state.games !=null){
        const tipsHeaders = this.state.tips.map((t)=>{
            return {
                Header: t.name,
                accessor: y => y.odds.filter(z=>z.tip.id===t.id).length>0? y.odds.filter(z=>z.tip.id===t.id)[0].odds : '-',
                Cell : props => <div>{props.value}</div>,
                id: t.name,
            }
        });
        const columns = [{
            id : 'games',
            Header: '{competition}',
            accessor: x => x.homeTeam.name + " - " + x.awayTeam.name
        }].concat(tipsHeaders);


        return <ReactTable
          data={this.state.games}
          columns={columns}
        />
      }
      else{
         return <div>HELLO.</div>
      }
  }

  componentDidMount() {
    fetch('/api/games',
        {
            headers: {
                "Authorization" : "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIyIiwiaWF0IjoxNTYyMDAzMzAwLCJleHAiOjE1NjI2MDgxMDB9.3zIubcGYHJrPrwa_b-87oQV5XRtBy9bPOOO_m5Mbmlrwh5eCCcuH3Eb9KFJAc3ByKM7rx9mXi8XEAiIZalojiA"
            },
        }).then(res => res.json()).then((games) => {
      this.setState({games: games});
    })
    fetch('/api/tips',
        {
            headers: {
                "Authorization" : "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIyIiwiaWF0IjoxNTYyMDAzMzAwLCJleHAiOjE1NjI2MDgxMDB9.3zIubcGYHJrPrwa_b-87oQV5XRtBy9bPOOO_m5Mbmlrwh5eCCcuH3Eb9KFJAc3ByKM7rx9mXi8XEAiIZalojiA"
            },
        }).then(res => res.json()).then((tips) => {
      this.setState({tips:tips});
    })
  }
}

export default Home;