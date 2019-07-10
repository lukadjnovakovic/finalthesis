package impl;

import dto.UserDTO;
import entity.UserEntity;
import mappers.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.UserRepo;
import services.IUserService;

import java.util.LinkedList;
import java.util.List;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    UserRepo userRepo;
    @Autowired
    UserMapper userMapper;


    @Override
    public List<UserDTO> returnAllUsers() {
        List<UserEntity> userEntities = userRepo.findAll();
        List<UserDTO> userDTOS = new LinkedList<>();

        for (UserEntity userEntity : userEntities) {
            userDTOS.add(userMapper.user2DTO(userEntity));
        }
        return userDTOS;
    }

    @Override
    public UserDTO returnUserById(int id) throws Exception {
        UserEntity userEntity = userRepo.findById(id).orElse(null);
        if (userEntity == null) {
            throw new Exception("No country with given ID");
        }
        return userMapper.user2DTO(userEntity);
    }

    @Override
    public UserDTO returnUserByUsername(String username) {

        UserEntity userEntity = userRepo.findByUsername(username);
        return  userMapper.user2DTO(userEntity);

    }

    @Override
    public void saveUser(UserEntity userEntity) {
        userRepo.save(userEntity);
    }


}
