package com.onlinetherapy.repository;

import com.onlinetherapy.models.entity.ChoseAppointments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChoseAppointmentsRepository extends JpaRepository<ChoseAppointments, Long> {

    ChoseAppointments findByImg(String img);

}
