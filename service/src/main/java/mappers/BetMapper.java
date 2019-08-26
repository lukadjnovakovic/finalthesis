package mappers;

import dto.BetDTO;
import entity.BetEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BetMapper {

    BetDTO bet2DTO(BetEntity betEntity);
    BetEntity bet2Entity(BetDTO betDTO);
}
