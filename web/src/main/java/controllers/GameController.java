package controllers;

import dto.GameDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import services.IGameService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class GameController {

    @Autowired
    IGameService gameService;

    @GetMapping(value = "/games")
    public List<GameDTO> getAllGames(){
        return gameService.returnAllGames();
    }

}
