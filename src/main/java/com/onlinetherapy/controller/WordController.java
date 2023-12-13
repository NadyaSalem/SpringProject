package com.onlinetherapy.controller;


import com.onlinetherapy.model.dtos.CreateWordDTO;
import com.onlinetherapy.service.AuthService;
import com.onlinetherapy.service.WordService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class WordController {

    private final AuthService authService;
    private final WordService wordService;

    public WordController(AuthService authService, WordService wordService) {
        this.authService = authService;
        this.wordService = wordService;
    }


    @ModelAttribute("createWordDTO")
    public CreateWordDTO initCreateOfferDTO() {
        return new CreateWordDTO();
    }

    @GetMapping("/words/add")
    public String words() {
        if (!this.authService.isLoggedIn()) {
            return "redirect:/";
        }

        return "word-add";
    }

    @PostMapping("/words/add")
    public String words(@Valid CreateWordDTO createWordDTO,
                        BindingResult bindingResult,
                        RedirectAttributes redirectAttributes) {

        if (!this.authService.isLoggedIn()) {
            return "redirect:/";
        }

        if (bindingResult.hasErrors() || !this.wordService.create(createWordDTO)) {
            redirectAttributes.addFlashAttribute("createWordDTO", createWordDTO);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.createWordDTO", bindingResult);

            return "redirect:/words/add";
        }

        return "redirect:/home";
    }
}

