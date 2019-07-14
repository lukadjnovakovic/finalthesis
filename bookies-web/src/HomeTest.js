import React from 'react';
import './Home.css';
import  ReactTable from 'react-table';
import  'react-table/react-table.css';
import 'bootstrap/dist/css/bootstrap.css';
import { Button, ListGroup } from 'react-bootstrap';

var api_base='http://localhost:8081';

function Cell(props){
  let data = props.value.data;
  let selectedTip = data.tips.filter(x => x.tip === props.tip)[0];
  let variant = !selectedTip.isSelected ? "success" : "primary";
  variant = data.isOver ? "secondary" : variant;
  return (
      <Button onClick={()=>props.onClick(props.value, props.tip)} disabled={data.isOver} variant={variant}>
        {selectedTip.odds}
      </Button>
  );
}

export class Home extends React.Component {

  handleCellClick(cell, tip) {
    let data = cell.data;
    let selectedTip = data.tips.filter(x => x.tip === tip)[0];

    console.log("odds " + selectedTip.odds);
    console.log("isOver " + data.isOver);
    console.log("tip " + selectedTip.tip);
    console.log("league " + data.league);
    console.log("match " + cell.id);
    console.log("\n");

    this.setState(state => {
      //console.log(state);
      const newState = state.tables[data.league].filter(x => x.id === cell.id)[0].data.tips.map(x => {
        if (x.tip === tip) {
          if (x.isSelected) {
            x.isSelected = false;
          } else {
            x.isSelected = true;
          }
        } else if (x.isSelected === true) {
          x.isSelected = false;
        }
        console.log(x);
        return null;
      });
      return {
        newState,
      };
    }, () => {

      const ticket = [];
      Object.keys(this.state.tables).forEach(function (league) {
        this.state.tables[league].forEach(function (game) {
          game.data.tips.forEach(function (tip) {
            if (tip.isSelected) {
              ticket.push({
                league: league,
                home: game.data.homeTeam,
                away: game.data.awayTeam,
                tip: tip.tip,
                odds: tip.odds,
              });
            }
          });
        });
      }.bind(this));
      this.setState({
        ticket: ticket,
      });
    });

  }

  constructor(props) {
    super(props);
    this.state = {
      games: null,
      tips: null,
      tables: null,
      ticket: [],
    }
  }

  componentDidMount() {
    fetch(api_base + '/api/games',
        {
          method: 'GET',
          headers: {
            "Authorization": "Bearer " + this.props.token,
          },
        }
    )
        .then(res => res.json())
        .then((games) => {
          this.setState({games: games})
        })
        .then(() => {
          fetch(api_base + '/api/tips',
              {
                method: 'GET',
                headers: {
                  "Authorization": "Bearer " + this.props.token,
                },
              })
              .then(res => res.json())
              .then((tips) => {
                this.setState({tips: tips})
              })
              .then(() => {
                    console.log("FETCHED GAMES:");
                    console.log(this.state.games);

                    // init tips
                    let tips = this.state.tips.map((t) => {
                      return t.name;
                    });

                    //console.log(tips);

                    // take tables object
                    let tables = {}
                    this.state.games.forEach(function (game, index) {
                      let row = {};
                      let _game = {};
                      _game.data = row;
                      _game.id = game.id;
                      row.tips = [];
                      tips.forEach(function (tip, index) {
                        //console.log(item);
                        row.tips.push({
                          tip: tip,
                          odds: game.odds.filter(x => x.tip.name === tip)[0].odds,
                          isSelected: false,
                        });
                      });

                      row.homeTeam = game.homeTeam.name;
                      row.awayTeam = game.awayTeam.name;
                      row.isOver = false;
                      row.league = game.competition.name;

                      if (!tables.hasOwnProperty(game.competition.name)) {
                        tables[game.competition.name] = [];
                      }
                      tables[game.competition.name].push(_game);
                    });

                    this.setState(
                        {tables: tables},
                        () => {
                          console.log(this.state.tables)
                        },
                    );
                  }
              )
        });
  }

  render() {
    if (this.state.tables != null) {

      var tables = [];

      Object.keys(this.state.tables).forEach(league => {

        const tipsHeaders = this.state.tips.map((t) => {
          return {
            Header: t.name,
            accessor: y => y,
            Cell: y => <Cell onClick={(x, y) => this.handleCellClick(x, y)} tip={t.name}
                             value={y.value}> </Cell>,
            id: t.name,
            resizable: false,
            sortable: false,
          }
        });
        const columns = [
          {
            id: 'homeTeam',
            Header: "Home",
            accessor: x => x.data.homeTeam,
            width: 250,
            resizable: false,
            sortable: true,
          },
          {
            id: 'awayTeam',
            Header: "Away",
            accessor: x => x.data.awayTeam,
            width: 250,
            resizable: false,
            sortable: true,
          }
        ].concat(tipsHeaders);

        const _columns = [{
          Header: league,
          columns: columns,
        }];


        tables = tables.concat(
            <ReactTable
                data={this.state.tables[league]}
                columns={_columns}
                defaultPageSize={this.state.tables[league].length > 5 ? 5 : this.state.tables[league].length}
                key={league}
            />
        );
      });

      const ticket = this.state.ticket.map((x) => <ListGroup.Item> {x.home} - { x.away} {x.tip} {x.odds} </ListGroup.Item> );

      return (
          <div>
            {tables}
            <ListGroup>
              {ticket}
            </ListGroup>
          </div>
      );
    } else {
      return <div></div>;
    }
  }
}
export default Home;r