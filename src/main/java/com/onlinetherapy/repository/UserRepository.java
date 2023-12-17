package com.onlinetherapy.repository;

import com.onlinetherapy.model.entity.UserEntity;
import com.onlinetherapy.model.enums.UserRoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long>{

    Optional<UserEntity> findByUsername(String username);

    Optional<UserEntity> findUserEntityByUsername(String username);

    Optional<UserEntity> findByBookedAppointment(UserEntity bookedBy);


    Optional<UserEntity> findByRoles(UserRoleEnum userRoleEnum);
}
