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

@Controller
public class AuthController {
    private final UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @ModelAttribute(name = "userRegistrationDTO")
    public UserRegistrationDTO initUserRegistrationDTO() {
        return new UserRegistrationDTO();
    }

    @GetMapping("/users/register")
    public String getRegister() {
        return "register";
    }

    @PostMapping("/users/register")
    public String register(@Valid @ModelAttribute(name = "userRegisterDTO")UserRegistrationDTO userRegistrationDTO,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors() || !this.userService.register(userRegistrationDTO)) {
            redirectAttributes.addFlashAttribute("userRegistrationDTO", userRegistrationDTO)
                    .addFlashAttribute("org.springframework.validation.BindingResult.userRegistrationDTO", bindingResult);
            return "redirect:/users/register";
        }
        return "login";
    }

    @GetMapping("/users/login")
    public String getLogin() {
        return "login";
    }


//
//    @PostMapping("/login")
//    public String login(@Valid UserLoginDTO userLoginDTO,
//                        BindingResult bindingResult,
//                        RedirectAttributes redirectAttributes) {
//
//        if (bindingResult.hasErrors()) {
//            redirectAttributes.addFlashAttribute("userLoginDTO", userLoginDTO);
//            redirectAttributes.addFlashAttribute(
//                    "org.springframework.validation.BindingResult.userLoginDTO", bindingResult);
//
//            return "redirect:/login";
//        }
//
//        if (!this.userService.isLogin(userLoginDTO)) {
//            redirectAttributes.addFlashAttribute("userLoginDTO", userLoginDTO);
//            redirectAttributes.addFlashAttribute("badCredentials", true);
//
//            return "redirect:/login";
//        }
//
//        return "redirect:/home";
//    }


    @PostMapping("/users/login-error")
    public String loginError(@ModelAttribute("username") String username, Model model) {
        model.addAttribute("username", username);
        model.addAttribute("bad_credentials", true);
        return "login";
    }

    @PostMapping("/users/logout")
    public String logout() {
        return "index";
    }
}
