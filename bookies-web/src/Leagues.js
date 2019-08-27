import React from "react";
import { Button, ListGroup } from 'react-bootstrap';

function LeagueButton(props) {
    let variant = !props.league.isSelected? "outline-info" : "info";
    return (
        <Button
            onClick={() => props.onClick(props.league.name)}
            variant={variant}>
            {props.league.name}
        </Button>
    );
}

export class Leagues extends React.Component {

    render() {
        let leagues = Array.from(this.props.allLeagues);
        let buttons =
            leagues.map(league => {
                return <ListGroup.Item>
                    <LeagueButton
                        league={league}
                        onClick={this.props.selectLeague}
                    />
                </ListGroup.Item>;
            });


        return (
            <ListGroup>
                {buttons}
            </ListGroup>
        )
    }
}

export default Leagues;