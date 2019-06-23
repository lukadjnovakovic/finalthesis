package services;

import dto.CompetitionDTO;

import java.util.List;

public interface ICompetitionService {

    List<CompetitionDTO> returnAllCompetitions();

    CompetitionDTO returnCompetitionById(int id) throws Exception;
}
