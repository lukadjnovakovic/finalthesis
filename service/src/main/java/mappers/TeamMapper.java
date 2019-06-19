package mappers;

import dto.TeamDTO;
import entity.TeamEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TeamMapper {

    TeamDTO team2DTO (TeamEntity teamEntity);
}
