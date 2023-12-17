package com.onlinetherapy.service.impl;

import com.onlinetherapy.model.dtos.ListUserRoleDTO;
import com.onlinetherapy.model.dtos.UserRoleDTO;
import com.onlinetherapy.model.entity.UserEntity;
import com.onlinetherapy.model.entity.UserRoleEntity;
import com.onlinetherapy.model.enums.UserRoleEnum;
import com.onlinetherapy.repository.UserRepository;
import com.onlinetherapy.repository.UserRoleRepository;
import com.onlinetherapy.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserRoleServiceImpl implements UserRoleService {
    private final UserRoleRepository userRoleRepository;
    private final UserRepository userRepository;

    @Autowired
    public UserRoleServiceImpl(UserRoleRepository userRoleRepository, UserRepository userRepository) {
        this.userRoleRepository = userRoleRepository;
        this.userRepository = userRepository;
    }


    @Override
    public ListUserRoleDTO findAllRoles() {
        List<UserRoleEntity> all = this.userRoleRepository.findAll();
        ListUserRoleDTO rolesDTO = new ListUserRoleDTO();
        for (UserRoleEntity role : all) {
            UserRoleDTO userRoleDTO = new UserRoleDTO().setRole(role.getRole());
            rolesDTO.addRole(userRoleDTO);
        }

        return rolesDTO;
    }

    @Override
    public void makeAdmin(Long id) {
        Optional<UserEntity> optUser = this.userRepository.findById(id);
        if (optUser.isPresent()) {
            Optional<UserRoleEntity> optRole = this.userRoleRepository.findUserRoleEntityByRole(UserRoleEnum.ADMIN);

            if (optRole.isPresent()) {
                List<UserRoleEntity> currRoles = optUser.get().getRoles();
                boolean hasRole = false;
                for (UserRoleEntity r : currRoles) {
                    if (r.getRole() == UserRoleEnum.ADMIN) {
                        hasRole = true;
                        break;
                    }
                }
                if (!hasRole) {
                    optUser.get().getRoles().add(optRole.get());
                    this.userRepository.save(optUser.get());
                }
            }
        }
    }

    @Override
    public void makeModerator(Long id) {
        Optional<UserEntity> optUser = this.userRepository.findById(id);
        if (optUser.isPresent()) {
            Optional<UserRoleEntity> optRole = this.userRoleRepository.findUserRoleEntityByRole(UserRoleEnum.MODERATOR);

            if (optRole.isPresent()) {
                List<UserRoleEntity> currRoles = optUser.get().getRoles();
                boolean hasRole = false;
                for (UserRoleEntity r : currRoles) {
                    if (r.getRole() == UserRoleEnum.MODERATOR) {
                        hasRole = true;
                        break;
                    }
                }
                if (!hasRole) {
                    optUser.get().getRoles().add(optRole.get());
                    this.userRepository.save(optUser.get());
                }
            }
        }
    }

    @Override
    public void deleteRoles(Long id) {
        Optional<UserEntity> optUser = this.userRepository.findById(id);
        if (optUser.isPresent()) {
            UserRoleEntity userRole = this.userRoleRepository
                    .findUserRoleEntityByRole(UserRoleEnum.USER).orElseThrow();

            UserEntity userToSave = optUser.get();
            List<UserRoleEntity> rolesList = new ArrayList<>();
            rolesList.add(userRole);
            userToSave.setRoles(rolesList);

            this.userRepository.save(userToSave);
        }

    }

}
