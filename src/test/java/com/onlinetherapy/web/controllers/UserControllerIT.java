package com.onlinetherapy.web.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(username = "client", roles = {"CLIENT"})
    void testPurchaseProduct() throws Exception {
        mockMvc.perform(get("/purchase/1"))
                .andExpect (status ()
                .is2xxSuccessful ());
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void  testAdminPanel() throws Exception {
        mockMvc.perform(get("/user/admin"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(model().attributeExists("allUsers"))
                .andExpect(model().attributeExists("allAppointments"))
                .andExpect(model().attributeExists("allOpenRequests"))
                .andExpect(model().attributeExists("allCloseRequests"))
                .andExpect(model().attributeExists("allMessages"))
                .andExpect(view().name("admin"));
    }
}
