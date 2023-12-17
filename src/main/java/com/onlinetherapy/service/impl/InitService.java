package com.onlinetherapy.service.impl;


import com.onlinetherapy.model.entity.UserEntity;
import com.onlinetherapy.model.entity.UserRoleEntity;
import com.onlinetherapy.model.enums.UserRoleEnum;
import com.onlinetherapy.repository.UserRepository;
import com.onlinetherapy.repository.UserRoleRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InitService {


    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public InitService(UserRepository userRepository,
                       UserRoleRepository userRoleRepository,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void init() {
        initRoles();
        initUsers();
    }


    private void initRoles() {
        if (this.userRoleRepository.count() == 0) {
            UserRoleEntity adminRole = new UserRoleEntity().setRole(UserRoleEnum.ADMIN);
            UserRoleEntity moderatorRole = new UserRoleEntity().setRole(UserRoleEnum.MODERATOR);
            UserRoleEntity userRole = new UserRoleEntity().setRole(UserRoleEnum.USER);

            this.userRoleRepository.save(adminRole);
            this.userRoleRepository.save(moderatorRole);
            this.userRoleRepository.save(userRole);
        }
    }

    private void initUsers() {
        if (this.userRepository.count() == 0) {
            initAdmin();
            initModerator();
            initCommonUser();
        }
    }

    private void initAdmin() {
        List<UserRoleEntity> all = this.userRoleRepository.findAll();

        UserEntity adminUser = new UserEntity();
        adminUser.setUsername("admin");
        adminUser.setEmail("admin@test.com");
        adminUser.setPassword(passwordEncoder.encode("testpass"));
        adminUser.setRoles(all);

        this.userRepository.save(adminUser);
    }

    private void initModerator() {
        UserRoleEntity moderatorRole = this.userRoleRepository
                .findUserRoleEntityByRole(UserRoleEnum.MODERATOR).orElseThrow();
        UserRoleEntity userRole = this.userRoleRepository
                .findUserRoleEntityByRole(UserRoleEnum.USER).orElseThrow();

        UserEntity moderatorUser = new UserEntity();
        moderatorUser.setUsername("moderator");
        moderatorUser.setEmail("moderator@test.com");
        moderatorUser.setPassword(passwordEncoder.encode("testpass"));
        moderatorUser.setRoles(List.of(moderatorRole, userRole));


        this.userRepository.save(moderatorUser);
    }

    private void initCommonUser() {
        UserRoleEntity userRole = this.userRoleRepository
                .findUserRoleEntityByRole(UserRoleEnum.USER).orElseThrow();

        UserEntity commonUser = new UserEntity();
        commonUser.setUsername("user");
        commonUser.setEmail("user@test.com");
        commonUser.setPassword(passwordEncoder.encode("testpass"));
        commonUser.setRoles(List.of(userRole));

        this.userRepository.save(commonUser);
    }
}
