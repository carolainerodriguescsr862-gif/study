package com.example.study.domain.service;

import com.example.study.api.dto.ProductPatchDTO;
import com.example.study.api.dto.ProductRequestDTO;
import com.example.study.api.dto.ProductResponseDTO;
import com.example.study.api.dto.ProductUpdateDTO;
import com.example.study.api.mapper.ProductMapper;
import com.example.study.domain.entity.Category;
import com.example.study.domain.entity.Product;
import com.example.study.domain.exception.BusinessException;
import com.example.study.domain.exception.ResourceNotFoundException;
import com.example.study.domain.repository.CategoryRepository;
import com.example.study.domain.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    private Product findByIdOrThrow(String id){
        return  productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
    }

    public ProductResponseDTO create(ProductRequestDTO dto, String categoryId){

        boolean exist = productRepository.existsByName(dto.getName());
        if(exist){
            throw new BusinessException("There is already a product with that name!");
        }
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found."));

        Product product = new Product();

        product.setName(dto.getName());
        product.setPrice(dto.getPrice());
        product.setActive(true);
        product.setCategory(category);

        Product saved = productRepository.save(product);
        return ProductMapper.toDTO(saved);
    }

    public ProductResponseDTO update(String id, ProductUpdateDTO dto){
        Product product = findByIdOrThrow(id);
        if(!product.isActive()){
            throw new BusinessException("Product is not active!");
        }

        boolean exist = productRepository.existsByName(dto.getName());
        if(exist && !product.getName().equals(dto.getName())){
            throw new BusinessException("There is already a product with that name.");
        }
        product.setName(dto.getName());
        product.setPrice(dto.getPrice());

        Product updated = productRepository.save(product);
        return ProductMapper.toDTO(updated);

    }

    public ProductResponseDTO updatePartial(String id, ProductPatchDTO dto) {

        Product product = findByIdOrThrow(id);

        if (!product.isActive()) {
            throw new BusinessException("Inactive product");
        }

        if (dto.getName() != null && !dto.getName().isBlank()) {
            product.setName(dto.getName());
        }

        if (dto.getPrice() != null && dto.getPrice().compareTo(BigDecimal.ZERO) > 0) {
            product.setPrice(dto.getPrice());
        }

        Product updated = productRepository.save(product);

        return ProductMapper.toDTO(updated);
    }
 public ProductResponseDTO findById(String id) {

        Product product = findByIdOrThrow(id);

        return ProductMapper.toDTO(product);
    }

    public List<ProductResponseDTO> findAll() {

        return productRepository.findAll()
                .stream()
                .map(ProductMapper::toDTO
                )
                .toList();
    }

    public void delete(String id) {

        Product product =findByIdOrThrow(id);

        if(!product.isActive()){
            throw new BusinessException("Product not active.");
        }

        product.setActive(false);
        productRepository.save(product);
    }

}
