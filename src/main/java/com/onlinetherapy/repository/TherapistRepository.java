package com.onlinetherapy.repository;

import com.onlinetherapy.models.entity.Therapist;
import com.onlinetherapy.models.enums.TherapistTypeEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TherapistRepository extends JpaRepository<Therapist, Long> {

    Therapist findByName(TherapistTypeEnum name);
}
