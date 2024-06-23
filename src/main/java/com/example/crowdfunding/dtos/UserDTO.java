package com.example.crowdfunding.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UserDTO {
    private int id;
    private String userName;
    private String password;
    private String firstname;
    private String lastname;
    private String email;
    private String phone;
    private String country;
    private String city;
    private String street;
}
