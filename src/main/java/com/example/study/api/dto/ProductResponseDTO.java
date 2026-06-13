package com.example.study.api.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;


public record ProductResponseDTO(String id,
                                 String name,
                                 BigDecimal price,
                                 boolean active)
{}
