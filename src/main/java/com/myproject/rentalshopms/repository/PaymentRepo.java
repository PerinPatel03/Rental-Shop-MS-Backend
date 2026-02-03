package com.myproject.rentalshopms.repository;

import com.myproject.rentalshopms.dto.PaymentResponseDTO;
import com.myproject.rentalshopms.enums.PaymentFlow;
import com.myproject.rentalshopms.enums.PaymentType;
import com.myproject.rentalshopms.model.Payment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PaymentRepo extends JpaRepository<Payment, Long> {
    @Query("SELECT p FROM Payment p WHERE p.booking.id = :bookingId ORDER BY p.paidAt ASC")
    List<Payment> findPaymentsByBookingId(@Param("bookingId") Long bookingId);

    @Query("""
        SELECT p FROM Payment p
        WHERE (:flow IS NULL OR p.paymentFlow = :flow)
        AND (:type IS NULL OR p.paymentType = :type)
        AND (CAST(:fromDate AS DATE) IS NULL OR p.paidAt >= :fromDate)
        AND (CAST(:toDate AS DATE) IS NULL OR p.paidAt <= :toDate)
    """)
    Page<Payment> searchPayments(
            @Param("flow") PaymentFlow flow,
            @Param("type") PaymentType type,
            @Param("fromDate") LocalDateTime fromDate,
            @Param("toDate") LocalDateTime toDate,
            Pageable pageable
    );

    @Query("SELECT COALESCE(SUM(p.amount), 0) FROM Payment p WHERE p.paymentFlow = :flow")
    BigDecimal sumByPaymentFlow(@Param("flow") PaymentFlow flow);

    @Query("SELECT COALESCE(SUM(p.amount), 0) FROM Payment p WHERE p.booking.id = :bookingId AND p.paymentFlow = :paymentFlow")
    BigDecimal sumByBookingIdAndFlow(@Param("bookingId") Long bookingId, @Param("paymentFlow") PaymentFlow paymentFlow);

//    @Query("SELECT COALESCE(SUM(p.amount), 0) FROM Payment p WHERE p.booking.id = :bookingId AND p.paymentFlow = :paymentFlow AND p.paymentType = :paymentType")
//    BigDecimal sumByBookingIdAndFlowType(@Param("bookingId") Long bookingId, @Param("paymentFlow") PaymentFlow paymentFlow, @Param("paymentType") PaymentType paymentType);

    @Query("""
        SELECT new com.myproject.rentalshopms.dto.PaymentResponseDTO(
            p.id,
            p.booking.id,
            p.paymentFlow,
            p.paymentType,
            p.amount,
            p.paidAt
        )
        FROM Payment p
        ORDER BY p.paidAt DESC
        """)
    List<PaymentResponseDTO> getRecentPayments(Pageable pageable);
}
