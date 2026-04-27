package com.example.study.api.dto;

import java.math.BigDecimal;

public record ProductResponseDTO(String id, String name, BigDecimal price, boolean active) {
}
