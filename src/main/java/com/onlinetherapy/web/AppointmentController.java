package com.onlinetherapy.web;


import com.onlinetherapy.model.dtos.CreateAppointmentDTO;
import com.onlinetherapy.service.AppointmentService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AppointmentController {

    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }


    @ModelAttribute("createAppointmentDTO")
    public CreateAppointmentDTO initCreateAppointmentDTO() {
        return new CreateAppointmentDTO();
    }

    @GetMapping("/appointments/add")
    public String appointments() {
//        if (!this.authService.isLoggedIn()) {
//            return "redirect:/";
//        }

        return "appointment-add";
    }

    @PostMapping("/appointments/add")
    public String appointments(@Valid CreateAppointmentDTO createAppointmentDTO,
                        BindingResult bindingResult,
                        RedirectAttributes redirectAttributes) {
//
//        if (!this.authService.isLoggedIn()) {
//            return "redirect:/";
//        }

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("createAppointmentDTO", createAppointmentDTO);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.createAppointmentDTO", bindingResult);

            return "redirect:/appointments/add";
        }

        this.appointmentService.create(createAppointmentDTO);
        return "redirect:/home";
    }
}

