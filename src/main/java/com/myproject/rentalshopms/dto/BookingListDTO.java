package com.myproject.rentalshopms.dto;

import com.myproject.rentalshopms.enums.BookingStatus;
import com.myproject.rentalshopms.enums.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

//@Data
//@AllArgsConstructor
//@NoArgsConstructor
public class BookingListDTO {
    private Long id;

    private Integer productCode;
    private String productName;
    private String customerName;
    private String phoneNumber;

    private LocalDate startDate;
    private LocalDate endDate;
    private BookingStatus bookingStatus;
    private PaymentStatus paymentStatus;

    public BookingListDTO() {
    }

    public BookingListDTO(Long id, Integer productCode, String productName, String customerName, String phoneNumber, LocalDate startDate, LocalDate endDate, BookingStatus bookingStatus, PaymentStatus paymentStatus) {
        this.id = id;
        this.productCode = productCode;
        this.productName = productName;
        this.customerName = customerName;
        this.phoneNumber = phoneNumber;
        this.startDate = startDate;
        this.endDate = endDate;
        this.bookingStatus = bookingStatus;
        this.paymentStatus = paymentStatus;
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

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
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
}
