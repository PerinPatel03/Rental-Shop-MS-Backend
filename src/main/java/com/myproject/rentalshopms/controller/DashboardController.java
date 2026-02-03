package com.myproject.rentalshopms.controller;

import com.myproject.rentalshopms.dto.PaymentResponseDTO;
import com.myproject.rentalshopms.dto.dashboard.ActiveBookingDTO;
import com.myproject.rentalshopms.dto.dashboard.DashboardSummaryDTO;
import com.myproject.rentalshopms.service.DashboardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class DashboardController {
    private final DashboardService dashboardService;

    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping("/dashboard/summary")
    public ResponseEntity<DashboardSummaryDTO> getSummary() {
        return new ResponseEntity<>(dashboardService.getSummary(), HttpStatus.OK);
    }

    @GetMapping("/dashboard/active-bookings")
    public ResponseEntity<List<ActiveBookingDTO>> getActiveBookings() {
        return new ResponseEntity<>(dashboardService.getActiveBookings(), HttpStatus.OK);
    }

    // "/dashboard/recent-payments?limit=${limit}"
    @GetMapping("/dashboard/recent-payments")
    public ResponseEntity<List<PaymentResponseDTO>> getRecentPayments(@RequestParam(defaultValue = "5") int limit) {
        return new ResponseEntity<>(dashboardService.getRecentPayments(limit), HttpStatus.OK);
    }
}
