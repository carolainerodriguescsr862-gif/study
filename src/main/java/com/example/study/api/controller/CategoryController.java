package com.example.study.api.controller;


import com.example.study.api.dto.CategoryRequestDTO;
import com.example.study.domain.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
    // get
    @GetMapping("/list/all")
    public ResponseEntity<?> listAll(){
        var category = categoryService.findAll();
        return ResponseEntity.ok(category);
    }

    @PostMapping("/created")
    public ResponseEntity<?> create(@RequestBody CategoryRequestDTO data){
        var category = categoryService.create(data);
        return ResponseEntity.ok(category);
    }
}
