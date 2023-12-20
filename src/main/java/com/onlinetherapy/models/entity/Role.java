package com.onlinetherapy.models.entity;

import jakarta.persistence.*;
import com.onlinetherapy.models.enums.UserRoleEnum;


@Entity
@Table(name = "roles")
public class Role extends BaseEntity {

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRoleEnum userRole;

    public UserRoleEnum getUserRole() {
        return userRole;
    }

    public Role setUserRole(UserRoleEnum role) {
        this.userRole = role;
        return this;
    }
}
