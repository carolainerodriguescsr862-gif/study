package com.example.study.api.dto;

import com.example.study.domain.entity.Product;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;

@Data
public class CategoryRequestDTO {

    @NotBlank(message= "Name cannot be empty")
    private String name;
    private List<Product> products;
}
