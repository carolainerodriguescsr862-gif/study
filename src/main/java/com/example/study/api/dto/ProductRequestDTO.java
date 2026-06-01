package com.example.study.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductRequestDTO {
    @NotBlank(message= "Name cannot be empty")
    private String name;

    @NotBlank(message = "Price is required")
    @Positive(message= "Price must be greater than zero")
    private BigDecimal price;
}



