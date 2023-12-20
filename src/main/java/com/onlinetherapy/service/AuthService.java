package com.onlinetherapy.service;


import com.onlinetherapy.models.dto.bindingModels.user.UserRegistrationDTO;
import com.onlinetherapy.models.entity.User;
import com.onlinetherapy.models.enums.UserRoleEnum;
import com.onlinetherapy.repository.UserRepository;
import com.onlinetherapy.repository.UserRoleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final UserRoleRepository roleRepository;

    @Autowired
    public AuthService(PasswordEncoder passwordEncoder,
                       UserRepository userRepository,
                       ModelMapper modelMapper,
                       UserRoleRepository roleRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.roleRepository = roleRepository;
    }

    public void registerUser(UserRegistrationDTO userRegistrationDTO) {

        User newUser = modelMapper.map (userRegistrationDTO, User.class);
        newUser.setPassword (passwordEncoder.encode (userRegistrationDTO.getPassword ()));
        newUser.setUserRoles (roleRepository.findByUserRole (UserRoleEnum.CLIENT));

        userRepository.save (newUser);
    }
}
