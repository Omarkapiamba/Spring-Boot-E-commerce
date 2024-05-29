package com.example.springbootecommerce.service.impl;

import com.example.springbootecommerce.dto.UserDTO;
import com.example.springbootecommerce.dto.UserRequest;
import com.example.springbootecommerce.dto.dtoMapper.UserDTOMapper;
import com.example.springbootecommerce.exceptions.ResourceNotFoundException;
import com.example.springbootecommerce.repository.UserRepository;
import com.example.springbootecommerce.service.UserService;
import com.example.springbootecommerce.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserDTOMapper userDTOMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           UserDTOMapper userDTOMapper) {
        this.userRepository = userRepository;
        this.userDTOMapper = userDTOMapper;
    }

    @Override
    public UserDTO getUserById(Long id) {

        if (userRepository.existsById(id)) {
            return userRepository.findById(id)
                    .map(userDTOMapper)
                    .get();
        } else {
            throw new ResourceNotFoundException("User", "id", id);
        }
    }

    @Override
    public List<UserDTO> getAllUser() {
        return userRepository.findAll()
                .stream()
                .map(userDTOMapper)
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO createUser(UserRequest userRequest) {

        User createdUser = userRequestToUser(userRequest);
        userRepository.save(createdUser);

        return userDTOMapper.apply(createdUser);
    }

    @Override
    public UserDTO updateUser(UserRequest userRequest) {

        User updatedUser = userRepository.findById(userRequest.id())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userRequest.id()));

        updatedUser.setId(userRequest.id());
        updatedUser.setUsername(userRequest.username());
        updatedUser.setName(userRequest.name());
        updatedUser.setLastName(userRequest.lastName());
        updatedUser.setPersonalNumber(userRequest.personalNumber());
        updatedUser.setPhoneNumber(userRequest.phoneNumber());
        updatedUser.setAdress(userRequest.adress());
        updatedUser.setRegistrationDate(userRequest.registrationDate());
        updatedUser.setUserRole(userRequest.userRole());
        updatedUser.setActive(userRequest.active());

        return userDTOMapper.apply(updatedUser);
    }

    @Override
    public void deleteUserById(Long id) {

        userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));

        userRepository.deleteById(id);

    }

    private User userRequestToUser(UserRequest userRequest) {

        return new User(
                userRequest.id(),
                userRequest.username(),
                userRequest.password(),
                userRequest.name(),
                userRequest.lastName(),
                userRequest.personalNumber(),
                userRequest.phoneNumber(),
                userRequest.adress(),
                userRequest.email(),
                userRequest.registrationDate(),
                userRequest.userRole(),
                userRequest.active()
        );
    }
}
