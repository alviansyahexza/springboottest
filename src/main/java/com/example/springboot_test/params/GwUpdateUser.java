package com.example.springboot_test.params;

import lombok.Data;

import java.util.Date;
import java.util.Optional;

@Data
public class GwUpdateUser {
    String username;
    String address;
    Optional<Date> birthdate;
}
