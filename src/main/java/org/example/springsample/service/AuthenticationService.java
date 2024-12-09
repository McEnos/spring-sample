package org.example.springsample.service;

import lombok.RequiredArgsConstructor;
import org.example.springsample.dto.LoginRequest;
import org.example.springsample.dto.LoginResponse;
import org.example.springsample.entity.User;
import org.example.springsample.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final TokenService tokenService;

    public LoginResponse login(LoginRequest loginRequest) {
        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.username(), loginRequest.password())
        );
        User user = userRepository.findByEmail(loginRequest.username()).get();
        return tokenService.generateToken(authenticate, user);
    }
}
