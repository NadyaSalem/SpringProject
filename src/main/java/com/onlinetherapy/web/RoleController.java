package com.onlinetherapy.web;

import com.onlinetherapy.model.dtos.ListUserRoleDTO;
import com.onlinetherapy.model.dtos.UserDTO;
import com.onlinetherapy.service.UserRoleService;
import com.onlinetherapy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class RoleController {
    private final UserRoleService userRoleService;
    private final UserService userService;

    @Autowired
    public RoleController(UserRoleService userRoleService, UserService userService) {
        this.userRoleService = userRoleService;
        this.userService = userService;
    }

    @GetMapping("/roles-show")
    public String getChangeRoles(Model model) {

        ListUserRoleDTO allRoles = this.userRoleService.findAllRoles();
        List<UserDTO> allUsers = this.userService.getAllUsers();

        model.addAttribute("allRoles", allRoles);
        model.addAttribute("allUsers", allUsers);
        return "roles-show";
    }

    @GetMapping("/users/roles-edit{id}")
    public String chooseChange(@PathVariable Long id, Model model) {
        UserDTO userDTO = this.userService.findById(id);

        model.addAttribute("userDTO", userDTO);

        return "roles-edit";
    }

    @GetMapping("/users/roles-edit/admin{id}")
    public String makeAdmin(@PathVariable Long id) {
        this.userRoleService.makeAdmin(id);
        return "roles-changed";
    }

    @GetMapping("/users/roles-edit/moderator{id}")
    public String makeModerator(@PathVariable Long id) {
        this.userRoleService.makeModerator(id);
        return "roles-changed";
    }

    @GetMapping("/users/roles-edit/delete-roles{id}")
    public String deleteRoles(@PathVariable Long id) {
        this.userRoleService.deleteRoles(id);
        return "roles-changed";
    }


}
