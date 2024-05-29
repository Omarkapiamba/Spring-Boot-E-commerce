package com.example.springbootecommerce.dto.dtoMapper;

import com.example.springbootecommerce.dto.UserDTO;
import com.example.springbootecommerce.model.User;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class UserDTOMapper implements Function<User, UserDTO> {

    @Override
    public UserDTO apply(User user) {
        return new UserDTO(
                user.getId(),
                user.getName(),
                user.getUsername(),
                user.getLastName(),
                user.getPersonalNumber(),
                user.getPhoneNumber(),
                user.getAdress(),
                user.getEmail(),
                user.getRegistrationDate(),
                user.getUserRole(),
                user.getActive()
        );
    }
}
