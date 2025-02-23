package com.login.service;

import org.springframework.stereotype.Service;

import com.login.dto.UserDTO;
import com.login.dto.UserRequest;
import com.login.dto.UserResponse;
import com.login.model.Role;
import com.login.model.User;
import com.login.reposirory.UserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    
    private final UserRepository userRepository; 

    @Transactional
    public UserResponse updateUser(UserRequest userRequest) {
       
        User user = User.builder()
        .id(userRequest.getId())
        .firstname(userRequest.getFirstname())
        .lastname(userRequest.getLastname())
        .role(Role.USER)
        .build();
        
        userRepository.updateUser(user.getId(), user.getFirstname(), user.getLastname());

        return new UserResponse("El usuario se registró satisfactoriamente");
    }

    public UserDTO getUser(Integer id) {
        User user= userRepository.findById(id).orElse(null);
       
        if (user!=null)
        {
            UserDTO userDTO = UserDTO.builder()
       
            .id(user.getId())   	
            .username(user.getUsername())
            .firstname(user.getFirstname())
            .lastname(user.getLastname())
            .build();
            return userDTO;
        }
        return null;
    }
}