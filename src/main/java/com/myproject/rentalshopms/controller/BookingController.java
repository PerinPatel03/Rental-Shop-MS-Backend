package com.myproject.rentalshopms.controller;

import com.myproject.rentalshopms.dto.*;
import com.myproject.rentalshopms.service.BookingService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class BookingController {
    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    // "/products/{id}/checkavailability?startdate=${startDate}&enddate=${endDate}"
    @GetMapping("/products/{id}/checkavailability")
    public boolean isProductAvailable(@PathVariable Long id, @RequestParam LocalDate startdate, @RequestParam LocalDate enddate) {
        return bookingService.isProductAvailable(id, startdate, enddate);
    }

//    @GetMapping("/products/{id}/bookings")
//    public ResponseEntity<List<BookingResponseDTO>> getProductBookings(@PathVariable Long id) {
//        return new ResponseEntity<>(bookingService.getProductBookings(id), HttpStatus.OK);
//    }

    @GetMapping("/bookings")
    public ResponseEntity<List<BookingResponseDTO>> getBookings() {
        return new ResponseEntity<>(bookingService.getBookings(), HttpStatus.OK);
    }

    @GetMapping("/bookings/{id}")
    public ResponseEntity<BookingResponseDTO> getBookingById(@PathVariable Long id) {
        return new ResponseEntity<>(bookingService.getBookingById(id), HttpStatus.OK);
    }

    @GetMapping("/bookinglist")
    public ResponseEntity<List<BookingListDTO>> getBookingList() {
        return new ResponseEntity<>(bookingService.getBookingList(), HttpStatus.OK);
    }

    @PostMapping("/addbookings")
    public ResponseEntity<BookingResponseDTO> addBooking(@Valid @RequestBody BookingRequestDTO requestDTO) {
        return new ResponseEntity<>(bookingService.addBooking(requestDTO), HttpStatus.OK);
    }

    @PutMapping("/bookings/{id}/updatebooking")
    public ResponseEntity<BookingResponseDTO> updateBooking(@PathVariable Long id, @Valid @RequestBody BookingUpdateRequestDTO updateRequestDTO) {
        return new ResponseEntity<>(bookingService.updateBooking(id, updateRequestDTO), HttpStatus.OK);
    }

    @PostMapping("/bookings/{id}/pickupproduct")
    public ResponseEntity<BookingResponseDTO> pickupProduct(@PathVariable Long id) {
        return new ResponseEntity<>(bookingService.pickupProduct(id), HttpStatus.OK);
    }

    @PostMapping("/bookings/{id}/returnproduct")
    public ResponseEntity<BookingResponseDTO> returnProduct(@PathVariable Long id, @Valid @RequestBody BookingReturnRequestDTO returnRequestDTO) {
        return new ResponseEntity<>(bookingService.returnProduct(id, returnRequestDTO), HttpStatus.OK);
    }
}
