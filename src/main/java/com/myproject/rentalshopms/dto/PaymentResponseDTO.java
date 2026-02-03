package com.myproject.rentalshopms.dto;

import com.myproject.rentalshopms.enums.PaymentFlow;
import com.myproject.rentalshopms.enums.PaymentType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

//@Data
//@AllArgsConstructor
//@NoArgsConstructor
public class PaymentResponseDTO {
    private Long id;
    private Long bookingId;
    private PaymentFlow paymentFlow;
    private PaymentType paymentType;
    private BigDecimal amount;
    private LocalDateTime paidAt;

    public PaymentResponseDTO() {
    }

    public PaymentResponseDTO(Long id, Long bookingId, PaymentFlow paymentFlow, PaymentType paymentType, BigDecimal amount, LocalDateTime paidAt) {
        this.id = id;
        this.bookingId = bookingId;
        this.paymentFlow = paymentFlow;
        this.paymentType = paymentType;
        this.amount = amount;
        this.paidAt = paidAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBookingId() {
        return bookingId;
    }

    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
    }

    public PaymentFlow getPaymentFlow() {
        return paymentFlow;
    }

    public void setPaymentFlow(PaymentFlow paymentFlow) {
        this.paymentFlow = paymentFlow;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDateTime getPaidAt() {
        return paidAt;
    }

    public void setPaidAt(LocalDateTime paidAt) {
        this.paidAt = paidAt;
    }
}
