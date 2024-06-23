package com.example.crowdfunding.controllers;

import com.example.crowdfunding.dtos.AuthResponseDTO;
import com.example.crowdfunding.dtos.LoginDTO;
import com.example.crowdfunding.dtos.RegisterDTO;
import com.example.crowdfunding.dtos.TestResponseDTO;
import com.example.crowdfunding.services.Iservices.AuthenticationService;
import com.example.crowdfunding.services.servicesImpl.TokenBlackListServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.example.crowdfunding.utils.ApiUri.AUTH_URI;

@RestController
@RequestMapping(AUTH_URI)
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {
    private final AuthenticationService authenticationService;
    private final TokenBlackListServiceImpl tokenBlackListService;
    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody LoginDTO loginDto){
        return authenticationService.login(loginDto);
    }

//    @PostMapping("/register")
//    public ResponseEntity<String> register(@RequestBody RegisterDTO registerDto) {
//        return authenticationService.register(registerDto);
//    }
      @PostMapping("/register")
      public TestResponseDTO register(@RequestBody RegisterDTO registerDto) {
          try {
              authenticationService.register(registerDto);
          } catch (Exception e) {
              return new TestResponseDTO("your registration failed", HttpStatus.BAD_REQUEST);
          }
          return new TestResponseDTO("success", HttpStatus.OK);
      }
    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request) {
        tokenBlackListService.addToBlacklist(request);
        return new ResponseEntity<>("You have logged out successfully", HttpStatus.OK);
    }
}