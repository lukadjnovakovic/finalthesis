package impl;

import dto.TeamDTO;
import entity.TeamEntity;

import mappers.TeamMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.TeamRepo;
import services.ITeamService;

import java.util.LinkedList;
import java.util.List;

@Service
public class TeamService implements ITeamService {

    @Autowired
    private TeamRepo teamRepo;
    @Autowired
    private TeamMapper teamMapper;


    @Override
    public List<TeamDTO> getAllTeams() {
        List<TeamEntity> teamEntities = new LinkedList<>();
        List<TeamDTO> teamDTOS = new LinkedList<>();

        teamEntities = teamRepo.findAll();

        for (TeamEntity teamEntity : teamEntities) {
            teamDTOS.add(teamMapper.team2DTO(teamEntity));
        }
        return teamDTOS;
    }
}
