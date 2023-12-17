package com.onlinetherapy.web;

import com.onlinetherapy.model.dtos.UserDTO;
import com.onlinetherapy.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class UserController {
  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @GetMapping("/users/all")
  public String allUsers(Model model,
                     @AuthenticationPrincipal UserDetails user) {

    List<UserDTO> allUsers = userService.getAllUsers();

    model.addAttribute("allUsers", allUsers);

    return "all_users";
  }

  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @PostMapping("/user/{userId}/set")
  public String setAdmin(@PathVariable("userId") Long userId,
                         @AuthenticationPrincipal UserDetails userDetails){

    userService.setAdmin(userId);

    return "redirect:/users/all";
  }

  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @PostMapping("/user/{userId}/get")
  public String getAdmin(@PathVariable("userId") Long userId,
                         @AuthenticationPrincipal UserDetails userDetails){

    userService.getAdmin(userId);

    return "redirect:/users/all";
  }
}
