package controllers;

import dto.TeamDTO;
import impl.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
@RestController
public class TeamController {

    @Autowired
    private TeamService teamService;

    @GetMapping(value = "/team")
    public List<TeamDTO> getAllTeams() {
        return teamService.getAllTeams();
    }
}
