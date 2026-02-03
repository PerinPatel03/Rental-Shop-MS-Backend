package com.myproject.rentalshopms.dto;

import com.myproject.rentalshopms.enums.BookingStatus;
import com.myproject.rentalshopms.enums.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

//@Data
//@AllArgsConstructor
//@NoArgsConstructor
public class BookingResponseDTO {
    private Long id;
    private Long productId;
    private Long customerId;

    private LocalDate startDate;
    private LocalDate endDate;

    private BigDecimal discount;
    private BigDecimal totalAmount;
    private BigDecimal securityDeposit;

    private BookingStatus bookingStatus;
    private PaymentStatus paymentStatus;
    private LocalDate createdAt;

    private LocalDate pickedUpAt;

    private BigDecimal keptSecurityDeposit;
    private String keptDepositReason;
    private LocalDate returnedAt;

    public BookingResponseDTO() {
    }

    public BookingResponseDTO(Long id, Long productId, Long customerId, LocalDate startDate, LocalDate endDate, BigDecimal discount, BigDecimal totalAmount, BigDecimal securityDeposit, BookingStatus bookingStatus, PaymentStatus paymentStatus, LocalDate createdAt, LocalDate pickedUpAt, BigDecimal keptSecurityDeposit, String keptDepositReason, LocalDate returnedAt) {
        this.id = id;
        this.productId = productId;
        this.customerId = customerId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.discount = discount;
        this.totalAmount = totalAmount;
        this.securityDeposit = securityDeposit;
        this.bookingStatus = bookingStatus;
        this.paymentStatus = paymentStatus;
        this.createdAt = createdAt;
        this.pickedUpAt = pickedUpAt;
        this.keptSecurityDeposit = keptSecurityDeposit;
        this.keptDepositReason = keptDepositReason;
        this.returnedAt = returnedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getSecurityDeposit() {
        return securityDeposit;
    }

    public void setSecurityDeposit(BigDecimal securityDeposit) {
        this.securityDeposit = securityDeposit;
    }

    public BookingStatus getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(BookingStatus bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDate getPickedUpAt() {
        return pickedUpAt;
    }

    public void setPickedUpAt(LocalDate pickedUpAt) {
        this.pickedUpAt = pickedUpAt;
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

    public LocalDate getReturnedAt() {
        return returnedAt;
    }

    public void setReturnedAt(LocalDate returnedAt) {
        this.returnedAt = returnedAt;
    }
}
