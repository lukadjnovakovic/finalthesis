import React from 'react';
import './Home.css';
import Matches from "./Matches";
import Ticket from './Ticket';
import 'react-table/react-table.css';
import 'bootstrap/dist/css/bootstrap.css';
import axios from "axios";

const api_base = 'http://localhost:8081';

export class Home extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            games: null, // from api
            tips: null, // from api
            matches: null, // transformed games
            ticket: [], // user created
            amount: "", // user created
            win: "", // calculated every time amount changes
            oddsOverall: 1,
        }
    }

    isGameSelected() {

    }

    setSelectedGame(matchId, tip, selected) {
        this.setState(state => {
            const newState = Object.keys(state.matches).forEach(function(league){
                this.state.matches[league].forEach(function(match){
                    if (match.data.id === matchId){
                        match.data.tips.forEach(function(tip){
                            if (tip.tip === tip){
                                tip.isSelected = selected;
                            }
                        });
                    }
                })
            });
            return {
                newState,
            }
        },()=>{
            const ticket = [];
            let oddsOverall = 1;
            Object.keys(this.state.matches).forEach(function (league) {
                this.state.matches[league].forEach(function (game) {
                    game.data.tips.forEach(function (tip) {
                        if (tip.isSelected) {
                            ticket.push({
                                league: league,
                                home: game.data.homeTeam,
                                away: game.data.awayTeam,
                                tip: tip.tip,
                                odds: tip.odds,
                                id: game.id,
                            });
                            oddsOverall = oddsOverall * tip.odds;
                        }
                    });
                });
            }.bind(this));

            this.setState(
                {
                    ticket: ticket,
                    oddsOverall: oddsOverall,
                    win: this.state.amount ? this.state.amount * oddsOverall : "",
                },
                () => { console.log(this.state.oddsOverall) }
            );

        });
    }

    handleCellClick(cell, tip) {
        let data = cell.data;

        this.setState(state => {
            const newState = state.matches[data.league].filter(x => x.id === cell.id)[0].data.tips.map(x => {
                if (x.tip === tip) {
                    x.isSelected = !x.isSelected;
                } else if (x.isSelected === true) {
                    x.isSelected = false;
                }
                // return null;
            });
            return {
                newState,
            };
        }, () => {

            const ticket = [];
            let oddsOverall = 1;
            Object.keys(this.state.matches).forEach(function (league) {
                this.state.matches[league].forEach(function (game) {
                    game.data.tips.forEach(function (tip) {
                        if (tip.isSelected) {
                            ticket.push({
                                league: league,
                                home: game.data.homeTeam,
                                away: game.data.awayTeam,
                                tip: tip.tip,
                                odds: tip.odds,
                                id: game.id,
                            });
                            oddsOverall = oddsOverall * tip.odds;
                        }
                    });
                });
            }.bind(this));

            this.setState(
                {
                    ticket: ticket,
                    oddsOverall: oddsOverall,
                    win: this.state.amount ? this.state.amount * oddsOverall : "",
                },
                () => { console.log(this.state.oddsOverall) }
            );
        });
    }

    setAmount(amount) {
        if (amount != null && amount < 0) {
            amount = -amount;
        }
        this.setState({
            amount: amount,
        });
        console.log(amount);
    }

    setWin(win) {
        this.setState({
            win: win,
        });
        console.log(win);
    }

    createTicket(alert) {
        if (!this.state.amount || this.state.amount === 0) {
            alert.error(<div>Place your bet!</div>)
            return;
        }

        // prepare request
        let apiBaseUrl = "http://localhost:8081/api"; //todo
        let games = this.state.ticket.map(x => {
            return new Object(
                {
                    "id": x.id,
                    "tip": this.state.tips.filter(y=>y.name===x.tip)[0].id,
                }
            );
        });
        let payload = {
            games: games,
            amount: this.state.amount,
            overallOdds: this.state.overallOdds,
        }

        //console.log(payload);

        // send request
        axios.post(apiBaseUrl + '/saveTicket', payload)
            .then(function (response) {
                console.log(response);
                if (response.status === 200) {
                    console.log("successfully made ticket");
                }
            })
            .catch(function (error) {
                console.log(error);
            });
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
                this.setState({ games: games })
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
                        this.setState({ tips: tips })
                    })
                    .then(() => {
                        console.log("FETCHED TIPS:");
                        console.log(this.state.tips);

                        // init tips
                        let tips = this.state.tips.map((t) => {
                            return t.name;
                        });

                        // take tables object
                        let matches = {}
                        this.state.games.forEach(function (game, index) {
                            let row = {};
                            let currentGame = {};
                            currentGame.data = row;
                            currentGame.id = game.id;
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
                            row.isOver = false;//game.homeGoals===null && game.awayGoals===null ? false : true;
                            row.league = game.competition.name;
                            row.homeGoals = game.homeGoals;
                            row.awayGoals = game.awayGoals;

                            if (!matches.hasOwnProperty(game.competition.name)) {
                                matches[game.competition.name] = [];
                            }
                            matches[game.competition.name].push(currentGame);
                        });

                        this.setState(
                            { matches: matches },
                            () => {
                                console.log(this.state.matches)
                            },
                        );
                    }
                    )
            });
    }

    render() {
        if (this.state.matches != null) {
            return (
                <div className="rowC">
                    <Matches
                        matches={this.state.matches}
                        tips={this.state.tips}
                        handleCellClick={(x, y) => this.handleCellClick(x, y)}
                    />
                    <Ticket
                        ticket={this.state.ticket}
                        win={this.state.win}
                        oddsOverall={this.state.oddsOverall}
                        setAmount={(x) => { this.setAmount(x) }}
                        setWin={(x) => { this.setWin(x) }}
                        amount={this.state.amount}
                        createTicket={(x) => this.createTicket(x)}
                    //removeGameFromTicket={(x) => this.removeGameFromTicket(x)}
                    />
                </div>
            );
        } else {
            return <div></div>;
        }
    }
}
export default Home;