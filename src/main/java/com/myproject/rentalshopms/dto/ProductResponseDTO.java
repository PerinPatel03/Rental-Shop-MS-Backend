package com.myproject.rentalshopms.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

//@Data
//@AllArgsConstructor
//@NoArgsConstructor
public class ProductResponseDTO {
    private Long id;
    private Integer productCode;
    private String name;
    private String description;
    private BigDecimal pricePerDay;
    private String imageUrl;
    private LocalDate createdAt;
    private LocalDate updatedAt;
    private boolean active;

    public ProductResponseDTO() {
    }

    public ProductResponseDTO(Long id, Integer productCode, String name, String description, BigDecimal pricePerDay, String imageUrl, LocalDate createdAt, LocalDate updatedAt, boolean active) {
        this.id = id;
        this.productCode = productCode;
        this.name = name;
        this.description = description;
        this.pricePerDay = pricePerDay;
        this.imageUrl = imageUrl;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.active = active;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getProductCode() {
        return productCode;
    }

    public void setProductCode(Integer productCode) {
        this.productCode = productCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(BigDecimal pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDate getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDate updatedAt) {
        this.updatedAt = updatedAt;
    }

    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
