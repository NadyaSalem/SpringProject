package com.onlinetherapy.service;

import com.onlinetherapy.model.dtos.UserDTO;
import com.onlinetherapy.model.dtos.UserRegistrationDTO;

import java.util.List;
public interface UserService {
    public boolean register(UserRegistrationDTO userRegistrationDTO);

    List<UserDTO> getAllUsers();

    void setAdmin(Long userId);

    void getAdmin(Long userId);
    public UserDTO findByUsername(String username);

    UserDTO findById(Long id);

}
