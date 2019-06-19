package dto;

public class BetDTO {

    private Integer id;
    private GameDTO game;
    private TipDTO tip;
    private double odds;
    private TicketDTO ticket;

    public BetDTO() {
    }

    public BetDTO(GameDTO game, TipDTO tip, double odds, TicketDTO ticket) {
        this.game = game;
        this.tip = tip;
        this.odds = odds;
        this.ticket = ticket;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public GameDTO getGame() {
        return game;
    }

    public void setGame(GameDTO game) {
        this.game = game;
    }

    public TipDTO getTip() {
        return tip;
    }

    public void setTip(TipDTO tip) {
        this.tip = tip;
    }

    public double getOdds() {
        return odds;
    }

    public void setOdds(double odds) {
        this.odds = odds;
    }

    public TicketDTO getTicket() {
        return ticket;
    }

    public void setTicket(TicketDTO ticket) {
        this.ticket = ticket;
    }
}
