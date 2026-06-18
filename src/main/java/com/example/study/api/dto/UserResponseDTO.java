package com.example.study.api.dto;

import java.time.LocalDateTime;

public record UserResponseDTO(
        String id,
        String name,
        LocalDateTime dateTime
) {}
