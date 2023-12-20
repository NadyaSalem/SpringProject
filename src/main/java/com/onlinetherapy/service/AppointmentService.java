package com.onlinetherapy.service;

import com.onlinetherapy.models.dto.bindingModels.appointments.CreateAppointmentDTO;
import com.onlinetherapy.models.dto.viewModels.appointments.AppointmentImgView;
import com.onlinetherapy.models.dto.viewModels.appointments.AppointmentToAdminPanelView;
import com.onlinetherapy.models.dto.viewModels.appointments.AppointmentWithInfoView;
import com.onlinetherapy.models.dto.viewModels.appointments.AppointmentsViewOnHomePage;
import com.onlinetherapy.models.entity.Appointment;
import com.onlinetherapy.repository.AppointmentRepository;
import com.onlinetherapy.repository.TherapistRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentService {
    private final ModelMapper modelMapper;
    private final TherapistRepository therapistRepository;
    private final AppointmentRepository appointmentRepository;
    private final ImageCloudService imageCloudService;

    @Autowired
    public AppointmentService(ModelMapper modelMapper, TherapistRepository therapistRepository,
                              AppointmentRepository appointmentRepository, ImageCloudService imageCloudService) {
        this.modelMapper = modelMapper;
        this.therapistRepository = therapistRepository;
        this.appointmentRepository = appointmentRepository;
        this.imageCloudService = imageCloudService;
    }

    public boolean addAppointment(CreateAppointmentDTO createAppointmentDTO) {
        String pictureUrl = imageCloudService.saveImage(createAppointmentDTO.getImg());
        Appointment appointment = modelMapper.map(createAppointmentDTO, Appointment.class);

        appointment.setTherapist(therapistRepository.findById (Long.parseLong (createAppointmentDTO.getTherapist()))
                .orElseThrow(() -> new Error("Therapist not found!")));
        appointment.setImg(pictureUrl);
        this.appointmentRepository.save(appointment);
        return true;
    }


    public List<AppointmentsViewOnHomePage> getAllAppointmentsToViewOnHomePage() {
        return this.appointmentRepository.findAll()
                .stream()
                .map(a -> {
                    return modelMapper.map(a, AppointmentsViewOnHomePage.class);
                }).toList();
    }

    public AppointmentWithInfoView getAppointmentInfoById(Long id) {
        Appointment appointment = this.appointmentRepository.findById(id)
                .orElseThrow(() -> new Error("Appointment not found!"));
        return this.modelMapper.map(appointment, AppointmentWithInfoView.class);
    }

    public List<AppointmentImgView> getAllAppointmentImage() {
        return this.appointmentRepository.findAll()
                .stream()
                .map(a -> {
                    return modelMapper.map(a, AppointmentImgView.class);
                }).toList();
    }

    public Appointment getAppointmentById(Long id) {
        return this.appointmentRepository.findById(id)
                .orElseThrow(() -> new Error("Appointment not found!"));
    }

    public List<AppointmentToAdminPanelView> getAllAppointments() {
        return this.appointmentRepository.findAll()
                .stream()
                .map(a -> {
                    return modelMapper.map(a, AppointmentToAdminPanelView.class);
                }).toList();

    }

    public void deleteAppointmentById(Long appointmentId) {
        this.appointmentRepository.deleteById(appointmentId);
    }

    public void editAppointment(Long appointmentId, AppointmentWithInfoView editAppointmentDTO) {
        Appointment appointmentToEdit = this.appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new Error("Appointment not found!"));
        appointmentToEdit.setDescription(editAppointmentDTO.getDescription());
        appointmentToEdit.setPrice(editAppointmentDTO.getPrice());
        appointmentToEdit.setImg(editAppointmentDTO.getImg());
        this.appointmentRepository.save(appointmentToEdit);
    }
}
