package entity;

import org.springframework.lang.Nullable;

import javax.persistence.*;

@Entity
@Table(name = "odds")
public class OddsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @OneToOne
    private Game game;
    @OneToOne
    private TipEntity tip;
    private double odds;
    @Nullable
    private boolean passed;

    public OddsEntity() {
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

    public boolean isPassed() {
        return passed;
    }

    public void setPassed(boolean passed) {
        this.passed = passed;
    }

    public OddsEntity(Game game, TipEntity tip, double odds,boolean passed) {
        this.game = game;
        this.tip = tip;
        this.odds = odds;
        this.passed = passed;
    }
}
