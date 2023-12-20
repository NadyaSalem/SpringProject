package com.onlinetherapy.repository;

import com.onlinetherapy.models.entity.PurchasedAppointments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseAppointmentsRepository extends JpaRepository<PurchasedAppointments, Long> {

}
