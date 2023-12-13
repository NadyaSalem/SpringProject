package com.onlinetherapy.controller;


import com.onlinetherapy.service.AuthService;
import com.onlinetherapy.service.WordService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final AuthService authService;
    private final WordService wordService;

    public HomeController(AuthService authService, WordService wordService) {
        this.authService = authService;
        this.wordService = wordService;
    }


    @GetMapping({"/"})
    public String loggedOutIndex(){
        if(this.authService.isLoggedIn()) {
            return "redirect:/home";
        }
        return "index";
    }


    @GetMapping({"/home"})
    public String loggedInIndex(){
        if(!this.authService.isLoggedIn()) {
            return "redirect:/";
        }


        return "home";
    }
}
