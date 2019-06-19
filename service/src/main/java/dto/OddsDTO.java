package dto;

public class OddsDTO {

    private Integer id;
    private GameDTO game;
    private TipDTO tip;
    private double odds;

    public OddsDTO() {
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

    public OddsDTO(GameDTO game, TipDTO tip, double odds) {
        this.game = game;
        this.tip = tip;
        this.odds = odds;
    }
}
