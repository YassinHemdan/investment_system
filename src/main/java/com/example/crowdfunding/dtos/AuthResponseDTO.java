package com.example.crowdfunding.dtos;

import lombok.Data;

import java.util.List;

@Data
public class AuthResponseDTO {
    private String accessToken;
    private String tokenType = "Bearer ";
    private List<String> roles;
    public AuthResponseDTO(String accessToken) {
        this.accessToken = accessToken;
    }
}
