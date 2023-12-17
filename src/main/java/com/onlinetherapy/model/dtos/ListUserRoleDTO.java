package com.onlinetherapy.model.dtos;

import java.util.ArrayList;
import java.util.List;

public class ListUserRoleDTO {
    private List<UserRoleDTO> roles = new ArrayList<>();

    public List<UserRoleDTO> getRoles() {
        return roles;
    }

    public ListUserRoleDTO setRoles(List<UserRoleDTO> roles) {
        this.roles = roles;
        return this;
    }

    public void addRole(UserRoleDTO userRoleDTO) {
        this.roles.add(userRoleDTO);
    }
}
