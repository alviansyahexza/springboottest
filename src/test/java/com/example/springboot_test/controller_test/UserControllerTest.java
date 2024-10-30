package com.example.springboot_test.controller_test;

import com.example.springboot_test.dto.UserDto;
import com.example.springboot_test.model.User;
import com.example.springboot_test.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Date;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class UserControllerTest {

    @MockBean
    private UserService userService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getUserById() throws Exception {
        User user = new User();
        user.setId(212);
        user.setBirthdate(new Date());
        user.setUsername("test username");
        user.setAddress("test address");
        user.setCreatedAt(new Date());
        user.setLastUpdateAt(new Date());

        Mockito.when(userService.getUserById(user.getId())).thenReturn(new UserDto(user));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/users/" + user.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value(user.getUsername()))
                .andExpect(jsonPath("$.address").value(user.getAddress()))
                .andExpect(jsonPath("$.birthdate").value(user.getBirthdate()))
        ;
    }
}
