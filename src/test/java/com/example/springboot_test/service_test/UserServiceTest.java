package com.example.springboot_test.service_test;

import com.example.springboot_test.dto.UserDto;
import com.example.springboot_test.model.User;
import com.example.springboot_test.params.GwCreateUser;
import com.example.springboot_test.repo.UserRepo;
import com.example.springboot_test.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Date;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepo userRepo;

    @Test
    void register() {
        User user = new User();
        user.setBirthdate(new Date());
        user.setUsername("test username");
        user.setAddress("test address");
        user.setCreatedAt(new Date());
        user.setLastUpdateAt(new Date());

        Mockito.when(userRepo.save(Mockito.any())).thenReturn(user);

        UserDto result = userService.createUser(new GwCreateUser());
        Assertions.assertEquals(result.getId(), user.getId());
        Assertions.assertEquals(result.getUsername(), user.getUsername());
        Assertions.assertEquals(result.getAddress(), user.getAddress());
        Assertions.assertEquals(result.getBirthdate(), user.getBirthdate().getTime());
        Assertions.assertEquals(result.getCreatedAt(), user.getCreatedAt().getTime());
    }

    @Test
    void gw2User() {
        GwCreateUser params = new GwCreateUser();
        params.setUsername("test username");
        params.setAddress("test address");
        params.setBirthdate(new Date());
        User result = userService.gw2User(params);
        Assertions.assertEquals(result.getAddress(), params.getAddress());
        Assertions.assertEquals(result.getUsername(), params.getUsername());
        Assertions.assertEquals(result.getBirthdate(), params.getBirthdate());
    }
}

