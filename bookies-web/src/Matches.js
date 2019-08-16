import React from "react";
import ReactTable from 'react-table';
import 'react-table/react-table.css';
import 'bootstrap/dist/css/bootstrap.css';
import {Button} from 'react-bootstrap';

function Cell(props){
    let data = props.value.data;
    let selectedTip = data.tips.filter(x => x.tip === props.tip)[0];
    let variant = !selectedTip.isSelected ? "outline-info" : "info";
    variant = data.isOver ? "secondary" : variant;
    return (
        <Button 
            onClick={()=>props.onClick(props.value, props.tip)}
            disabled={data.isOver} 
            variant={variant}>
            {selectedTip.odds}
        </Button>
    );
}

export class Matches extends React.Component {

    render() {
        let matches = [];
        Object.keys(this.props.matches).forEach(league => {
            const columnsTips = this.props.tips.map((t) => {
                return {
                    Header: t.name,
                    accessor: y => y,
                    Cell: y => <Cell 
                                onClick={this.props.handleCellClick} 
                                tip={t.name}
                                value={y.value}/>,
                    id: t.name,
                    resizable: false,
                    sortable: false,
                    width:80,
                }
            });
            const columnsMatches = [
                {
                    id: 'homeTeam',
                    Header: "Home",
                    accessor: x => x.data.homeTeam,
                    width: 150,
                    resizable: false,
                    sortable: true,
                },
                {
                    id: 'awayTeam',
                    Header: "Away",
                    accessor: x => x.data.awayTeam,
                    width: 150,
                    resizable: false,
                    sortable: true,
                },
                {
                    id: 'score',
                    Header: "Score",
                    accessor: x => x.data.isOver ? x.data.homeGoals + " - " + x.data.awayGoals : "",
                    width: 150,
                    resizable: false,
                    sortable: true,
                }
            ].concat(columnsTips);

            const columnsMatchesWithTitle = [{
                Header: league,
                columns: columnsMatches,
            }];

            matches = matches.concat(
                <ReactTable
                    data={this.props.matches[league]}
                    columns={columnsMatchesWithTitle}
                    defaultPageSize={this.props.matches[league].length > 5 ? 5 : this.props.matches[league].length}
                    key={league}
                />
            );
        });
        return (
            <div>
                {matches}
            </div>
        );
    }
}

export default Matches;