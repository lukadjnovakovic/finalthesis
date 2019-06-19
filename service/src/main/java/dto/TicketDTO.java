package dto;

import java.util.Date;
import java.util.List;

public class TicketDTO {

    private Integer id;
    private List<BetDTO> bets;
    private UserDTO user;
    private double payment;
    private double overallOdds;
    private double win;
    private Date dateOfCreation;

    public TicketDTO() {
    }

    public TicketDTO(List<BetDTO> bets, UserDTO user, double payment, double overallOdds, double win, Date dateOfCreation) {
        this.bets = bets;
        this.user = user;
        this.payment = payment;
        this.overallOdds = overallOdds;
        this.win = win;
        this.dateOfCreation = dateOfCreation;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<BetDTO> getBets() {
        return bets;
    }

    public void setBets(List<BetDTO> bets) {
        this.bets = bets;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
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
