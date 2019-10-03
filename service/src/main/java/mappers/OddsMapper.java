package mappers;

import dto.OddsDTO;
import entity.OddsEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OddsMapper {

    OddsDTO odds2DTO(OddsEntity oddsEntity);
    OddsEntity dto2entiry(OddsDTO oddsDTO);
}
