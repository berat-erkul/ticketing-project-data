package com.cydeo.service.impl;

import com.cydeo.dto.UserDTO;
import com.cydeo.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public List<UserDTO> listAllUsers() {
        return List.of();
    }

    @Override
    public UserDTO findByUserName(String username) {
        return null;
    }

    @Override
    public void save(UserDTO userDTO) {

    }

    @Override
    public void update(UserDTO userDTO) {

    }

    @Override
    public void delete(String username) {

    }
}
