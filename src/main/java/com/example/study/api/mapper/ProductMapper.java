package com.example.study.api.mapper;

import com.example.study.api.dto.ProductResponseDTO;
import com.example.study.domain.entity.Product;

public class ProductMapper {
    public static ProductResponseDTO toDTO(Product product){
        return new ProductResponseDTO(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.isActive()
        );
    }
}
