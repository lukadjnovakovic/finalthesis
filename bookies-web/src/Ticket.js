import React from 'react';
import ReactTable from 'react-table';
import  'react-table/react-table.css';
import 'bootstrap/dist/css/bootstrap.css';
import { Form, Button } from 'react-bootstrap';
import './Ticket.css';
import { useAlert } from 'react-alert';

const CreateTicketButton = (props) =>{
    const alert = useAlert()
    return (
        <div className="wrapper">
        <Button
         onClick={() => props.createTicket(alert)}
         className="send"
         variant="success">
            Make ticket
        </Button>
        </div>
    )
}    

export class Ticket extends React.Component{
    // props ticket win oddsOverall setAmount

    handleChange = event => {
        this.props.setAmount(event.target.value);
        this.props.setWin(event.target.value?this.props.oddsOverall*event.target.value:"");
    };

    render(){
        //const data = this.props.ticket.map((x) => <ListGroup.Item key={x.id}> {x.home} - { x.away} {x.tip} {x.odds} </ListGroup.Item> );

        // match tip and odds
        const columnsBets=[{
            id:"match",
            Header:"Match",
            accessor: x => x.home + "-" + x.away,
            width:200,
        },{
            id:"tip",
            Header:"Tip",
            accessor: x => x.tip,
        },{
            id:"odds",
            Header:"Odds",
            accessor: x => x.odds,
        }]

        let ticketTable = null;
        let form = null;
        let create = null;
        if (this.props.ticket.length > 0){ 
            ticketTable = 
                <ReactTable
                    columns={columnsBets}
                    data={this.props.ticket}
                    pageSize={this.props.ticket.length}
                    showPageJump={false}
                    showPageSizeOptions={false}
                    showPageJump={false}
                    showPagination={false}
                />;

            form = <div className='rowC'>
                <Form onSubmit={e => { e.preventDefault(); }}>
                    <Form.Group>
                        <Form.Label>Place:</Form.Label>
                        <Form.Control 
                            type="number" 
                            value={this.props.amount}
                            onChange={this.handleChange}
                            placeholder="Place your bet!"
                            className="quantity"
                         />
                    </Form.Group>
                </Form>
                <Form onSubmit={e => { e.preventDefault(); }}>
                    <Form.Group>
                        <Form.Label>Win:</Form.Label>
                        <Form.Control 
                            type="number" 
                            value={this.props.win}
                            placeholder="$$$"
                            readOnly
                         />
                    </Form.Group>
                </Form>
                <Form onSubmit={e => { e.preventDefault(); }}>
                    <Form.Group>
                        <Form.Label>Total odds:</Form.Label>
                        <Form.Control 
                            type="number" 
                            value={this.props.oddsOverall}
                            placeholder="Odds"
                            readOnly
                         />
                    </Form.Group>
                </Form>
                </div>
            create = <CreateTicketButton createTicket={this.props.createTicket}/>;
        }
        return (
            <div>
                {form}
                {ticketTable}
                {create}
            </div>
        );
    }
}

export default Ticket;


