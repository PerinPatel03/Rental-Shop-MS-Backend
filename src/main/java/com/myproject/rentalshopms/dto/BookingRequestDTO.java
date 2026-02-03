package com.myproject.rentalshopms.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

//@Data
//@AllArgsConstructor
//@NoArgsConstructor
public class BookingRequestDTO {
    @NotNull(message = "Product id is required")
    private Long productId;
    @NotNull(message = "Customer id is required")
    private Long customerId;

    @NotNull(message = "Start date is required")
    private LocalDate startDate;
    @NotNull(message = "End date is required")
    private LocalDate endDate;

    @NotNull(message = "Discount is required")
    @DecimalMin(value = "0.0", inclusive = true, message = "Discount cannot be negative")
    @DecimalMax(value = "100.0", inclusive = true, message = "Discount cannot exceed 100")
    private BigDecimal discount;
    @NotNull(message = "Security deposit is required")
    @DecimalMin(value = "0.0", message = "Security deposit cannot be negative")
    private BigDecimal securityDeposit;

    public BookingRequestDTO() {
    }

    public BookingRequestDTO(Long productId, Long customerId, @NotNull(message = "Start date is required") LocalDate startDate, @NotNull(message = "End date is required") LocalDate endDate, @NotNull(message = "Discount is required") BigDecimal discount, @NotNull(message = "Security deposit is required") BigDecimal securityDeposit) {
        this.productId = productId;
        this.customerId = customerId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.discount = discount;
        this.securityDeposit = securityDeposit;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public BigDecimal getSecurityDeposit() {
        return securityDeposit;
    }

    public void setSecurityDeposit(BigDecimal securityDeposit) {
        this.securityDeposit = securityDeposit;
    }
}
