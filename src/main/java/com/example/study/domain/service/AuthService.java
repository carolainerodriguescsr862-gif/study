package com.example.study.domain.service;

import com.example.study.api.dto.LoginRequestDTO;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;

    public AuthService(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    public void login(LoginRequestDTO dto){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.login(),
                        dto.password()));
    }
}
