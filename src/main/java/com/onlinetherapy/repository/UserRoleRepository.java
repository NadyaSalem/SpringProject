package com.onlinetherapy.repository;

import com.onlinetherapy.models.entity.Role;
import com.onlinetherapy.models.enums.UserRoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRoleRepository extends JpaRepository<Role, Long> {
    List<Role> findByUserRole(UserRoleEnum client);

}
