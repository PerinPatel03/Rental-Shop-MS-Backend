package com.myproject.rentalshopms.service;

import com.myproject.rentalshopms.dto.PaymentResponseDTO;
import com.myproject.rentalshopms.dto.dashboard.ActiveBookingDTO;
import com.myproject.rentalshopms.dto.dashboard.DashboardSummaryDTO;
import com.myproject.rentalshopms.enums.BookingStatus;
import com.myproject.rentalshopms.enums.PaymentFlow;
import com.myproject.rentalshopms.repository.BookingRepo;
import com.myproject.rentalshopms.repository.PaymentRepo;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class DashboardService {
    private final BookingRepo bookingRepo;
    private final PaymentRepo paymentRepo;

    public DashboardService(BookingRepo bookingRepo, PaymentRepo paymentRepo) {
        this.bookingRepo = bookingRepo;
        this.paymentRepo = paymentRepo;
    }

    public DashboardSummaryDTO getSummary() {
        // TODO:

        BigDecimal totalCredit = paymentRepo.sumByPaymentFlow(PaymentFlow.CREDIT);
        BigDecimal totalDebit = paymentRepo.sumByPaymentFlow(PaymentFlow.DEBIT);
        BigDecimal netPaid = totalCredit.subtract(totalDebit);

        BigDecimal sumTotalBookingAmount = bookingRepo.sumTotalBookingAmount();
        BigDecimal sumTotalKeptSecurityDeposit = bookingRepo.sumTotalKeptSecurityDeposit();

        BigDecimal totalRevenue = sumTotalBookingAmount != null ? sumTotalBookingAmount : BigDecimal.ZERO;

        BigDecimal totalSecurityDepositHeld = sumTotalKeptSecurityDeposit != null ? sumTotalKeptSecurityDeposit : BigDecimal.ZERO;

        BigDecimal totalDue = totalRevenue.subtract(netPaid.subtract(totalSecurityDepositHeld));

        Long activeRentals = bookingRepo.countByBookingStatusIn(List.of(BookingStatus.RESERVED, BookingStatus.PICKED_UP));

//        return new DashboardSummaryDTO(totalRevenue, totalSecurityDepositHeld, totalDue, activeRentals);

        DashboardSummaryDTO dto = new DashboardSummaryDTO();
        dto.setTotalRevenue(totalRevenue);
        dto.setTotalSecurityDepositHeld(totalSecurityDepositHeld);
        dto.setTotalDue(totalDue);
        dto.setActiveRentals(activeRentals);
        return dto;
    }

    public List<ActiveBookingDTO> getActiveBookings() {
        return bookingRepo.getActiveBookings();
    }

    public List<PaymentResponseDTO> getRecentPayments(int limit) {
        return paymentRepo.getRecentPayments(PageRequest.of(0, limit));
    }
}
