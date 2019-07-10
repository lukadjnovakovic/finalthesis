import React from 'react';
import './Home.css';
import  ReactTable from 'react-table'
import  'react-table/react-table.css'

var api_base='http://localhost:8081';

export class Home extends React.Component{

  constructor(props){
    super(props);
    this.state = {
        games : null,
        tips: null,
    }
  }

  componentDidMount() {
        fetch(api_base +'/api/games',
            {
                method: 'GET',
                headers: {
                    "Authorization" : "Bearer " + this.props.token,
                },
            }).then(res => res.json()).then((games) => {
            this.setState({games: games});
        });
        fetch(api_base+'/api/tips',
            {
                method: 'GET',
                headers: {
                    "Authorization" : "Bearer " + this.props.token,
                },
            }).then(res => res.json()).then((tips) => {
            this.setState({tips:tips});
        });
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
         return <div>HELLO HOME.</div>
      }
  }
}

export default Home;