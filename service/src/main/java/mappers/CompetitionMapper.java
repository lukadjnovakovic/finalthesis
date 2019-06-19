package mappers;

import dto.CompetitionDTO;
import entity.Competition;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CompetitionMapper {

    CompetitionDTO competition2DTO(Competition competition);
}
