package entity;

import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "ticket")
public class TicketEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @OneToMany
    @JoinColumn(name = "ticket")
    private List<BetEntity> bets;
    @ManyToOne
    private UserEntity user;
    private double payment;
    private double overallOdds;
    private double win;
    private Date dateOfCreation;

    public TicketEntity() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<BetEntity> getBets() {
        return bets;
    }

    public void setBets(List<BetEntity> bets) {
        this.bets = bets;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public double getPayment() {
        return payment;
    }

    public void setPayment(double payment) {
        this.payment = payment;
    }

    public double getOverallOdds() {
        return overallOdds;
    }

    public void setOverallOdds(double overallOdds) {
        this.overallOdds = overallOdds;
    }

    public double getWin() {
        return win;
    }

    public void setWin(double win) {
        this.win = win;
    }

    public Date getDateOfCreation() {
        return dateOfCreation;
    }

    public void setDateOfCreation(Date dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }
}
