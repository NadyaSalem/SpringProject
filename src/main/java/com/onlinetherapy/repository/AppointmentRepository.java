package com.onlinetherapy.repository;

import com.onlinetherapy.model.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    Optional<Object> findByBookedBy(Long id);


    //List<CreateAppointmentDTO> findAllByTherapistType(TherapistType therapistType);
}
