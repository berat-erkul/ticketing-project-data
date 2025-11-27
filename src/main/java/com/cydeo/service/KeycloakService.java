package com.cydeo.service;

import com.cydeo.dto.UserDTO;

public interface KeycloakService {

    String getLoggedInUserName();

    void userCreate(UserDTO dto);

    void userDelete(String username);

    void userUpdate(UserDTO userDTO);

}
