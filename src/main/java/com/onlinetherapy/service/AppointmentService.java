package com.onlinetherapy.service;

import com.onlinetherapy.model.dtos.CreateAppointmentDTO;
import jakarta.validation.Valid;

public interface AppointmentService {

    public void create(@Valid CreateAppointmentDTO createAppointmentDTO);


//    public void deleteAllWords(@Valid CreateAppointmentDTO createWordDTO) {
//        TherapistType languageName = TherapistType.valueOf(createWordDTO.getLanguage());
//        Therapist language = this.languageRepository.findByName(languageName);


}
