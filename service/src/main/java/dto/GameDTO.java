package dto;

import java.util.Date;
import java.util.List;

public class GameDTO {

    private Integer id;
    private Date date;
    private TeamDTO homeTeam;
    private TeamDTO awayTeam;
    private CompetitionDTO competition;
    private List<OddsDTO> odds;
    private int homeGoals;
    private int awayGoals;

    public GameDTO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public TeamDTO getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(TeamDTO homeTeam) {
        this.homeTeam = homeTeam;
    }

    public TeamDTO getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(TeamDTO awayTeam) {
        this.awayTeam = awayTeam;
    }

    public CompetitionDTO getCompetition() {
        return competition;
    }

    public void setCompetition(CompetitionDTO competition) {
        this.competition = competition;
    }

    public int getHomeGoals() {
        return homeGoals;
    }

    public void setHomeGoals(int homeGoals) {
        this.homeGoals = homeGoals;
    }

    public int getAwayGoals() {
        return awayGoals;
    }

    public void setAwayGoals(int awayGoals) {
        this.awayGoals = awayGoals;
    }

    public GameDTO(Date date, TeamDTO homeTeam, TeamDTO awayTeam, CompetitionDTO competition,int homeGoals,int awayGoals) {
        this.date = date;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.competition = competition;
        this.homeGoals = homeGoals;
        this.awayGoals = awayGoals;
    }

    public List<OddsDTO> getOdds() {
        return odds;
    }

    public void setOdds(List<OddsDTO> odds) {
        this.odds = odds;
    }
}
