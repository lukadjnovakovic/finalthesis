package mappers;

import dto.CountryDTO;
import entity.Country;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CountryMapper {

    CountryDTO country2DTO(Country country);
}
