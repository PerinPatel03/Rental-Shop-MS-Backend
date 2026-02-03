package com.myproject.rentalshopms.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

//@Data
//@AllArgsConstructor
//@NoArgsConstructor
public class ProductRequestDTO {

    @NotNull(message = "Product code is required")
    @Positive(message = "Product code must be positive")
    private Integer productCode;

    @NotBlank(message = "Product name is required")
    @Size(min = 2, max = 100, message = "Product name must be between 2 and 100 characters")
    private String name;

    @NotBlank(message = "Description is required")
    @Size(max = 255, message = "Description can not exceed 255 characters")
    private String description;

    @NotNull(message = "Price per day is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Price per day must be greater than 0")
    private BigDecimal pricePerDay;

    public ProductRequestDTO() {
    }

    public ProductRequestDTO(@NotNull(message = "Product code is required") Integer productCode, String name, String description, @NotNull(message = "Price per day is required") BigDecimal pricePerDay) {
        this.productCode = productCode;
        this.name = name;
        this.description = description;
        this.pricePerDay = pricePerDay;
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
}
