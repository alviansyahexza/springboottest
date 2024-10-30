package com.example.springboot_test.controller;

import com.example.springboot_test.dto.UserDto;
import com.example.springboot_test.model.User;
import com.example.springboot_test.params.GwCreateUser;
import com.example.springboot_test.params.GwUpdateUser;
import com.example.springboot_test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    // Create a new user
    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody GwCreateUser params) {
        UserDto createdUser = userService.createUser(params);
        return ResponseEntity.ok(createdUser);
    }

    // Get all users
    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    // Get a single user by ID
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Integer id) {
        UserDto user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    // Update a user
    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Integer id, @RequestBody GwUpdateUser gwUpdateUser) {
        UserDto updatedUser = userService.updateUser(id, gwUpdateUser);
        return ResponseEntity.ok(updatedUser);
    }

    // Delete a user
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
