package org.example.springsample.service;

import lombok.RequiredArgsConstructor;
import org.example.springsample.dto.LoginResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Service
@RequiredArgsConstructor
public class TokenService {
   private final JwtEncoder jwtEncoder;


 public LoginResponse generateToken(Authentication authentication, Object user) {
        Instant instant = Instant.now();
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(instant)
                .expiresAt(instant.plus(3600, ChronoUnit.SECONDS))
                .subject(authentication.getName())
                .claim("user", user)
                .build();
        String tokenValue = this.jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
        return LoginResponse.builder()
                .accessToken(tokenValue)
                .expiresIn(String.valueOf(3599))
                .build();
    }
}
