package com.myproject.rentalshopms.dto;

import com.myproject.rentalshopms.enums.PaymentType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

//@Data
//@AllArgsConstructor
//@NoArgsConstructor
public class PaymentRequestDTO {
    @NotNull(message = "Booking id is required")
    private Long bookingId;
    @NotNull(message = "Payment reason is required")
    private PaymentType paymentType;
    @NotNull(message = "Amount is required")
    @Positive(message = "Amount must be greater than 0")
    private BigDecimal amount;

    public PaymentRequestDTO() {
    }

    public PaymentRequestDTO(@NotNull(message = "Booking id is required") Long bookingId, @NotNull(message = "Payment reason is required") PaymentType paymentType, @NotNull(message = "Amount is required") BigDecimal amount) {
        this.bookingId = bookingId;
        this.paymentType = paymentType;
        this.amount = amount;
    }

    public Long getBookingId() {
        return bookingId;
    }

    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
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
}
