import React from 'react';
import './Home.css';
import Matches from "./Matches";
import Ticket from './Ticket';
import Leagues from './Leagues';
import 'react-table/react-table.css';
import 'bootstrap/dist/css/bootstrap.css';
import axios from "axios";
import queryString from 'query-string';

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
            leagues: null,
        }
    }

    selectLeague(leagueName) {
        this.setState(state => {
            const newState = state.leagues.forEach(function (league) {
                if (league.name === leagueName) {
                    league.isSelected = !league.isSelected;
                }
            })
            return {
                newState,
            }
        });
    }

    clearTicket(){
        Object.keys(this.state.matches).forEach(function(leagueName){
            this.setState(state=>{
                const newState = state.matches[leagueName].map(match=>{
                    match.data.tips.map(x=>{
                        x.isSelected = false;
                    })
                })
                return {
                    newState,
                }
            })
        }.bind(this));
        this.setState({
            amount: "",
            win:"",
            oddsOverall: 1,
            ticket:[],
        })
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

    completePayment(paymentId, payerId) {

        let apiBaseUrlPayPal = 'http://localhost:8081/';

        let config = {
            headers: {
                "Authorization": "Bearer " + this.props.token,
            }
        }

        axios.post(apiBaseUrlPayPal + 'api/complete/payment?paymentId=' + paymentId + '&PayerID=' + payerId ,config)
            .then(function (response) {
                console.log(response);
                if (response.status === 'success') {
                    console.log('payment success')
                }
            })
            .catch(function (error) {
                console.log(error);
            });
    }

    makePayment(sum) {

        let apiBaseUrlPayPal = 'http://localhost:8081/';

        let config = {
            headers: {
                "Authorization": "Bearer " + this.props.token,
            }
        }

        axios.post(apiBaseUrlPayPal + 'api/make/payment?sum='+sum,config)
            .then(function (response) {
                console.log(response);
                if (response.status === 200) {
                    console.log('ggghghghgghghghghg');
                    var win = window.open(response.data.redirect_url, '_self');
                    win.focus();
                }
            })
            .catch(function (error) {
                console.log(error);
            });
    }





    createTicket(alert) {

        if (!this.state.amount || this.state.amount === 0) {
            alert.error(<div>Place your bet!</div>)
            return;
        }

        this.makePayment(this.state.amount);

        // prepare request
        let apiBaseUrl = "http://localhost:8081/api"; //todo
        let odds = [];
        Object.keys(this.state.matches).forEach(function (league) {
            this.state.matches[league].forEach(function (game) {
                let selected = game.data.tips.filter(x => x.isSelected)[0];
                if (selected) {
                    odds.push(game.data.tips.filter(x => x.isSelected)[0].oddsID);
                }
            });
        }.bind(this));

        let payload = {
            odds: odds,
            amount: this.state.amount,
            oddsOverall: this.state.oddsOverall,
        }

        let config = {
            headers: {
                "Authorization": "Bearer " + this.props.token,
            }
        }

        console.log(payload);

        // send request
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

        this.clearTicket();
    }

    componentDidMount() {
        const values = queryString.parse(window.location.search);
        console.log(values);
        console.log(values.PayerID);
        console.log(values.paymentId);

        if(values.paymentId){
            this.completePayment(values.PayerID,values.paymentId);
        }

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
                            //console.log("FETCHED TIPS:");
                            //console.log(this.state.tips);

                            // init tips
                            let tips = this.state.tips.map((t) => {
                                return t.name;
                            });

                            // take tables object
                            let matches = {}
                            let leagues = new Set([])
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
                                        oddsID: game.odds.filter(x => x.tip.name === tip)[0].id
                                    });
                                });

                                row.homeTeam = game.homeTeam.name;
                                row.awayTeam = game.awayTeam.name;
                                row.isOver = false;//game.homeGoals===null && game.awayGoals===null ? false : true;
                                row.league = game.competition.name;
                                row.homeGoals = game.homeGoals;
                                row.awayGoals = game.awayGoals;

                                let league = {}
                                league.name = game.competition.name;
                                league.isSelected = false;

                                leagues.add(league);

                                if (!matches.hasOwnProperty(game.competition.name)) {
                                    matches[game.competition.name] = [];
                                }
                                matches[game.competition.name].push(currentGame);
                            });


                            this.setState(
                                {
                                    matches: matches,
                                    leagues: leagues,
                                },
                                () => {
                                    //console.log(this.state.matches);
                                    //console.log(this.state.leagues);
                                },
                            );
                        }
                    )
            });
    }

    render() {
        if (this.state.matches != null) {

            let matches = {}
            this.state.leagues.forEach(function(league){
                if (league.isSelected){
                    matches[league.name] = this.state.matches[league.name];
                }
            }.bind(this));
            if (!Object.keys(matches).length){
                matches = this.state.matches;
            }

            return (
                <div className="rowC">
                    <Leagues
                        selectLeague={(x) => this.selectLeague(x)}
                        allLeagues={this.state.leagues}
                    />
                    <Matches
                        matches={matches}
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
                    />
                </div>
            );
        } else {
            return <div></div>;
        }
    }
}
export default Home;