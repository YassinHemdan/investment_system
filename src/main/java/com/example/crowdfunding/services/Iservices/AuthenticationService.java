package com.example.crowdfunding.services.Iservices;

import com.example.crowdfunding.dtos.AuthResponseDTO;
import com.example.crowdfunding.dtos.LoginDTO;
import com.example.crowdfunding.dtos.RegisterDTO;
import org.springframework.http.ResponseEntity;

public interface AuthenticationService {
    ResponseEntity<String> register(RegisterDTO registerDTO) throws Exception;
    ResponseEntity<AuthResponseDTO> login(LoginDTO loginDTO);
}
