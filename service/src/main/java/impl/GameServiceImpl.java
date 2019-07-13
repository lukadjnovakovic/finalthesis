package impl;

import dto.GameDTO;
import dto.OddsDTO;
import entity.Game;
import entity.OddsEntity;
import entity.TipEntity;
import mappers.GameMapper;
import mappers.OddsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.GameRepo;
import repository.OddsRepo;
import services.IGameService;

import java.util.LinkedList;
import java.util.List;

@Service
public class GameServiceImpl implements IGameService {

    @Autowired
    GameRepo gameRepo;
    @Autowired
    GameMapper gameMapper;
    @Autowired
    OddsRepo oddsRepo;
    @Autowired
    OddsMapper oddsMapper;


    @Override
    public List<GameDTO> returnAllGames() {
        List<Game> gameEntities = gameRepo.findAll();
        List<GameDTO> gameDTOS = new LinkedList<>();

        for (Game game : gameEntities) {
            List<OddsEntity> odds = oddsRepo.findAllByGame_Id(game.getId());
            //if rezultat
            odds = processOdds(odds);

            List<OddsDTO> oddsDTOS = new LinkedList<>();
            for (OddsEntity odd : odds) {
                oddsDTOS.add(oddsMapper.odds2DTO(odd));
            }
            GameDTO gameDTO = gameMapper.game2DTO(game);

            gameDTO.setOdds(oddsDTOS);
            gameDTOS.add(gameDTO);
        }
        return gameDTOS;
    }

    private List<OddsEntity> processOdds(List<OddsEntity> odds) {

        for (OddsEntity oddsEntity :
                odds) {
            TipEntity tip = oddsEntity.getTip();
            oddsEntity.setPassed(isOddsPassed(tip, oddsEntity));
        }

        return odds;
    }

    private boolean isOddsPassed(TipEntity tip, OddsEntity oddsEntity) {

        String tipName = tip.getName();
        int homeGoals = oddsEntity.getGame().getHomeGoals();
        int awayGoals = oddsEntity.getGame().getAwayGoals();

        if (tipName.equals("1")) {
            if (homeGoals > awayGoals) {
                return true;
            }
        }

        return false;
    }

    @Override
    public GameDTO returnGameById(int id) throws Exception {
        Game game = gameRepo.findById(id).orElse(null);
        if (game == null) {
            throw new Exception("No game with given ID");
        }
        return gameMapper.game2DTO(game);
    }
}
