package services;

import dto.BetDTO;

import java.util.List;

public interface IBetService {

    List<BetDTO> returnAllBets();

    BetDTO returnBetById(int id) throws Exception;

    List<BetDTO> returnAllBetsByTicketId(Integer id);
}
