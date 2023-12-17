package com.onlinetherapy.service.impl;

import com.onlinetherapy.model.dtos.CreateAppointmentDTO;
import com.onlinetherapy.model.entity.Appointment;
import com.onlinetherapy.model.entity.Therapist;
import com.onlinetherapy.model.entity.UserEntity;
import com.onlinetherapy.model.enums.TherapistType;
import com.onlinetherapy.repository.AppointmentRepository;
import com.onlinetherapy.repository.TherapistRepository;
import com.onlinetherapy.repository.UserRepository;
import com.onlinetherapy.service.AppointmentService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.beans.Transient;

@Service
public class AppointmentServiceImpl implements AppointmentService {
    private final UserRepository userRepository;
    private final TherapistRepository therapistRepository;
    private final AppointmentRepository appointmentRepository;

    public AppointmentServiceImpl(UserRepository userRepository, TherapistRepository therapistRepository,
                                  AppointmentRepository appointmentRepository) {
        this.userRepository = userRepository;
        this.therapistRepository = therapistRepository;
        this.appointmentRepository = appointmentRepository;
    }

    public boolean isOwner(String userName, Long id) {

        return appointmentRepository.
                findById(id).
                filter(o -> o.getBookedBy().getEmail().equals(userName)).
                isPresent();
    }

    @Transient
    public void create(@Valid CreateAppointmentDTO createAppointmentDTO) {

       // Optional<UserEntity> owner = this.userRepository.findByBookedAppointment(createAppointmentDTO.getBookedBy());
//        UserRoleEnum userRoleEnum = UserRoleEnum.valueOf(String.valueOf(UserRoleEnum.ADMIN));
//        Optional<UserEntity> owner = this.userRepository.findByRoles(userRoleEnum);
//
//        if (owner.isPresent()) {
            TherapistType therapistType = TherapistType.valueOf(String.valueOf(createAppointmentDTO.getTherapist()));
            Therapist therapist = this.therapistRepository.findByTherapistType(therapistType);
// Should Be Logged User!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
            UserEntity owner = new UserEntity();
            owner.setUsername("lili");
            owner.setEmail("lili@test.com");
            owner.setPassword("12345");


            Appointment appointment = new Appointment();
            appointment.setBookedBy(owner);
            appointment.setInputDate(createAppointmentDTO.getInputDate());
            appointment.setTherapist(therapist);

            this.appointmentRepository.save(appointment);
            this.userRepository.save(owner);
            this.therapistRepository.save(appointment.getTherapist());
        }

    }


//    public void deleteAllWords(@Valid CreateAppointmentDTO createWordDTO) {
//        TherapistType languageName = TherapistType.valueOf(createWordDTO.getLanguage());
//        Therapist language = this.languageRepository.findByName(languageName);


