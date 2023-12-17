package com.onlinetherapy.model.dtos;

import jakarta.validation.constraints.Size;

public class UserLoginDTO {

    @Size(min = 3, max = 20, message = "Username should be between 3 and 20 characters.")
    private String username;

    @Size(min = 3, max = 20, message = "Password length must be between 3 and 20 characters!")
    private String password;

    public String getUsername() {
        return username;
    }

    public UserLoginDTO setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserLoginDTO setPassword(String password) {
        this.password = password;
        return this;
    }


}
