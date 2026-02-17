package com.myproject.rentalshopms.repository;

import com.myproject.rentalshopms.dto.dashboard.ActiveBookingDTO;
import com.myproject.rentalshopms.enums.BookingStatus;
import com.myproject.rentalshopms.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookingRepo extends JpaRepository<Booking, Long> {
    @Query("""
            SELECT COUNT(b) = 0 
            FROM Booking b 
            WHERE b.product.id = :prodId 
            AND b.bookingStatus IN (
                com.myproject.rentalshopms.enums.BookingStatus.RESERVED, 
                com.myproject.rentalshopms.enums.BookingStatus.PICKED_UP
            ) 
            AND b.startDate <= :endDate 
            AND b.endDate >= :startDate
            """)
    boolean isProductAvailable(
            @Param("prodId") Long prodId,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate
    );

    // if you want to combine availability checker for both add and update :  AND (:bookingId IS NULL OR b.id <> :bookingId)

    @Query("""
        SELECT COUNT(b) = 0
        FROM Booking b
        WHERE b.product.id = :prodId
        AND b.id <> :bookingId
        AND b.bookingStatus IN (
            com.myproject.rentalshopms.enums.BookingStatus.RESERVED,
            com.myproject.rentalshopms.enums.BookingStatus.PICKED_UP
        )
        AND b.startDate <= :endDate
        AND b.endDate >= :startDate
        """)
    boolean isProductAvailableForUpdate(
            @Param("bookingId") Long bookingId,
            @Param("prodId") Long prodId,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate
    );


//    @Query("SELECT b FROM Booking b WHERE b.product.id = :prodId")
//    List<Booking> getProductBookings(@Param("prodId") Long prodId);

    @Query("SELECT COALESCE(SUM(b.totalAmount), 0) FROM Booking b")
    BigDecimal sumTotalBookingAmount();

    @Query("SELECT COALESCE(SUM(b.keptSecurityDeposit), 0) FROM Booking b")
    BigDecimal sumTotalKeptSecurityDeposit();

    @Query("SELECT COUNT(b) FROM Booking b WHERE b.bookingStatus IN :statuses")
    Long countByBookingStatusIn(@Param("statuses") List<BookingStatus> statuses);

    @Query("""
        SELECT new com.myproject.rentalshopms.dto.dashboard.ActiveBookingDTO(
            b.id,
            b.product.id,
            b.customer.id,
            b.startDate,
            b.endDate,
            b.bookingStatus,
            b.paymentStatus
        )
        FROM Booking b
        WHERE b.bookingStatus IN ('RESERVED','PICKED_UP')
        ORDER BY b.startDate
        """)
    List<ActiveBookingDTO> getActiveBookings();
}
