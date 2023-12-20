package com.onlinetherapy.service;

import com.onlinetherapy.models.entity.PurchasedAppointments;
import com.onlinetherapy.repository.PurchaseAppointmentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class PurchasedAppointmentsService {
    private final PurchaseAppointmentsRepository purchaseAppointmentsRepository;

    @Autowired
    public PurchasedAppointmentsService(PurchaseAppointmentsRepository purchaseAppointmentsRepository) {
        this.purchaseAppointmentsRepository = purchaseAppointmentsRepository;
    }

    public List<PurchasedAppointments> addAppointments(List<PurchasedAppointments> appointmentsToAddInDB) {
        List<PurchasedAppointments> appointmentsInDB = this.purchaseAppointmentsRepository.findAll();

        for (PurchasedAppointments appointmentToAdd : appointmentsToAddInDB) {
            appointmentToAdd.setAppointmentSum(appointmentToAdd.getPrice().multiply(BigDecimal.valueOf(appointmentToAdd.getCount())));

            List<PurchasedAppointments> sameAppointments = appointmentsInDB.stream().filter(a -> a.getImg().equals(appointmentToAdd.getImg())).toList();

            if (!sameAppointments.isEmpty()) {
                if (sameAppointments.stream().anyMatch(a -> a.getCount() == appointmentToAdd.getCount())) {
                    appointmentToAdd.setId(sameAppointments.stream()
                            .filter(a -> a.getCount() == appointmentToAdd.getCount())
                            .findFirst()
                            .orElseThrow(() -> new Error("Appointment not found!"))
                            .getId());
                } else {
                    this.purchaseAppointmentsRepository.save(appointmentToAdd);
                }
            } else {
                this.purchaseAppointmentsRepository.save(appointmentToAdd);
            }
        }
        return appointmentsToAddInDB;
    }
}



