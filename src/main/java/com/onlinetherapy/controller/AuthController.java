package com.onlinetherapy.controller;

import com.onlinetherapy.model.dtos.LoginDTO;
import com.onlinetherapy.model.dtos.UserRegistrationDTO;
import com.onlinetherapy.service.AuthService;
import com.onlinetherapy.util.CurrentUser;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AuthController {
    private final AuthService authService;
    private final CurrentUser currentUser;

    @Autowired
    public AuthController(AuthService authService, CurrentUser currentUser) {
        this.authService = authService;
        this.currentUser = currentUser;
    }

    @ModelAttribute("registrationDTO")
    public UserRegistrationDTO initRegistrationDTO() {
        return new UserRegistrationDTO();
    }


    @GetMapping("/register")
    public String register() {
        if (this.authService.isLoggedIn()) {
            return "redirect:/home";
        }
        return "register";
    }

    @PostMapping("/register")
    public String register(@Valid UserRegistrationDTO registrationDTO,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors() || !this.authService.register(registrationDTO)) {
            redirectAttributes.addFlashAttribute("registrationDTO", registrationDTO);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.registrationDTO", bindingResult);

            return "redirect:/register";
        }

        return "redirect:/login";
    }

    @ModelAttribute("loginDTO")
    public LoginDTO initLoginDTO() {
        return new LoginDTO();
    }

    @GetMapping("/login")
    public String login(Model model) {
        if (currentUser.isLogged()) {
            return "redirect:/home";
        }

        if (!model.containsAttribute("isFound")) {
            model.addAttribute("isFound", true);
        }
        return "login";
    }

    @PostMapping("/login")
    public String login(@Valid LoginDTO loginDTO,
                        BindingResult bindingResult,
                        RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("loginDTO", loginDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.loginDTO", bindingResult);
            return "redirect:/users/login";
        }

        CurrentUser findUserByUsernameAndPassword = authService.findByUsernameAndPassword(loginDTO.getUsername(), loginDTO.getPassword());

        if (findUserByUsernameAndPassword == null) {
            redirectAttributes.addFlashAttribute("isFound", false);
            return "redirect:login";
        }

        authService.loginUser(findUserByUsernameAndPassword.getId(), loginDTO.getUsername());

        return "redirect:/home";
    }

//
//    @GetMapping("/login")
//    public String login() {
//        if (this.authService.isLoggedIn()) {
//            return "redirect:/home";
//        }
//        return "login";
//    }


    @GetMapping("/logout")
    public String logout() {
        if (this.authService.isLoggedIn()) {
            this.authService.logout();
        }

        return "redirect:/";
    }
}
