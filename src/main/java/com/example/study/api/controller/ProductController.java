package com.example.study.api.controller;

import com.example.study.api.dto.ProductPatchDTO;
import com.example.study.api.dto.ProductRequestDTO;
import com.example.study.api.dto.ProductResponseDTO;
import com.example.study.api.dto.ProductUpdateDTO;
import com.example.study.domain.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/categories/{categoryId}/products")
    public ResponseEntity<ProductResponseDTO> createProduct(@Valid @RequestBody ProductRequestDTO data, @PathVariable String categoryId){
        var product = productService.create(data, categoryId);
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

    @GetMapping("/products")
    public ResponseEntity<Page<ProductResponseDTO>> listAll(Pageable pageable){
        var products = productService.findAll(pageable);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<ProductResponseDTO> listId(@PathVariable String id){
        var product = productService.findById(id);
        return ResponseEntity.ok(product);
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<ProductResponseDTO> updateAll(@PathVariable String id, @Valid @RequestBody ProductUpdateDTO data){
        var product = productService.update(id,data);
        return ResponseEntity.ok(product);
    }
    @PatchMapping("/products/{id}")
    public ResponseEntity<ProductResponseDTO> updatePartial(@PathVariable String id, @Valid @RequestBody ProductPatchDTO data){

        var product = productService.updatePartial(id,data);
        return ResponseEntity.ok(product);
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id){
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
