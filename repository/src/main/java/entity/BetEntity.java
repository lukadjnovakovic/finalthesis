package entity;

import javax.persistence.*;

@Entity
@Table(name = "bet")
public class BetEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    private Game game;
    @ManyToOne
    private TipEntity tip;
    private double odds;
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

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public TipEntity getTip() {
        return tip;
    }

    public void setTip(TipEntity tip) {
        this.tip = tip;
    }

    public double getOdds() {
        return odds;
    }

    public void setOdds(double odds) {
        this.odds = odds;
    }

    public TicketEntity getTicket() {
        return ticket;
    }

    public void setTicket(TicketEntity ticket) {
        this.ticket = ticket;
    }
}
