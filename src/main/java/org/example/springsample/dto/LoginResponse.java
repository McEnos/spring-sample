package org.example.springsample.dto;

import lombok.Builder;

@Builder
public record LoginResponse(String accessToken, String expiresIn) {
}
