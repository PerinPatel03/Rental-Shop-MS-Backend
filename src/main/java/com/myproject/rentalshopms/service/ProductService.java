package com.myproject.rentalshopms.service;

import com.myproject.rentalshopms.dto.ProductRequestDTO;
import com.myproject.rentalshopms.dto.ProductResponseDTO;
import com.myproject.rentalshopms.model.Product;
import com.myproject.rentalshopms.repository.ProductRepo;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ProductService {
    private final ProductRepo productRepo;

    public ProductService(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    public ProductResponseDTO mapToResponseDTO(Product product) {
        ProductResponseDTO prodResDTO = new ProductResponseDTO();
        prodResDTO.setId(product.getId());
        prodResDTO.setProductCode(product.getProductCode());
        prodResDTO.setName(product.getName());
        prodResDTO.setDescription(product.getDescription());
        prodResDTO.setPricePerDay(product.getPricePerDay());
        prodResDTO.setImageUrl(product.getImageUrl());
        prodResDTO.setCreatedAt(product.getCreatedAt());
        prodResDTO.setUpdatedAt(product.getUpdatedAt());
        prodResDTO.setActive(product.getActive());

        return prodResDTO;
    }

    public List<ProductResponseDTO> getActiveProducts() {
        return productRepo.getActiveProducts()
                .stream().map(this::mapToResponseDTO).toList(); // Here, this::mapToResponseDTO ===> (product) -> mapToResponseDTO(product)
    }

    public ProductResponseDTO getProductById(Long prodId) {
        Product product = productRepo.findById(prodId).orElseThrow(() -> new RuntimeException("Product not found"));
        return mapToResponseDTO(product);
    }

    public ProductResponseDTO addProduct(ProductRequestDTO dto) {
        Product product = new Product();
        product.setProductCode(dto.getProductCode());
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPricePerDay(dto.getPricePerDay());

        product.setActive(true);
        product.setCreatedAt(LocalDate.now());
        product.setUpdatedAt(LocalDate.now());

        Product saved = productRepo.save(product);
        return mapToResponseDTO(saved);
    }

    public ProductResponseDTO updateProduct(Long prodId, ProductRequestDTO dto) {
        Product product = productRepo.findById(prodId).orElseThrow(() -> new RuntimeException("Product not found"));
        product.setProductCode(dto.getProductCode());
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPricePerDay(dto.getPricePerDay());

        product.setUpdatedAt(LocalDate.now());

        Product updated = productRepo.save(product);
        return mapToResponseDTO(updated);
    }

    public ProductResponseDTO softDeleteProduct(Long prodId) {
        Product product = productRepo.findById(prodId).orElseThrow(() -> new RuntimeException("Product not found"));
        product.setActive(false);
        Product softDeletedProduct = productRepo.save(product);

        return mapToResponseDTO(softDeletedProduct);
    }

//    public void deleteProduct(Long prodId) {
//        productRepo.deleteById(prodId);
//    }

//    public List<ProductResponseDTO> searchProducts(String keyword) {
//        return productRepo.searchProducts(keyword)
//                .stream().map(this::mapToResponseDTO).toList();
//    }
}
