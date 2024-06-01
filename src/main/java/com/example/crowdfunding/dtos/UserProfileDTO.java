package com.example.crowdfunding.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserProfileDTO {
    private String userName;
    private String email;
    private String country;
    private String city;
    private String street;

    // TODO: ADD phone attribute
}
