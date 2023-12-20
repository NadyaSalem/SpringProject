package com.onlinetherapy.service;

import com.onlinetherapy.models.entity.ChoseAppointments;
import com.onlinetherapy.repository.ChoseAppointmentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChoseAppointmentsService {
    private final ChoseAppointmentsRepository choseAppointmentsRepository;

    @Autowired
    public ChoseAppointmentsService(ChoseAppointmentsRepository choseAppointmentsRepository) {
        this.choseAppointmentsRepository = choseAppointmentsRepository;
    }

    public void save(ChoseAppointments choseAppointment) {
        this.choseAppointmentsRepository.save (choseAppointment);
    }

    public void deleteById(Long appointmentId) {
        this.choseAppointmentsRepository.deleteById (appointmentId);
    }

    public void deleteAll() {
        this.choseAppointmentsRepository.deleteAll ();
    }

    public ChoseAppointments getByImg(String img){
        return this.choseAppointmentsRepository.findByImg(img);
    }
}
