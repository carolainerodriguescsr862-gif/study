package com.example.study.api.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;


public record ProductResponseDTO(String id,
                                 @NotBlank(message= "Name cannot be empty")
                                 String name,
                                 @NotBlank(message = "Price is required")
                                 @Positive(message= "Price must be greater than zero")
                                 BigDecimal price,
                                 boolean active)
{}
