package org.example.springsample.dto;

import lombok.Data;

public record LoginRequest(String username, String password) {
}
