package com.example.study.api.controller;


import com.example.study.api.dto.CategoryDetailDTO;
import com.example.study.api.dto.CategoryRequestDTO;
import com.example.study.api.dto.CategoryResponseDTO;
import com.example.study.domain.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<Page<CategoryResponseDTO>> listAll(Pageable pageable){
        var category = categoryService.findAll(pageable);
        return ResponseEntity.ok(category);
    }

    @PostMapping
    public ResponseEntity<CategoryResponseDTO> create(@Valid @RequestBody CategoryRequestDTO data){
        var category = categoryService.create(data);
        return ResponseEntity.status(HttpStatus.CREATED).body(category);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDetailDTO> findById(@PathVariable String id){
        var category = categoryService.findById(id);
        return ResponseEntity.ok(category);
    }
}
