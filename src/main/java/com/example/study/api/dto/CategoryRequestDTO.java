package com.example.study.api.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CategoryRequestDTO {

    @NotBlank(message= "Name cannot be empty")
    private String name;
}
