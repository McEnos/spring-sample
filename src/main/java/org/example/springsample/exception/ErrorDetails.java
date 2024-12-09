package org.example.springsample.exception;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@Data
public class ErrorDetails {
    private String message;
    private String details;
    private LocalDateTime timestamp;
}
