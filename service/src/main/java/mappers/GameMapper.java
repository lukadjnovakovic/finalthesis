package mappers;

import dto.GameDTO;
import entity.Game;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GameMapper {


    GameDTO game2DTO (Game game);
}
