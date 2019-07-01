package mappers;

import dto.UserDTO;
import entity.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDTO user2DTO(UserEntity userEntity);
    UserEntity userEntity(UserDTO userDTO);
}
