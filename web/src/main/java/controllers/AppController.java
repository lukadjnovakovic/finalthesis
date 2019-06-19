package controllers;

import dto.GameDTO;
import dto.TeamDTO;
import impl.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import services.IGameService;
import services.ITeamService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class AppController {

    @Autowired
    private ITeamService teamService;
    @Autowired
    private IGameService gameService;

    @GetMapping("/")
    public String helloWorld(){
        return "index";
    }

    @RequestMapping("/games")
    public String renderGamesPage(HttpServletRequest request) {

        List<GameDTO> allGames = gameService.returnAllGames();
        request.setAttribute("games", allGames);

        return "index";
    }
}
