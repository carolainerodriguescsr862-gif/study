package com.example.study.api.mapper;

import com.example.study.api.dto.CategoryResponseDTO;
import com.example.study.domain.entity.Category;

public class CategoryMapper {

    public static CategoryResponseDTO toDTO(Category category){
       return new CategoryResponseDTO(
                category.getId(),
                category.getName(),
                category.getProducts()
        );
    }
}
