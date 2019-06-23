package services;

import dto.UserDTO;
import entity.UserEntity;

import java.util.List;

public interface IUserService {

    List<UserDTO> returnAllUsers();
    UserDTO returnUserById(int id) throws Exception;
    UserDTO returnUserByUsername(String username);
    void saveUser(UserEntity userEntity);

}
