package com.onlinetherapy.models.dto.viewModels.users;

import com.onlinetherapy.models.entity.Role;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AllUsersView {
    private Long id;
    private String username;
    private String email;
    private List<Role> roles;
}
