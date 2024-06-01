package com.example.crowdfunding.services.servicesImpl;

import com.example.crowdfunding.config.jwt.JWTGenerator;
import com.example.crowdfunding.dtos.AuthResponseDTO;
import com.example.crowdfunding.dtos.LoginDTO;
import com.example.crowdfunding.dtos.RegisterDTO;
import com.example.crowdfunding.entities.Authority;
import com.example.crowdfunding.entities.UserEntity;
import com.example.crowdfunding.repos.AuthorityRepo;
import com.example.crowdfunding.repos.UserRepo;
import com.example.crowdfunding.services.Iservices.AuthenticationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@AllArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final AuthenticationManager authenticationManager;
    private final UserRepo userRepository;
    private final AuthorityRepo authorityRepo;
    private final PasswordEncoder passwordEncoder;
    private final JWTGenerator jwtGenerator;

    @Override
    public ResponseEntity<String> register(RegisterDTO registerDTO) {
        if (userRepository.existsByUserName(registerDTO.getUsername())) {
            return new ResponseEntity<>("Username is taken!", HttpStatus.BAD_REQUEST);
        }
        UserEntity user = new UserEntity();
        user.setUserName(registerDTO.getUsername());
        user.setPassword(passwordEncoder.encode((registerDTO.getPassword())));

        Authority authority = authorityRepo.findByAuthorityName("USER").get();
        user.setAuthorities(Collections.singletonList(authority));

        userRepository.save(user);

        return new ResponseEntity<>("User registered success!", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<AuthResponseDTO> login(LoginDTO loginDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDTO.getUsername(),
                        loginDTO.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtGenerator.generateToken(authentication);
        return new ResponseEntity<>(new AuthResponseDTO(token), HttpStatus.OK);
    }
}
