package com.onlinetherapy.web.controllers;

import com.onlinetherapy.models.dto.bindingModels.appointments.CreateAppointmentDTO;
import com.onlinetherapy.models.dto.viewModels.appointments.AppointmentWithInfoView;
import com.onlinetherapy.service.AppointmentService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AppointmentController {

    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @ModelAttribute("createAppointmentDTO")
    public CreateAppointmentDTO initCreatePAppointmentDTO(){
        return new CreateAppointmentDTO();
    }

    @GetMapping("/appointments/add")
    public String appointmentsAdd(){
        return "appointment-add";
    }

    @PostMapping("/appointments/add")
    public String appointmentsAdd(@Valid CreateAppointmentDTO createAppointmentDTO,
                                  BindingResult bindingResult,
                                  RedirectAttributes redirectAttributes){

        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("CreateAppointmentDTO", createAppointmentDTO);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.CreateAppointmentDTO", bindingResult);

            return "redirect:/appointments/add";
        }
        this.appointmentService.addAppointment(createAppointmentDTO);
        return "redirect:/appointment";
    }

    @GetMapping("/appointment/edit/{id}")
    public String editAppointment(@PathVariable("id") Long appointmentId, Model model){
        model.addAttribute("appointmentToEdit", this.appointmentService.getAppointmentInfoById(appointmentId));
        return "appointment-edit";
    }

    @PatchMapping("/appointment/edit/{id}")
    public String editAppointment(@PathVariable("id") Long appointmentId,
                                  @Valid AppointmentWithInfoView editAppointmentDTO,
                                  BindingResult bindingResult,
                                  RedirectAttributes redirectAttributes){

        if(bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("editAppointmentTO", editAppointmentDTO);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.editAppointmentDTO", bindingResult);

            return "redirect:/appointment/edit/" + appointmentId;
        }

        this.appointmentService.editAppointment(appointmentId, editAppointmentDTO);
        return "redirect:/user/admin";
    }

    @GetMapping("/appointment/delete/{id}")
    public String deleteAppointment(@PathVariable("id") Long appointmentId){
        this.appointmentService.deleteAppointmentById(appointmentId);
        return "redirect:/user/admin";
    }

    @GetMapping("/appointment/info/{id}")
    public String appointmentInfo(@PathVariable("id") Long id, Model model){
        model.addAttribute ("appointmentInfo", this.appointmentService.getAppointmentInfoById(id));
        return "appointment-info";
    }

    @GetMapping("/appointment")
    public String allAppointmentsPage (Model model){
        model.addAttribute("allAppointments",  this.appointmentService.getAllAppointmentsToViewOnHomePage());
        return "appointment";
    }
}
