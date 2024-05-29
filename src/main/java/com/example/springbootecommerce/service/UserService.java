package com.example.springbootecommerce.service;

import com.example.springbootecommerce.dto.UserDTO;
import com.example.springbootecommerce.dto.UserRequest;

import java.util.List;

public interface UserService {

    UserDTO getUserById(Long id);

    List<UserDTO> getAllUser();

    UserDTO createUser(UserRequest userRequest);

    UserDTO updateUser(UserRequest userRequest);

    void deleteUserById(Long id);
}
