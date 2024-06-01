package com.example.crowdfunding.dtos;

import lombok.Data;

@Data
public class RegisterDTO {
    private String username;
    private String password;
    private String lastname;
    private String firstname;
    private String email;
    private String phone;
}
