package com.example.springboot_test.api_test;

import com.example.springboot_test.model.User;
import com.example.springboot_test.repo.UserRepo;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Date;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserApiTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepo userRepo;

    User user;

    @BeforeEach
    public void setup() {
        user = new User();
        user.setBirthdate(new Date());
        user.setUsername("test username");
        user.setAddress("test address");
        userRepo.save(user);
    }

    @AfterEach
    public void clear() {
        userRepo.deleteById(user.getId());
    }

    @Test
    public void shouldFetchUserFromDatabase() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/users/" + user.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value(user.getUsername()))
                .andExpect(jsonPath("$.address").value(user.getAddress()))
                .andExpect(jsonPath("$.birthdate").value(user.getBirthdate()));
    }
}
