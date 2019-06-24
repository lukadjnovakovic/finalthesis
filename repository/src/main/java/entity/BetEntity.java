package entity;

import javax.persistence.*;

@Entity
@Table(name = "bet")
public class BetEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    private OddsEntity odds;
    @ManyToOne
    private TicketEntity ticket;

    public BetEntity() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public OddsEntity getOdds() {
        return odds;
    }

    public void setOdds(OddsEntity odds) {
        this.odds = odds;
    }

    public TicketEntity getTicket() {
        return ticket;
    }

    public void setTicket(TicketEntity ticket) {
        this.ticket = ticket;
    }
}
