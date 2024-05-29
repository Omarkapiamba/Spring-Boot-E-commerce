package com.example.springbootecommerce.controller;

import com.example.springbootecommerce.dto.UserDTO;
import com.example.springbootecommerce.dto.UserRequest;
import com.example.springbootecommerce.exceptions.ResourceNotFoundException;
import com.example.springbootecommerce.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/users")
public class UserController {

    private final UserServiceImpl userProfileService;

    @Autowired
    public UserController(UserServiceImpl userProfileServiceImpl) {
        this.userProfileService = userProfileServiceImpl;
    }

    @GetMapping(path = "/{userId}")
    public UserDTO getUserById(@PathVariable("userId") Long id) {

        return userProfileService.getUserById(id);
    }

    @GetMapping()
    public List<UserDTO> getAllUser() {

        return userProfileService.getAllUser();
    }

    @PostMapping(path = "/create")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserRequest userRequest) {


        UserDTO userDTO = userProfileService.createUser(userRequest);

        return new ResponseEntity<>(userDTO, HttpStatus.CREATED);
    }

    @PutMapping(path = "/update")
    public ResponseEntity<UserDTO> updateUser(@RequestBody UserRequest userRequest) {

        UserDTO userDTO = userProfileService.updateUser(userRequest);

        return new ResponseEntity<>(userDTO, HttpStatus.ACCEPTED);
    }

    @DeleteMapping(path = "/delete/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable("userId") Long id) {
        try {
            userProfileService.deleteUserById(id);
            return new ResponseEntity<>(String.format("User med id: %s är raderad", id), HttpStatus.ACCEPTED);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(String.format("User med id: %s hittades inte", id), HttpStatus.NOT_FOUND);
        }
    }


}
