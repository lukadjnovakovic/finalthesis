package dto;

public class BetDTO {

    private Integer id;
    private OddsDTO odds;
    private TicketDTO ticket;

    public BetDTO() {
    }

    public BetDTO(OddsDTO odds, TicketDTO ticket) {
        this.odds = odds;
        this.ticket = ticket;
    }

    public OddsDTO getOdds() {
        return odds;
    }

    public void setOdds(OddsDTO odds) {
        this.odds = odds;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public TicketDTO getTicket() {
        return ticket;
    }

    public void setTicket(TicketDTO ticket) {
        this.ticket = ticket;
    }
}
