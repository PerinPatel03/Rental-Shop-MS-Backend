package com.myproject.rentalshopms.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

//@Data
//@AllArgsConstructor
//@NoArgsConstructor
public class BookingReturnRequestDTO {
    @NotNull(message = "Kept security deposit is required")
    @DecimalMin(value = "0.0", message = "Kept security deposit cannot be negative")
    private BigDecimal keptSecurityDeposit;
    @NotBlank(message = "Kept security deposit reason is required")
    @Size(max = 255, message = "Kept security deposit reason can not exceed 255 characters")
    private String keptDepositReason;

    public BookingReturnRequestDTO() {
    }

    public BookingReturnRequestDTO(@NotNull(message = "Kept security deposit is required") BigDecimal keptSecurityDeposit, String keptDepositReason) {
        this.keptSecurityDeposit = keptSecurityDeposit;
        this.keptDepositReason = keptDepositReason;
    }

    public BigDecimal getKeptSecurityDeposit() {
        return keptSecurityDeposit;
    }

    public void setKeptSecurityDeposit(BigDecimal keptSecurityDeposit) {
        this.keptSecurityDeposit = keptSecurityDeposit;
    }

    public String getKeptDepositReason() {
        return keptDepositReason;
    }

    public void setKeptDepositReason(String keptDepositReason) {
        this.keptDepositReason = keptDepositReason;
    }
}
