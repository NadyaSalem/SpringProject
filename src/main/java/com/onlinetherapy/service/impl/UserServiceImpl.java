package com.onlinetherapy.service.impl;

import com.onlinetherapy.model.dtos.UserDTO;
import com.onlinetherapy.model.dtos.UserRegistrationDTO;
import com.onlinetherapy.model.entity.UserEntity;
import com.onlinetherapy.model.entity.UserRoleEntity;
import com.onlinetherapy.model.enums.UserRoleEnum;
import com.onlinetherapy.repository.UserRepository;
import com.onlinetherapy.repository.UserRoleRepository;
import com.onlinetherapy.service.UserService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;
    private final UserRoleRepository userRoleRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           PasswordEncoder passwordEncoder,
                           ModelMapper modelMapper,
                           UserRoleRepository userRoleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
        this.userRoleRepository = userRoleRepository;
    }

    @Override
    public boolean register(@Valid UserRegistrationDTO userRegistrationDTO) {

        if (userRegistrationDTO == null) {
            return false;
        }

        if (!userRegistrationDTO.getPassword().equals(userRegistrationDTO.getConfirmPassword())) {
            return false;
        }

        String username = userRegistrationDTO.getUsername();
        if (this.userRepository.findByUsername(username).isPresent()) {
            return false;
        }

        UserRoleEntity userRole = this.userRoleRepository
                .findUserRoleEntityByRole(UserRoleEnum.USER).orElseThrow();

        UserEntity user = new UserEntity();
        user.setUsername(userRegistrationDTO.getUsername());
        user.setEmail(userRegistrationDTO.getEmail());
        user.setPassword(passwordEncoder.encode(userRegistrationDTO.getPassword()));
        user.setRoles(List.of(userRole));

        this.userRepository.save(user);
        //TODO: send mail for NEW registration:
//        NewUserEmailSender.newUserRegisteredEmail();
        return true;
    }


    @Override
    public UserDTO findByUsername(String username) {
        Optional<UserEntity> byUsername = this.userRepository.findByUsername(username);

        if (byUsername.isPresent()) {

            UserEntity currentUser = byUsername.get();
            this.userRepository.save(currentUser);

         return this.modelMapper.map(currentUser, UserDTO.class);
        }
        return null;
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<UserEntity> all = this.userRepository.findAll();

        return all
                .stream()
                .map(u -> this.modelMapper.map(u, UserDTO.class))
                .toList();
    }


    @Override
    public UserDTO findById(Long id) {
        Optional<UserEntity> byId = this.userRepository.findById(id);
        if (byId.isPresent()) {
            return this.modelMapper.map(byId, UserDTO.class);
        }
        return null;
    }
//
//    @Override
//    public boolean isLoggedIn() {
//        return false;
//    }
//

    @Override
    public void setAdmin(Long userId) {

    }

    @Override
    public void getAdmin(Long userId) {

    }


}
