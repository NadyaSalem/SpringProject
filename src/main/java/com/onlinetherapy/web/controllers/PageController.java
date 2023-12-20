package com.onlinetherapy.web.controllers;

import com.onlinetherapy.service.AppointmentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    private final AppointmentService appointmentService;

    public PageController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }

    @GetMapping("/gallery")
    public String gallery(Model model) {
        model.addAttribute ("img", this.appointmentService.getAllAppointmentImage());
        return "gallery";
    }

}
