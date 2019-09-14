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
    TipRepo tipRepo;
    @Autowired
    GameRepo gameRepo;
    @Autowired
    OddsRepo oddsRepo;
    @Autowired
    TicketRepo ticketRepo;
    @Autowired
    BetRepo betRepo;
    @Autowired
    UserRepo userRepo;


    @Override
    public void run(String... args) throws Exception {

        Country c1 = new Country("Spain");
        Country c2 = new Country("Italy");
        Country c3 = new Country("England");
        Country c4 = new Country("France");

        Competition co1 = new Competition("Premier League");
        Competition co2 = new Competition("Primera");
        Competition co3 = new Competition("Serie A");
        Competition co4 = new Competition("Ligue 1");

        TipEntity t1 = new TipEntity("1");
        TipEntity tx = new TipEntity("X");
        TipEntity t2 = new TipEntity("2");
        TipEntity tgg = new TipEntity("GG");
        TipEntity tOver = new TipEntity("3+");
        TipEntity tCmb = new TipEntity("1&3+");

        TeamEntity tm1 = new TeamEntity("Real Madrid", c1);
        TeamEntity tm2 = new TeamEntity("Real Betis", c1);
        TeamEntity tm3 = new TeamEntity("Arsenal", c3);
        TeamEntity tm4 = new TeamEntity("Roma", c2);
        TeamEntity tm5 = new TeamEntity("Juventus", c2);
        TeamEntity tm6 = new TeamEntity("QPR", c3);
        TeamEntity tm7 = new TeamEntity("Napoli", c2);
        TeamEntity tm8 = new TeamEntity("Parma", c2);
        TeamEntity tm9 = new TeamEntity("PSG", c3);
        TeamEntity tm10 = new TeamEntity("Lyon", c4);
        TeamEntity tm11 = new TeamEntity("Villarreal", c1);
        TeamEntity tm12 = new TeamEntity("Valladolid", c1);

        Game g1 = new Game(new Date(), tm1, tm2, co2, 2, 1);
        Game g2 = new Game(new Date(), tm3, tm6, co1, 2, 0);
        Game g3 = new Game(new Date(), tm4, tm5, co3, 2, 0);
        Game g4 = new Game(new Date(), tm7, tm8, co3, 2, 0);
        Game g5 = new Game(new Date(), tm9, tm10, co4, 2, 0);
        Game g6 = new Game(new Date(), tm11, tm12, co2, 2, 0);

        OddsEntity odds1 = new OddsEntity(g1, t1, 1.75, false);
        OddsEntity odds2 = new OddsEntity(g1, tx, 2.2, false);
        OddsEntity odds3 = new OddsEntity(g1, t2, 1.9, false);
        OddsEntity odds4 = new OddsEntity(g1, tgg, 3.55, false);
        OddsEntity odds5 = new OddsEntity(g1, tOver, 4.2, false);
        OddsEntity odds6 = new OddsEntity(g1, tCmb, 3.83, false);

        OddsEntity odds11 = new OddsEntity(g2, t1, 1.15, false);
        OddsEntity odds12 = new OddsEntity(g2, tx, 2.60, false);
        OddsEntity odds13 = new OddsEntity(g2, t2, 4.4, false);
        OddsEntity odds14 = new OddsEntity(g2, tgg, 3.00, false);
        OddsEntity odds15 = new OddsEntity(g2, tOver, 2.5, false);
        OddsEntity odds16 = new OddsEntity(g2, tCmb, 5.25, false);

        OddsEntity odds17 = new OddsEntity(g3, t1, 1.15, false);
        OddsEntity odds18 = new OddsEntity(g3, tx, 2.60, false);
        OddsEntity odds19 = new OddsEntity(g3, t2, 4.4, false);
        OddsEntity odds20 = new OddsEntity(g3, tgg, 3.00, false);
        OddsEntity odds21 = new OddsEntity(g3, tOver, 2.5, false);
        OddsEntity odds22 = new OddsEntity(g3, tCmb, 5.25, false);

        OddsEntity odds23 = new OddsEntity(g4, t1, 1.15, false);
        OddsEntity odds24 = new OddsEntity(g4, tx, 2.60, false);
        OddsEntity odds25 = new OddsEntity(g4, t2, 4.4, false);
        OddsEntity odds26 = new OddsEntity(g4, tgg, 3.00, false);
        OddsEntity odds27 = new OddsEntity(g4, tOver, 2.5, false);
        OddsEntity odds28 = new OddsEntity(g4, tCmb, 5.25, false);

        OddsEntity odds29 = new OddsEntity(g5, t1, 1.15, false);
        OddsEntity odds30 = new OddsEntity(g5, tx, 2.60, false);
        OddsEntity odds31 = new OddsEntity(g5, t2, 4.4, false);
        OddsEntity odds32 = new OddsEntity(g5, tgg, 3.00, false);
        OddsEntity odds33 = new OddsEntity(g5, tOver, 2.5, false);
        OddsEntity odds34 = new OddsEntity(g5, tCmb, 5.25, false);

        OddsEntity odds35 = new OddsEntity(g6, t1, 1.15, false);
        OddsEntity odds36 = new OddsEntity(g6, tx, 2.60, false);
        OddsEntity odds37 = new OddsEntity(g6, t2, 4.4, false);
        OddsEntity odds38 = new OddsEntity(g6, tgg, 3.00, false);
        OddsEntity odds39 = new OddsEntity(g6, tOver, 2.5, false);
        OddsEntity odds40 = new OddsEntity(g6, tCmb, 5.25, false);

        UserEntity user1 = new UserEntity("Luka", "Novakovic", "lule1313", "lule1313");
        userRepo.save(user1);

        TicketEntity ticket1 = new TicketEntity();
        ticket1.setUser(user1);

        ticketRepo.save(ticket1);

        userRepo.save(user1);



        countryRepo.save(c1);
        countryRepo.save(c2);
        countryRepo.save(c3);
        countryRepo.save(c4);

        competitionRepo.save(co1);
        competitionRepo.save(co2);
        competitionRepo.save(co3);
        competitionRepo.save(co4);


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
        teamRepo.save(tm5);
        teamRepo.save(tm6);
        teamRepo.save(tm7);
        teamRepo.save(tm8);
        teamRepo.save(tm9);
        teamRepo.save(tm10);
        teamRepo.save(tm11);
        teamRepo.save(tm12);


        gameRepo.save(g1);
        gameRepo.save(g2);
        gameRepo.save(g3);
        gameRepo.save(g4);
        gameRepo.save(g5);
        gameRepo.save(g6);




        oddsRepo.save(odds1);
        oddsRepo.save(odds2);
        oddsRepo.save(odds3);
        oddsRepo.save(odds4);
        oddsRepo.save(odds5);
        oddsRepo.save(odds6);
        oddsRepo.save(odds11);
        oddsRepo.save(odds12);
        oddsRepo.save(odds13);
        oddsRepo.save(odds14);
        oddsRepo.save(odds15);
        oddsRepo.save(odds16);
        oddsRepo.save(odds17);
        oddsRepo.save(odds18);
        oddsRepo.save(odds19);
        oddsRepo.save(odds20);
        oddsRepo.save(odds21);
        oddsRepo.save(odds22);
        oddsRepo.save(odds23);
        oddsRepo.save(odds24);
        oddsRepo.save(odds25);
        oddsRepo.save(odds26);
        oddsRepo.save(odds27);
        oddsRepo.save(odds28);
        oddsRepo.save(odds29);
        oddsRepo.save(odds30);
        oddsRepo.save(odds31);
        oddsRepo.save(odds32);
        oddsRepo.save(odds33);
        oddsRepo.save(odds34);
        oddsRepo.save(odds35);
        oddsRepo.save(odds36);
        oddsRepo.save(odds37);
        oddsRepo.save(odds38);
        oddsRepo.save(odds39);
        oddsRepo.save(odds40);



        BetEntity bet1 = new BetEntity(odds2, ticket1.getId());
        BetEntity bet2 = new BetEntity(odds13, ticket1.getId());

        betRepo.save(bet1);
        betRepo.save(bet2);
    }
}
