package com.example.springboot_test.service;

import com.example.springboot_test.dto.UserDto;
import com.example.springboot_test.model.User;
import com.example.springboot_test.params.GwCreateUser;
import com.example.springboot_test.params.GwUpdateUser;
import com.example.springboot_test.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepo userRepo;

    private User gw2User(GwCreateUser params) {
        User user = new User();
        user.setUsername(params.getUsername());
        user.setBirthdate(params.getBirthdate());
        user.setAddress(params.getAddress());
        return user;
    }

    public UserDto createUser(GwCreateUser params) {
        User user = gw2User(params);
        userRepo.save(user);
        return new UserDto(user);
    }

    public List<UserDto> getAllUsers() {
        Iterable<User> all = userRepo.findAll();
        List<UserDto> dtos = new ArrayList<>();
        all.forEach((e) -> dtos.add(new UserDto(e)));
        return dtos;
    }

    public UserDto getUserById(Integer id) {
        return new UserDto(userRepo.findById(id).orElseThrow());
    }

    public UserDto updateUser(Integer id, GwUpdateUser gwUpdateUser) {
        User user = userRepo.findById(id).orElseThrow();
        if (!gwUpdateUser.getAddress().isEmpty())
            user.setAddress(gwUpdateUser.getAddress());
        if (!gwUpdateUser.getUsername().isEmpty())
            user.setUsername(gwUpdateUser.getUsername());
        if (gwUpdateUser.getBirthdate().isPresent())
            user.setBirthdate(gwUpdateUser.getBirthdate().get());
        userRepo.save(user);
        return new UserDto(userRepo.findById(id).orElseThrow());
    }

    public void deleteUser(Integer id) {
        userRepo.deleteById(id);
    }
}
