package controllers;

import dto.TeamDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import services.ITeamService;

import java.util.List;

@RestController
public class TeamController {

    @Autowired
    private static ITeamService teamService;

    @GetMapping(value = "/team")
    public static List<TeamDTO> getAllTeams() {
        return teamService.getAllTeams();
    }
}
