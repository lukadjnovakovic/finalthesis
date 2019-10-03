package services;

import dto.OddsDTO;

import java.util.List;

public interface IOddsService {

    List<OddsDTO> returnAllOdds();

    OddsDTO returOddsDTObyId(int id);

    void saveAllOdds(List<OddsDTO> oddsDTOS);
}
