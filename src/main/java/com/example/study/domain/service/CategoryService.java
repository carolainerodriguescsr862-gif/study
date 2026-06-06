package com.example.study.domain.service;

import com.example.study.api.dto.CategoryRequestDTO;
import com.example.study.api.dto.CategoryResponseDTO;
import com.example.study.api.mapper.CategoryMapper;
import com.example.study.domain.entity.Category;
import com.example.study.domain.exception.BusinessException;
import com.example.study.domain.exception.ResourceNotFoundException;
import com.example.study.domain.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    private Category findByIdOrThrow(String id){
        return  categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));
    }


    public CategoryResponseDTO create(CategoryRequestDTO dto){
        boolean exist = categoryRepository.existsByName(dto.getName());
        if(exist){
            throw new BusinessException("There is already a category with that name!");
        }
        Category category = new Category();

        category.setName(dto.getName());

        Category saved = categoryRepository.save(category);

        return CategoryMapper.toDTO(saved);
    }

    public CategoryResponseDTO update(String id, CategoryRequestDTO dto){
        Category category= findByIdOrThrow(id);

        boolean exist = categoryRepository.existsByName(dto.getName());
        if(exist && !category.getName().equals(dto.getName())){
            throw new BusinessException("There is already a category with that name.");
        }
        category.setName(dto.getName());

        Category saved = categoryRepository.save(category);
        return CategoryMapper.toDTO(saved);
    }


    public List<CategoryResponseDTO> findAll(){

        return categoryRepository.findAll().stream()
                .map(CategoryMapper::toDTO)
                .toList();
    }

    public CategoryResponseDTO findById(String id){

        Category category = findByIdOrThrow(id);
        return CategoryMapper.toDTO(category);
    }

}
