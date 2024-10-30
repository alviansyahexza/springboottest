package com.example.springboot_test.params;

import lombok.Data;

import java.util.Date;

@Data
public class GwCreateUser {
    String username;
    String address;
    Date birthdate;
}
