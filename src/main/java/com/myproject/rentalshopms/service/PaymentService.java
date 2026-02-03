package com.myproject.rentalshopms.service;

import com.myproject.rentalshopms.dto.PaymentRequestDTO;
import com.myproject.rentalshopms.dto.PaymentResponseDTO;
import com.myproject.rentalshopms.enums.BookingStatus;
import com.myproject.rentalshopms.enums.PaymentFlow;
import com.myproject.rentalshopms.enums.PaymentType;
import com.myproject.rentalshopms.enums.PaymentStatus;
import com.myproject.rentalshopms.model.Booking;
import com.myproject.rentalshopms.model.Payment;
import com.myproject.rentalshopms.repository.BookingRepo;
import com.myproject.rentalshopms.repository.PaymentRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class PaymentService {
    private final PaymentRepo paymentRepo;
    private final BookingRepo bookingRepo;

    public PaymentService(PaymentRepo paymentRepo, BookingRepo bookingRepo) {
        this.paymentRepo = paymentRepo;
        this.bookingRepo = bookingRepo;
    }

    public PaymentResponseDTO mapToResponseDTO(Payment payment) {
        PaymentResponseDTO paymentResDTO = new PaymentResponseDTO();
        paymentResDTO.setId(payment.getId());
        paymentResDTO.setBookingId(payment.getBooking().getId());
        paymentResDTO.setPaymentFlow(payment.getPaymentFlow());
        paymentResDTO.setPaymentType(payment.getPaymentType());
        paymentResDTO.setAmount(payment.getAmount());
        paymentResDTO.setPaidAt(payment.getPaidAt());

        return paymentResDTO;
    }

    public void updatePaymentStatus(Booking booking) {
        BigDecimal totalDue = booking.getBookingStatus() == BookingStatus.RETURNED ?
                booking.getTotalAmount().add(booking.getKeptSecurityDeposit())
                :
                booking.getTotalAmount().add(booking.getSecurityDeposit());

        BigDecimal totalCredit = paymentRepo.sumByBookingIdAndFlow(booking.getId(), PaymentFlow.CREDIT);

        BigDecimal totalDebit = paymentRepo.sumByBookingIdAndFlow(booking.getId(), PaymentFlow.DEBIT);

        BigDecimal netPaid = totalCredit.subtract(totalDebit);

        if (netPaid.compareTo(BigDecimal.ZERO) == 0) {
            booking.setPaymentStatus(PaymentStatus.PAYMENT_DUE);
        } else if (netPaid.compareTo(totalDue) < 0) {
            booking.setPaymentStatus(PaymentStatus.PARTIALLY_PAID);
        } else {
            booking.setPaymentStatus(PaymentStatus.PAID);
        }

        bookingRepo.save(booking);
    }

    @Transactional(readOnly = true)
    public List<PaymentResponseDTO> findPaymentsByBookingId(Long bookingId) {
        return paymentRepo.findPaymentsByBookingId(bookingId)
                .stream().map(this::mapToResponseDTO).toList();
    }

    @Transactional
    public PaymentResponseDTO savePayment(PaymentRequestDTO dto) {
        Booking booking = bookingRepo.findById(dto.getBookingId()).orElseThrow(() -> new RuntimeException("Booking not found"));

        if (dto.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new RuntimeException("Amount must be greater than zero");
        }

        Payment payment = new Payment();
        payment.setBooking(booking);
        payment.setPaymentType(dto.getPaymentType());
        payment.setAmount(dto.getAmount());
        payment.setPaidAt(LocalDateTime.now());

        if (dto.getPaymentType() == PaymentType.REFUND) {
            payment.setPaymentFlow(PaymentFlow.DEBIT);
        } else {
            payment.setPaymentFlow(PaymentFlow.CREDIT);
        }

        Payment saved = paymentRepo.save(payment);

        updatePaymentStatus(booking);

        return mapToResponseDTO(saved);
    }

    @Transactional(readOnly = true)
    public Page<PaymentResponseDTO> searchPayments(
            PaymentFlow flow,
            PaymentType type,
            LocalDate fromDate,
            LocalDate toDate,
            Pageable pageable
    ) {
        LocalDateTime fromDateTime = fromDate != null ? fromDate.atStartOfDay() : null;
        LocalDateTime toDateTime = toDate != null ? toDate.atTime(23, 59, 59) : null;

        Page<Payment> page = paymentRepo.searchPayments(flow, type, fromDateTime, toDateTime, pageable);

        return page.map(this::mapToResponseDTO);
    }

//    public BigDecimal sumByBookingIdAndFlow(Long bookingId, PaymentFlow paymentFlow) {
//        return paymentRepo.sumByBookingIdAndFlow(bookingId, paymentFlow);
//    }
//
//    public BigDecimal sumByBookingIdAndFlowType(Long bookingId, PaymentFlow paymentFlow, PaymentType paymentType) {
//        return paymentRepo.sumByBookingIdAndFlowType(bookingId, paymentFlow, paymentType);
//    }
}
