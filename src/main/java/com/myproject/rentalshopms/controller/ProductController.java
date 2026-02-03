package com.myproject.rentalshopms.controller;

import com.myproject.rentalshopms.dto.ProductRequestDTO;
import com.myproject.rentalshopms.dto.ProductResponseDTO;
import com.myproject.rentalshopms.service.ProductImageService;
import com.myproject.rentalshopms.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class ProductController {
    private final ProductService productService;
    private final ProductImageService productImageService;

    public ProductController(ProductService productService, ProductImageService productImageService) {
        this.productService = productService;
        this.productImageService = productImageService;
    }

    @GetMapping("/products")
    public ResponseEntity<List<ProductResponseDTO>> getActiveProducts() {
        return new ResponseEntity<>(productService.getActiveProducts(), HttpStatus.OK);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<ProductResponseDTO> getProductById(@PathVariable Long id) {
        return new ResponseEntity<>(productService.getProductById(id), HttpStatus.OK);
    }

    @PostMapping("/addproduct")
    public ResponseEntity<ProductResponseDTO> addProduct(@Valid @RequestBody ProductRequestDTO requestDTO) {
        return new ResponseEntity<>(productService.addProduct(requestDTO), HttpStatus.OK);
    }

    @PutMapping("/updateproduct/{id}")
    public ResponseEntity<ProductResponseDTO> updateProduct(@PathVariable Long id, @Valid @RequestBody ProductRequestDTO updatedRequestDTO) {
        return new ResponseEntity<>(productService.updateProduct(id, updatedRequestDTO), HttpStatus.OK);
    }

    @DeleteMapping("/softdeleteproduct/{id}")
    public ResponseEntity<ProductResponseDTO> softDeleteProduct(@PathVariable Long id) {
        return new ResponseEntity<>(productService.softDeleteProduct(id), HttpStatus.OK);
    }

//    @DeleteMapping("/deleteproduct/{id}")
//    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
//        productImageService.deleteImage(id);
//        productService.deleteProduct(id);
//        return new ResponseEntity<>("Product deleted", HttpStatus.OK);
//    }

    // "/products/search?keyword=${keyword}"
//    @GetMapping("/products/search")
//    public ResponseEntity<List<ProductResponseDTO>> searchProducts(@RequestParam String keyword) {
//        return new ResponseEntity<>(productService.searchProducts(keyword), HttpStatus.OK);
//    }
}
