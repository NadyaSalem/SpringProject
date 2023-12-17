package com.onlinetherapy.web;

import com.onlinetherapy.model.dtos.UserRegistrationDTO;
import com.onlinetherapy.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class UserRegistrationController {
    private final UserService userService;


    @Autowired
    public UserRegistrationController(UserService userService) {
        this.userService = userService;
    }

//    @ModelAttribute("userRegistrationDTO")
//    public UserRegistrationDTO initUserRegistrationDTO(){
//        return new UserRegistrationDTO();
//    }
//    @GetMapping("/users/register")
//    public String register(Model model) {
//
//        return "register";
//    }

//    @PostMapping("/users/register")
//    public String register(
//            @Valid UserRegistrationDTO userRegistrationDTO,
//            BindingResult bindingResult,
//            RedirectAttributes redirectAttributes) {
//
//
//        if(bindingResult.hasErrors()){
//            redirectAttributes.addFlashAttribute("userRegistrationDTO", userRegistrationDTO);
//            redirectAttributes.addFlashAttribute(
//                    "org.springframework.validation.BindingResult.userRegistrationDTO", bindingResult);
//
//            return "redirect:/users/register";
//        }
//
//        userService.registerUser(userRegistrationDTO);
//
//        return "redirect:/users/login";
//    }

}
