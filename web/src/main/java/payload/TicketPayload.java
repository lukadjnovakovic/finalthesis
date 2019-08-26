package payload;

import java.util.List;

public class TicketPayload {

    private double oddsOverall;
    private double amount;
    private List<Integer> odds;


    public double getOddsOverall() {
        return oddsOverall;
    }

    public void setOddsOverall(double oddsOverall) {
        this.oddsOverall = oddsOverall;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public List<Integer> getOdds() {
        return odds;
    }
}
