package com.example.study.domain.service;

import com.example.study.api.dto.CategoryRequestDTO;
import com.example.study.api.dto.CategoryResponseDTO;
import com.example.study.api.mapper.CategoryMapper;
import com.example.study.domain.entity.Category;
import com.example.study.domain.exception.BusinessException;
import com.example.study.domain.exception.ResourceNotFoundException;
import com.example.study.domain.repository.CategoryRepository;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public CategoryResponseDTO create(CategoryRequestDTO dto){
        boolean exist = categoryRepository.existsByName(dto.getName());
        if(exist){
            throw new BusinessException("There is already a category with that name!");
        }
        Category category = new Category();

        category.setName(dto.getName());
        category.setProducts(dto.getProducts());

        Category saved = categoryRepository.save(category);

        return CategoryMapper.toDTO(saved);
    }
}
