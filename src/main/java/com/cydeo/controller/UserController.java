package com.cydeo.controller;

import com.cydeo.dto.ResponseWrapper;
import com.cydeo.dto.UserDTO;
import com.cydeo.service.RoleService;
import com.cydeo.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private final RoleService roleService;
    private final UserService userService;

    public UserController(RoleService roleService, UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    // api/v1/user
    @GetMapping
    public ResponseEntity<ResponseWrapper> getUsers() {
        return ResponseEntity.ok(ResponseWrapper.builder()
                .message("Users are successfully retrieved")
                .data(userService.listAllUsers())
                .code(HttpStatus.OK.value())
                .success(true)
                .build());
    }

    // api/v1/user/{username}
    @GetMapping("/{username}")
    public ResponseEntity<ResponseWrapper> getByUserName(@PathVariable("username") String username){
        return ResponseEntity.ok(ResponseWrapper.builder()
                .message("User is successfully retrieved")
                .data(userService.findByUserName(username))
                .code(HttpStatus.OK.value())
                .success(true)
                .build());
    }

    // api/v1/user/create  + body
   @PostMapping("/create")
   public ResponseEntity<ResponseWrapper> createUserByBody(@RequestBody UserDTO userDTO){

        userService.save(userDTO); //persist the user

        return ResponseEntity.status(HttpStatus.CREATED).body(
                ResponseWrapper.builder()
                        .message("User is successfully created")
                        .data(userDTO)
                        .code(HttpStatus.CREATED.value())
                        .success(true)
                        .build()
        );
   }

    // api/v1/user/update  + body
    @PutMapping("/update")
    public ResponseEntity<ResponseWrapper> updateUser(@RequestBody UserDTO userDTO){
        userService.update(userDTO);

        return ResponseEntity.status(HttpStatus.OK).body(ResponseWrapper.builder()
                .message("User is successfully updated")
                .data(userDTO)
                .code(HttpStatus.OK.value())
                .success(true)
                .build());
    }


    @DeleteMapping("/delete/{username}")
    public ResponseEntity<ResponseWrapper> deleteUserByUsername(@PathVariable("username") String username){

        userService.delete(username);

        return ResponseEntity.status(HttpStatus.OK).body(ResponseWrapper.builder()
                .message("User is successfully deleted")
                .success(true)
                .code(HttpStatus.OK.value())
                .build());
    }
}

