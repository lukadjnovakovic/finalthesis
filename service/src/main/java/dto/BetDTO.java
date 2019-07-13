package dto;

public class BetDTO {

    private Integer id;
    private OddsDTO odds;
    private int ticket;

    public BetDTO() {
    }

    public BetDTO(OddsDTO odds, int ticket) {
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

    public int getTicket() {
        return ticket;
    }

    public void setTicket(int ticket) {
        this.ticket = ticket;
    }
}
