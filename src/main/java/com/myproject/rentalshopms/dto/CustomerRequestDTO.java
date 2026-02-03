package com.myproject.rentalshopms.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//@Data
//@AllArgsConstructor
//@NoArgsConstructor
public class    CustomerRequestDTO {
    @NotBlank(message = "Customer name is required")
    @Size(min = 2, max = 100, message = "Customer name must be between 2 and 100 characters")
    private String customerName;

    @NotNull(message = "Phone number is required")
    @Pattern(
            regexp = "^[+]?[0-9]{10,15}$",
            message = "Invalid phone number"
    )
    @Column(nullable = false, length = 20)
    private String phoneNumber;

    @NotBlank(message = "Address is required")
    @Size(max = 255, message = "Address can not exceed 255 characters")
    private String address;

    public CustomerRequestDTO() {
    }

    public CustomerRequestDTO(String customerName, @NotNull(message = "Phone number is required") String phoneNumber, String address) {
        this.customerName = customerName;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
