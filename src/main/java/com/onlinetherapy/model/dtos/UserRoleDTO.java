package com.onlinetherapy.model.dtos;

import com.onlinetherapy.model.enums.UserRoleEnum;

public class UserRoleDTO {
    private UserRoleEnum role;

    public UserRoleEnum getRole() {
        return role;
    }

    public UserRoleDTO setRole(UserRoleEnum role) {
        this.role = role;
        return this;
    }
}
