package dbSeeder;

import entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import repository.*;

import java.util.Date;

@Component
public class DBSeeder implements CommandLineRunner {

    @Autowired
    CompetitionRepo competitionRepo;
    @Autowired
    CountryRepo countryRepo;
    @Autowired
    TeamRepo teamRepo;
    @Autowired
    UserRepo userRepo;
    @Autowired
    TipRepo tipRepo;
    @Autowired
    GameRepo gameRepo;
    @Autowired
    OddsRepo oddsRepo;


    @Override
    public void run(String... args) throws Exception {

        Country c1 = new Country("Spain");
        Country c2 = new Country("Italy");
        Country c3 = new Country("England");

        Competition co1 = new Competition("Premier League");
        Competition co2 = new Competition("Primera");
        Competition co3 = new Competition("Serie A");

        UserEntity user = new UserEntity("Luka","Novakovic","luka","luka");

        TipEntity t1 = new TipEntity("1");
        TipEntity tx = new TipEntity("X");
        TipEntity t2 = new TipEntity("2");
        TipEntity tgg = new TipEntity("GG");
        TipEntity tOver = new TipEntity("3+");
        TipEntity tCmb = new TipEntity("1&3+");

        TeamEntity tm1 = new TeamEntity("Real Madrid",c1);
        TeamEntity tm2 = new TeamEntity("Real Betis",c1);
        TeamEntity tm3 = new TeamEntity("Arsenal",c3);
        TeamEntity tm4 = new TeamEntity("QPR",c3);

        Game g1 = new Game(new Date(),tm1,tm2,co2);
        Game g2 = new Game(new Date(),tm3,tm4,co1);

        OddsEntity odds1 = new OddsEntity(g1,t1,2.25);
        OddsEntity odds2 = new OddsEntity(g1,tx,2.25);
        OddsEntity odds3 = new OddsEntity(g1,t2,2.25);
        OddsEntity odds4 = new OddsEntity(g1,tgg,2.25);
        OddsEntity odds5 = new OddsEntity(g1,tOver,2.25);
        OddsEntity odds6 = new OddsEntity(g1,tCmb,4.25);

        OddsEntity odds11 = new OddsEntity(g2,t1,2.25);
        OddsEntity odds12 = new OddsEntity(g2,tx,2.25);
        OddsEntity odds13 = new OddsEntity(g2,t2,2.25);
        OddsEntity odds14 = new OddsEntity(g2,tgg,2.25);
        OddsEntity odds15 = new OddsEntity(g2,tOver,2.25);
        OddsEntity odds16 = new OddsEntity(g2,tCmb,5.25);

        countryRepo.save(c1);
        countryRepo.save(c2);
        countryRepo.save(c3);

        competitionRepo.save(co1);
        competitionRepo.save(co2);
        competitionRepo.save(co3);

        userRepo.save(user);

        tipRepo.save(t1);
        tipRepo.save(tx);
        tipRepo.save(t2);
        tipRepo.save(tgg);
        tipRepo.save(tOver);
        tipRepo.save(tCmb);

        teamRepo.save(tm1);
        teamRepo.save(tm2);
        teamRepo.save(tm3);
        teamRepo.save(tm4);

        gameRepo.save(g1);
        gameRepo.save(g2);

        oddsRepo.save(odds1);
        oddsRepo.save(odds2);
        oddsRepo.save(odds3);
        oddsRepo.save(odds4);
        oddsRepo.save(odds5);
        oddsRepo.save(odds11);
        oddsRepo.save(odds12);
        oddsRepo.save(odds13);
        oddsRepo.save(odds14);
        oddsRepo.save(odds15);
        oddsRepo.save(odds16);
        oddsRepo.save(odds6);


    }
}
