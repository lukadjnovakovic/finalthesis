package entity;

import org.springframework.data.annotation.Reference;

import javax.persistence.*;

@Entity
@Table(name = "bet")
public class BetEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    private OddsEntity odds;
    private Integer ticket;

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

    public int getTicket() {
        return ticket;
    }

    public void setTicket(Integer ticket) {
        this.ticket = ticket;
    }

    public BetEntity(OddsEntity odds, int ticket) {
        this.odds = odds;
        this.ticket = ticket;
    }
}
