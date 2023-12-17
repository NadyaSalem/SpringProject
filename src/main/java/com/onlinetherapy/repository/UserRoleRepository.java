package com.onlinetherapy.repository;

import com.onlinetherapy.model.entity.UserRoleEntity;
import com.onlinetherapy.model.enums.UserRoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRoleEntity, Long> {
    Optional<UserRoleEntity> findUserRoleEntityByRole(UserRoleEnum role);

}
