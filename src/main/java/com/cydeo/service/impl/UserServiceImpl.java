package com.cydeo.service.impl;

import com.cydeo.dto.UserDTO;
import com.cydeo.entity.User;
import com.cydeo.mapper.MapperUtil;
import com.cydeo.repository.UserRepository;
import com.cydeo.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final MapperUtil mapperUtil;

    public UserServiceImpl(UserRepository userRepository,MapperUtil mapperUtil) {
        this.userRepository = userRepository;
        this.mapperUtil = mapperUtil;
    }

    @Override
    public List<UserDTO> listAllUsers() {
        List<User> userList = userRepository.findAll();
        return userList.stream()
                .map(user -> mapperUtil.convert(user,UserDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO findByUserName(String username) {
        // userRepository.findByUserName(username) --> User
        return mapperUtil.convert(userRepository.findByUserName(username),UserDTO.class);
    }

    @Override
    public void save(UserDTO userDTO) {
        // userRepository.save(userDTO); // This will not work because we need to convert UserDTO to User
        userRepository.save(mapperUtil.convert(userDTO, User.class));
    }

    @Override                                       //  UserDTO       User
    public void update(UserDTO userDTO) {  //  (UserName)    (#Id,UserName))
        User foundUser = userRepository.findByUserName(userDTO.getUserName());
        User updatedUser = mapperUtil.convert(userDTO, User.class);

        updatedUser.setId(foundUser.getId());

        userRepository.save(updatedUser);
    }

    @Override
    public void delete(String username) {
        User user =userRepository.findByUserName(username);
        user.setIsDeleted(true);
        userRepository.save(user);
    }
}
