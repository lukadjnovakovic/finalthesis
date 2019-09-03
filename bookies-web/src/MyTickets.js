import React from 'react';
import ReactTable from 'react-table';
import 'react-table/react-table.css';
import 'bootstrap/dist/css/bootstrap.css';
import { Button } from 'react-bootstrap';

const api_base = 'http://localhost:8081';

// function Cell(props) {
//     let variant = props.passed ? "success" : "danger";
//     return (
//         <Button
//             disabled={true}
//             variant={variant}>
//             {props.passed ? "Yes" : "No"}
//         </Button>
//     );
// }

export class MyTickets extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            mytickets: null,
        }
    }

    componentDidMount() {
        fetch(api_base + '/api/tickets',
            {
                method: 'GET',
                headers: {
                    "Authorization": "Bearer " + this.props.token,
                },
            }
        ).then(
            res => res.json()
        ).then((mytickets) => {
            this.setState({
                mytickets: mytickets,
            });
            console.log(mytickets);
        });
    }

    render() {
        const columnsTicket = [{
            id: "match",
            Header: "Match",
            accessor: x => x.odds.game.homeTeam.name + " - " + x.odds.game.awayTeam.name,
        }, {
            id: "tip",
            Header: "Tip",
            accessor: x => x.odds.tip.name,
        }, {
            id: "passed",
            Header: "Passed",
            accessor: x => x.odds.passed ? "Yes" : "No",
            // Cell: y => <Cell 
            //             passed={y.odds.passed}
            //             />,
        }, {
            id: "odds",
            Header: "Odds",
            accessor: x => x.odds.odds,
        }];

        const columnsTicketInfo = [{
            id: "date",
            Header: "Date of Creation",
            accessor: x => x.dateOfCreation
        }, {
            id: "oddsOverall",
            Header: "Odds Overall",
            accessor: x => x.overallOdds
        }, {
            id: "prize",
            Header: "Prize",
            accessor: x => x.win
        }, {
            id: "invested",
            Header: "Invested",
            accessor: x => x.payment
        }]

        let ticketTable = [];
        if (this.state.mytickets) {
            for (let i = 0; i < this.state.mytickets.length; i++) {
                //one element array
                let meta = [this.state.mytickets[i],]
                ticketTable.push(
                    <div>
                        <h4>ticket #{i}</h4>
                        <ReactTable
                            columns={columnsTicketInfo}
                            data={meta}
                            pageSize={1}
                            showPageJump={false}
                            showPageSizeOptions={false}
                            showPagination={false}
                            sortable={false}
                        />
                        <ReactTable
                            columns={columnsTicket}
                            data={this.state.mytickets[i].bets}
                            pageSize={this.state.mytickets[i].bets.length}
                            showPageJump={false}
                            showPageSizeOptions={false}
                            showPagination={false}
                        />
                    </div>
                );
            }
        }

        //console.log(this.state.mytickets);

        return (
            <div>
                {ticketTable.map(ticket => (
                    <div key={ticket}>{ticket}</div>
                ))}
            </div>
        );
    }


}

export default MyTickets;