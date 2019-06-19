package services;

import dto.GameDTO;

import java.util.List;

public interface IGameService {

    List<GameDTO> returnAllGames();

    GameDTO returnGameById(int id) throws Exception;


}
