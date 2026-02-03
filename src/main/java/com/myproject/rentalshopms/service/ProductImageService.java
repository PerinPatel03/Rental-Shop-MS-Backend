package com.myproject.rentalshopms.service;

import com.myproject.rentalshopms.model.Product;
import com.myproject.rentalshopms.repository.ProductRepo;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class ProductImageService {
    private final ProductRepo productRepo;
    private final Path uploadDir = Paths.get("uploads/images/products");

    public ProductImageService(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

//    public String getImageUrl(Long productId) {
//        Product product = productRepo.findById(productId).orElseThrow(() -> new RuntimeException("Product not found"));
//
//        return product.getImageUrl();
//    }

    public String uploadImage(Long productId, MultipartFile file) {
        if (file.isEmpty()) {
            throw new RuntimeException("File is empty");
        }

        String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();

        try {
            Files.createDirectories(uploadDir);
            Files.copy(file.getInputStream(), uploadDir.resolve(fileName));
        } catch (IOException e) {
            throw new RuntimeException("Image upload failed");
        }

        Product product = productRepo.findById(productId).orElseThrow(() -> new RuntimeException("Product not found"));

        product.setImageUrl("/images/products/" + fileName);
        productRepo.save(product);

        return product.getImageUrl();
    }

    public String updateImage(Long productId, MultipartFile file) {
        deleteImage(productId);

        return uploadImage(productId, file);
    }

    public void deleteImage(Long productId) {
        Product product = productRepo.findById(productId).orElseThrow(() -> new RuntimeException("Product not found"));

        if (product.getImageUrl() == null) {
            return;
        }

        String fileName = Paths.get(product.getImageUrl()).getFileName().toString();
        Path filePath = uploadDir.resolve(fileName);

        try {
            Files.deleteIfExists(filePath);
        } catch (IOException e) {
            throw new RuntimeException("Image deletion failed");
        }

        product.setImageUrl(null);
        productRepo.save(product);
    }
}
