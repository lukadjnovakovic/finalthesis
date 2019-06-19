package mappers;

import dto.TipDTO;
import entity.TipEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TipMapper {

    TipDTO tip2DTO(TipEntity tipEntity);
}
