package com.onlinetherapy.web.controllers;

import com.onlinetherapy.models.dto.bindingModels.requests.MakeRequestDTO;
import com.onlinetherapy.models.dto.bindingModels.messages.MessageDTO;
import com.onlinetherapy.service.RequestService;
import com.onlinetherapy.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
public class RequestController {
    private final UserService userService;
    private final RequestService requestService;

    public RequestController(UserService userService, RequestService requestService) {
        this.userService = userService;
        this.requestService = requestService;
    }

    @ModelAttribute("makeRequestDTO")
    public MakeRequestDTO initMakeRequestDTO() {
        return new MakeRequestDTO();
    }

    @ModelAttribute("messageDTO")
    public MessageDTO initAddAppointmentDTO() {
        return new MessageDTO ();
    }

    @GetMapping("/cart/remove-appointment-from-list/{id}")
    String removeAppointmentFromChoseList(@PathVariable("id") Long appointmentId, Principal username) {
        this.userService.removeAppointmentFromChoseList(appointmentId, username);
        return "redirect:/cart";
    }

    @GetMapping("/cart")
    public String cart(Principal username, Model model) {
        model.addAttribute ("cartCashAppointment", this.userService.getChoseListByUserToViewInShoppingCard (username));
        model.addAttribute ("count", this.userService.countOfItemInCart (username));
        model.addAttribute ("sumForAllAppointments", this.userService.sumForAllPurchaseAppointment(username));
        return "cart";
    }

    @PatchMapping("/cart")
    public String cart(Principal username,
                       @Valid MakeRequestDTO makeRequestDTO,
                       BindingResult bindingResult,
                       RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors ()) {
            redirectAttributes.addFlashAttribute ("makeRequestDTO", makeRequestDTO);
            redirectAttributes.addFlashAttribute (
                    "org.springframework.validation.BindingResult.makeRequestDTO", bindingResult);

            return "redirect:/cart";
        }
        Long requestId = this.userService.requestAppointments(makeRequestDTO, username);
        return "redirect:/request/details/" + requestId;
    }

    @GetMapping("/request/details/{id}")
    public String placeRequest(@PathVariable("id") Long requestId,
                               Principal principal, Model model) {
        model.addAttribute ("requestDetails", this.userService.getRequestDetailsById(principal, requestId));
        return "request-details";
    }

    @GetMapping("/request/close/{id}")
    public String closeRequest(@PathVariable("id") Long requestId) {
        this.requestService.closeRequest(requestId);
        return "redirect:/user/admin";
    }

    @GetMapping("/requests")
    public String requestByClientId(Principal principal, Model model) {
        model.addAttribute ("clientRequests", this.userService.getAllRequests(principal));
        model.addAttribute ("completedRequests", this.userService.getCompletedRequestsWithoutMessage(principal));
        return "requests";
    }

    @PostMapping("/requests")
    public String addMessage(Principal principal, @Valid MessageDTO messageDTO,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors ()) {
            redirectAttributes.addFlashAttribute ("messageDTO", messageDTO);
            redirectAttributes.addFlashAttribute (
                    "org.springframework.validation.BindingResult.messageDTO", bindingResult);

            return "redirect:/requests";
        }
        this.userService.addMessage (messageDTO, principal);
        return "redirect:/requests";
    }
}
