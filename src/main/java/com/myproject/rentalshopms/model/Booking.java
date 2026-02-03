package com.myproject.rentalshopms.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.myproject.rentalshopms.enums.BookingStatus;
import com.myproject.rentalshopms.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "booking", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Payment> payments;

    @ManyToOne
    @JoinColumn(
            name = "productId",
            referencedColumnName = "id",
            nullable = false
    )
    private Product product;

    @ManyToOne
    @JoinColumn(
            name = "customerId",
            referencedColumnName = "id",
            nullable = false
    )
    private Customer customer;

    private LocalDate startDate;
    private LocalDate endDate;

    private BigDecimal discount;
    private BigDecimal totalAmount;
    private BigDecimal securityDeposit;

    @Enumerated(EnumType.STRING)
    private BookingStatus bookingStatus;
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;
    private LocalDate createdAt;

    private LocalDate pickedUpAt;

    private BigDecimal keptSecurityDeposit;
    private String keptDepositReason;
    private LocalDate returnedAt;

    public Booking() {
    }

    public Booking(Long id, List<Payment> payments, Product product, Customer customer, LocalDate startDate, LocalDate endDate, BigDecimal discount, BigDecimal totalAmount, BigDecimal securityDeposit, BookingStatus bookingStatus, PaymentStatus paymentStatus, LocalDate createdAt, LocalDate pickedUpAt, BigDecimal keptSecurityDeposit, String keptDepositReason, LocalDate returnedAt) {
        this.id = id;
        this.payments = payments;
        this.product = product;
        this.customer = customer;
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

    public List<Payment> getPayments() {
        return payments;
    }

    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
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
