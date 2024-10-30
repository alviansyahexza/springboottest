package com.example.springboot_test.dto;

import com.example.springboot_test.model.User;
import lombok.Data;

@Data
public class UserDto {
    Integer id;
    String username;
    String address;
    Long birthdate;
    Long createdAt;
    Long lastUpdateAt;

    public UserDto(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.address = user.getAddress();
        this.birthdate = user.getBirthdate().getTime();
        this.createdAt = user.getCreatedAt().getTime();
        this.lastUpdateAt = user.getLastUpdateAt().getTime();
    }
}
