package com.example.study.api.dto;

import com.example.study.domain.enums.UserRole;

import java.time.LocalDateTime;

public record UserResponseDTO(
        String id,
        String login,
        LocalDateTime dateTime,
        UserRole userRole
) {}
