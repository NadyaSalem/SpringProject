package com.onlinetherapy.service;


import com.onlinetherapy.model.dtos.ListUserRoleDTO;

public interface UserRoleService {
    ListUserRoleDTO findAllRoles();
    void makeAdmin(Long id);
    void makeModerator(Long id);
    void deleteRoles(Long id);
}
