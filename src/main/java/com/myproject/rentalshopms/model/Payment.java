package com.myproject.rentalshopms.model;

import com.myproject.rentalshopms.enums.PaymentFlow;
import com.myproject.rentalshopms.enums.PaymentType;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(
            name = "bookingId",
            referencedColumnName = "id",
            nullable = false
    )
    private Booking booking;

    @Enumerated(EnumType.STRING)
    private PaymentFlow paymentFlow;

    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;

    private BigDecimal amount;
    private LocalDateTime paidAt;

    public Payment() {
    }

    public Payment(Long id, Booking booking, PaymentFlow paymentFlow, PaymentType paymentType, BigDecimal amount, LocalDateTime paidAt) {
        this.id = id;
        this.booking = booking;
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

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
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
