package com.example.study.api.mapper;

import com.example.study.api.dto.CategoryDetailDTO;
import com.example.study.api.dto.CategoryResponseDTO;
import com.example.study.api.dto.ProductSummaryDTO;
import com.example.study.domain.entity.Category;

import java.util.List;

public class CategoryMapper {

    public static CategoryDetailDTO toDetailDTO(Category category){

        List<ProductSummaryDTO> products = category.getProducts()
                .stream()
                .map(product -> new ProductSummaryDTO(
                        product.getId(),
                        product.getName()
                )).toList();

       return new CategoryDetailDTO(
                category.getId(),
                category.getName(),
                products
        );
    }

    public static CategoryResponseDTO toDTO(Category category){
        return new CategoryResponseDTO(category.getId(),
                category.getName());
    }

}
