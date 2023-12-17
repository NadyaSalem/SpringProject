package com.onlinetherapy.repository;

import com.onlinetherapy.model.entity.Therapist;
import com.onlinetherapy.model.enums.TherapistType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TherapistRepository extends JpaRepository<Therapist, Long> {

    Therapist findByTherapistType(TherapistType therapistType);
}
