package com.onlinetherapy.model.dtos;

import com.onlinetherapy.model.entity.UserEntity;
import com.onlinetherapy.model.enums.UserRoleEnum;

public class UserWithRoleDTO {
    private Long id;
    private String username;
    private boolean isAdmin;

    public UserWithRoleDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public UserWithRoleDTO(UserEntity user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.isAdmin = user.getRoles().
                stream().
                anyMatch(r -> r.getRole().equals(UserRoleEnum.ADMIN));
    }
}