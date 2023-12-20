package com.onlinetherapy.web.controllers;

import com.onlinetherapy.service.MessageService;
import com.onlinetherapy.service.RequestService;
import com.onlinetherapy.service.AppointmentService;
import com.onlinetherapy.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.security.Principal;

@Controller
public class UserController {
    private final UserService userService;
    private final AppointmentService appointmentService;
    private final RequestService requestService;
    private final MessageService messageService;

    public UserController(UserService userService, AppointmentService appointmentService, RequestService requestService,
                          MessageService messageService) {
        this.userService = userService;
        this.appointmentService = appointmentService;
        this.requestService = requestService;
        this.messageService = messageService;
    }

    @GetMapping("/purchase/{id}")
    public String buyAppointment(@PathVariable("id") Long appointmentId, Principal username) {
        this.userService.addAppointmentToChoseList(appointmentId, username);
        return "redirect:/appointment";
    }

    @GetMapping("/user/admin")
    public String adminPanel(Model model) {
        model.addAttribute("allUsers", this.userService.getAllUsers());
        model.addAttribute("allAppointments", this.appointmentService.getAllAppointments());
        model.addAttribute("allOpenRequests", this.requestService.GetAllOpenRequests());
        model.addAttribute("allCloseRequests", this.requestService.GetAllCloseRequests());
        model.addAttribute("allMessages", this.messageService.getAllMessages());
        return "admin";
    }

    @GetMapping("/user/change-role/{id}")
    public String changeRole(@PathVariable("id") Long userId) {
        this.userService.changeUserRole(userId);
        return "redirect:/user/admin";
    }

}
