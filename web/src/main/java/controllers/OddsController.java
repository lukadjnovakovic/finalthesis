package controllers;

import dto.OddsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import services.IOddsService;

import java.util.List;
import java.util.Random;

@Component
public class OddsController {

    @Autowired
    private IOddsService oddsService;

    @Scheduled(fixedDelay = 60000)
    public void randomPassedOdds(){
        List<OddsDTO> oddsDTOS = oddsService.returnAllOdds();

        int random = getRandomNumberInRange(1,oddsDTOS.size());
        random = random -1;

        if(!oddsDTOS.get(random).isPassed()){
            oddsDTOS.get(random).setPassed(true);
        }

        oddsService.saveAllOdds(oddsDTOS);
        System.out.println("updated odds...");
    }

    private static int getRandomNumberInRange(int min, int max) {

        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }
}
