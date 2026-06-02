package com.example.study.api.dto;

import com.example.study.domain.entity.Product;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record CategoryResponseDTO(String id,
                                  @NotBlank(message= "Name cannot be empty")String name,
                                  List<Product> products) {}
