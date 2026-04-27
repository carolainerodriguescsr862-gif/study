package com.example.study.api.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductUpdateDTO {
    private String name;
    private BigDecimal price;
}
