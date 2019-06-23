package impl;

import dto.CompetitionDTO;
import entity.Competition;
import mappers.CompetitionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.CompetitionRepo;
import services.ICompetitionService;

import java.util.LinkedList;
import java.util.List;

@Service
public class CompetitionServiceImpl implements ICompetitionService {

    @Autowired
    CompetitionRepo competitionRepo;
    @Autowired
    CompetitionMapper competitionMapper;


    @Override
    public List<CompetitionDTO> returnAllCompetitions() {
        List<Competition> competitions = competitionRepo.findAll();
        List<CompetitionDTO> competitionDTOS = new LinkedList<>();

        for (Competition competition : competitions) {
            competitionDTOS.add(competitionMapper.competition2DTO(competition));
        }
        return competitionDTOS;
    }

    @Override
    public CompetitionDTO returnCompetitionById(int id) throws Exception {
        Competition competition = competitionRepo.findById(id).orElse(null);
        if (competition == null) {
            throw new Exception("No competition with given ID");
        }
        return competitionMapper.competition2DTO(competition);
    }
}
