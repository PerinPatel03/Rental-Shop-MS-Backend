package com.myproject.rentalshopms.controller;

import com.myproject.rentalshopms.dto.PaymentRequestDTO;
import com.myproject.rentalshopms.dto.PaymentResponseDTO;
import com.myproject.rentalshopms.enums.PaymentFlow;
import com.myproject.rentalshopms.enums.PaymentType;
import com.myproject.rentalshopms.service.PaymentService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class PaymentController {
    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping("/bookings/{id}/payments")
    public ResponseEntity<List<PaymentResponseDTO>> findPaymentsByBookingId(@PathVariable Long id) {
        return new ResponseEntity<>(paymentService.findPaymentsByBookingId(id), HttpStatus.OK);
    }

    @PostMapping("/bookings/savepayment")
    public ResponseEntity<PaymentResponseDTO> savePayment(@Valid @RequestBody PaymentRequestDTO paymentReqDTO) {
        return new ResponseEntity<>(paymentService.savePayment(paymentReqDTO), HttpStatus.OK);
    }

    // "/payments?flow=CREDIT&type=RENTAL&fromDate=2026-01-01&toDate=2026-02-01&page=0&size=20"
    // "/payments?flow=${flow}&type=${type}&fromdate=${fromDate}&todate=${toDate}&page=${page}&size=${size}"
    @GetMapping("/payments")
    public ResponseEntity<Page<PaymentResponseDTO>> searchPayments(
            @RequestParam(required = false) PaymentFlow flow,
            @RequestParam(required = false) PaymentType type,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fromdate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate todate,
            Pageable pageable
    ) {
        return new ResponseEntity<>(paymentService.searchPayments(flow, type, fromdate, todate, pageable), HttpStatus.OK);
    }

    // "/bookings/{id}/payments/sumbyif?paymentflow=${paymentFlow}"
//    @GetMapping("/bookings/{id}/sumbyif")
//    public ResponseEntity<BigDecimal> sumByBookingIdAndFlow(@PathVariable Long id, @RequestParam PaymentFlow paymentFlow) {
//        return new ResponseEntity<>(paymentService.sumByBookingIdAndFlow(id, paymentFlow), HttpStatus.OK);
//    }

    // "/bookings/{id}/payments/sumbyifr?paymentflow=${paymentFlow}&paymenttype=${paymentType}"
//    @GetMapping("/bookings/{id}/sumbyifr")
//    public ResponseEntity<BigDecimal> sumByBookingIdAndFlowType(@PathVariable Long id, @RequestParam PaymentFlow paymentFlow, @RequestParam PaymentType paymentType) {
//        return new ResponseEntity<>(paymentService.sumByBookingIdAndFlowType(id, paymentFlow, paymentType), HttpStatus.OK);
//    }
}
