package com.example.study.domain.service;

import com.example.study.api.dto.ProductPatchDTO;
import com.example.study.api.dto.ProductRequestDTO;
import com.example.study.api.dto.ProductResponseDTO;
import com.example.study.api.dto.ProductUpdateDTO;
import com.example.study.api.mapper.ProductMapper;
import com.example.study.domain.entity.Product;
import com.example.study.domain.exception.BusinessException;
import com.example.study.domain.exception.ResourceNotFoundException;
import com.example.study.domain.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductResponseDTO create(ProductRequestDTO dto){

        if(dto.getName() == null || dto.getName().isBlank()){
            throw new BusinessException("Invalid name!");
        }

        if(dto.getPrice() == null || dto.getPrice().compareTo(BigDecimal.ZERO) <= 0){
            throw new BusinessException("The value must be greater than zero.");
        }
        boolean exist = productRepository.existsByName(dto.getName());
        if(exist){
            throw new BusinessException("There is already a product with that name!");
        }

        Product product = new Product();
        product.setName(dto.getName());
        product.setPrice(dto.getPrice());
        product.setActive(true);

        Product saved = productRepository.save(product);
        return ProductMapper.toDTO(product);
    }

    public ProductResponseDTO update(String id, ProductUpdateDTO dto){
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found."));

        if(!product.isActive()){
            throw new BusinessException("Product is not active!");
        }
        if(dto.getName() == null || dto.getName().isBlank()){
            throw new BusinessException("Invalid name!");
        }

        if(dto.getPrice() == null || dto.getPrice().compareTo(BigDecimal.ZERO) <= 0){
            throw new BusinessException("The value must be greater than zero.");
        }

        boolean exist = productRepository.existsByName(dto.getName());
        if(exist && !product.getName().equals(dto.getName())){
            throw new BusinessException("There is already a product with that name.");
        }
        product.setName(dto.getName());
        product.setPrice(dto.getPrice());

        Product updated = productRepository.save(product);
        return ProductMapper.toDTO(product);

    }

    public ProductResponseDTO updatePartial(String id, ProductPatchDTO dto) {

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

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

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

        return ProductMapper.toDTO(product);
    }

    public List<ProductResponseDTO> findAll() {

        return productRepository.findAll()
                .stream()
                .map(p -> new ProductResponseDTO(
                        p.getId(),
                        p.getName(),
                        p.getPrice(),
                        p.isActive()
                ))
                .toList();
    }

    public void delete(String id) {

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

        product.setActive(false);
        productRepository.save(product);
    }

}
