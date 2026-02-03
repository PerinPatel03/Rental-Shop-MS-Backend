package com.myproject.rentalshopms.controller;

import com.myproject.rentalshopms.service.ProductImageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin
@RequestMapping("/api/products")
public class ProductImageController {
    private final ProductImageService productImageService;

    public ProductImageController(ProductImageService productImageService) {
        this.productImageService = productImageService;
    }

//    @GetMapping("/{id}/getimageurl")
//    public ResponseEntity<String> getImageUrl(@PathVariable Long id) {
//        return new ResponseEntity<>(productImageService.getImageUrl(id), HttpStatus.OK);
//    }

    @PostMapping("/{id}/uploadimage")
    public ResponseEntity<String> uploadImage(@PathVariable Long id, @RequestParam("file") MultipartFile file) {
        return new ResponseEntity<>(productImageService.uploadImage(id, file), HttpStatus.OK);
    }

    @PutMapping("/{id}/updateimage")
    public ResponseEntity<String> updateImage(@PathVariable Long id, @RequestParam("file") MultipartFile file) {
        return new ResponseEntity<>(productImageService.updateImage(id, file), HttpStatus.OK);
    }

//    @DeleteMapping("/{id}/deleteimage")
//    public ResponseEntity<String> deleteImage(@PathVariable Long id) {
//        productImageService.deleteImage(id);
//        return new ResponseEntity<>("Image deleted", HttpStatus.OK);
//    }
}
