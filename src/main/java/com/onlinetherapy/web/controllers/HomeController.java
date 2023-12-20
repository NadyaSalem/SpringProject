package com.onlinetherapy.web.controllers;

import com.onlinetherapy.models.dto.viewModels.messages.MessagesView;
import com.onlinetherapy.models.dto.viewModels.appointments.AppointmentsViewOnHomePage;
import com.onlinetherapy.service.MessageService;
import com.onlinetherapy.service.AppointmentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class HomeController {

    private final AppointmentService appointmentService;
    private final MessageService messageService;

    public HomeController(AppointmentService appointmentService, MessageService messageService) {
        this.appointmentService = appointmentService;
        this.messageService = messageService;
    }

    @ModelAttribute("homePageAppointmentDTO")
    public AppointmentsViewOnHomePage initHomePageAppointmentsDTO(){
        return new AppointmentsViewOnHomePage();
    }

    @ModelAttribute("messagesDTO")
    public MessagesView initMessagesDTO(){
        return new MessagesView ();
    }

    @GetMapping("/")
    public String loggedOutIndex (Model model){
        model.addAttribute("appointments",  this.appointmentService.getAllAppointmentsToViewOnHomePage());
        return "index";
    }

}
