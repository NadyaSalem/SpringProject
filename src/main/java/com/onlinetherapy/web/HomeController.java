package com.onlinetherapy.web;



import com.onlinetherapy.service.AppointmentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    private final AppointmentService appointmentService;

    public HomeController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }


    @GetMapping({"/"})
    public String loggedOutIndex(){
//        if(this.authService.isLoggedIn()) {
//            return "redirect:/home";
//        }
        return "index";
    }


    @GetMapping({"/home"})
    public String loggedInIndex(){
//        if(!this.authService.isLoggedIn()) {
//            return "redirect:/";
//        }


        return "home";
    }
}
