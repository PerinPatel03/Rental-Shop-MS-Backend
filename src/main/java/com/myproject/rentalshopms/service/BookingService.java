package com.myproject.rentalshopms.service;

import com.myproject.rentalshopms.dto.*;
import com.myproject.rentalshopms.enums.BookingStatus;
import com.myproject.rentalshopms.enums.PaymentStatus;
import com.myproject.rentalshopms.model.Booking;
import com.myproject.rentalshopms.model.Customer;
import com.myproject.rentalshopms.model.Product;
import com.myproject.rentalshopms.repository.BookingRepo;
import com.myproject.rentalshopms.repository.CustomerRepo;
import com.myproject.rentalshopms.repository.ProductRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class BookingService {
    private final BookingRepo bookingRepo;
    private final ProductRepo productRepo;
    private final CustomerRepo customerRepo;

    public BookingService(BookingRepo bookingRepo, ProductRepo productRepo, CustomerRepo customerRepo) {
        this.bookingRepo = bookingRepo;
        this.productRepo = productRepo;
        this.customerRepo = customerRepo;
    }

    public BookingResponseDTO mapToResponseDTO(Booking booking) {
        BookingResponseDTO bookResDTO = new BookingResponseDTO();
        bookResDTO.setId(booking.getId());
        bookResDTO.setProductId(booking.getProduct().getId());
        bookResDTO.setCustomerId(booking.getCustomer().getId());
        bookResDTO.setStartDate(booking.getStartDate());
        bookResDTO.setEndDate(booking.getEndDate());
        bookResDTO.setDiscount(booking.getDiscount());
        bookResDTO.setTotalAmount(booking.getTotalAmount());
        bookResDTO.setSecurityDeposit(booking.getSecurityDeposit());
        bookResDTO.setBookingStatus(booking.getBookingStatus());
        bookResDTO.setPaymentStatus(booking.getPaymentStatus());
        bookResDTO.setCreatedAt(booking.getCreatedAt());
        bookResDTO.setPickedUpAt(booking.getPickedUpAt());
        bookResDTO.setKeptSecurityDeposit(booking.getKeptSecurityDeposit());
        bookResDTO.setKeptDepositReason(booking.getKeptDepositReason());
        bookResDTO.setReturnedAt(booking.getReturnedAt());

        return bookResDTO;
    }

    public BigDecimal calculateTotalAmount(long days, BigDecimal pricePerDay, BigDecimal discount) {
        BigDecimal gross = pricePerDay.multiply(BigDecimal.valueOf(days));

        BigDecimal discountAmount = gross
                .multiply(discount)
                .divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);

        return gross.subtract(discountAmount);
    }

    public boolean isProductAvailable(Long prodId, LocalDate startDate, LocalDate endDate) {
        LocalDate today = LocalDate.now();
        if (startDate.isBefore(today)) {
            throw new RuntimeException("Start date cannot be in the past");
        }
        if (endDate.isBefore(startDate)) {
            throw new RuntimeException("End date must be equal to or after start date");
        }

        return bookingRepo.isProductAvailable(prodId, startDate, endDate);
    }

//    public List<BookingResponseDTO> getProductBookings(Long prodId) {
//        return bookingRepo.getProductBookings(prodId)
//                .stream().map(this::mapToResponseDTO).toList();
//    }

    public List<BookingResponseDTO> getBookings() {
        return bookingRepo.findAll()
                .stream().map(this::mapToResponseDTO).toList();
    }

    public BookingResponseDTO getBookingById(Long bookingId) {
        Booking booking = bookingRepo.findById(bookingId).orElseThrow(() -> new RuntimeException("Booking not found"));
        return mapToResponseDTO(booking);
    }

    public List<BookingListDTO> getBookingList() {
        return bookingRepo.findAll()
                .stream().map((booking) -> {
                    BookingListDTO bookingListDTO = new BookingListDTO();
                    bookingListDTO.setId(booking.getId());
                    bookingListDTO.setProductCode(booking.getProduct().getProductCode());
                    bookingListDTO.setProductName(booking.getProduct().getName());
                    bookingListDTO.setCustomerName(booking.getCustomer().getCustomerName());
                    bookingListDTO.setPhoneNumber(booking.getCustomer().getPhoneNumber());
                    bookingListDTO.setStartDate(booking.getStartDate());
                    bookingListDTO.setEndDate(booking.getEndDate());
                    bookingListDTO.setBookingStatus(booking.getBookingStatus());
                    bookingListDTO.setPaymentStatus(booking.getPaymentStatus());

                    return bookingListDTO;
                }).toList();
    }

    @Transactional
    public BookingResponseDTO addBooking(BookingRequestDTO dto) {
        Product product = productRepo.findById(dto.getProductId()).orElseThrow(() -> new RuntimeException("Product not found"));
        Customer customer = customerRepo.findById(dto.getCustomerId()).orElseThrow(() -> new RuntimeException("Customer not found"));

        boolean available = isProductAvailable(product.getId(), dto.getStartDate(), dto.getEndDate());

        if (!available) {
            throw new RuntimeException("Product not available for selected dates");
        }

        // Don't rely on frontend calculated value for totalAmount, Recalculate here...
        long days = ChronoUnit.DAYS.between(dto.getStartDate(), dto.getEndDate()) + 1;
        BigDecimal totalAmount = calculateTotalAmount(days, product.getPricePerDay(), dto.getDiscount());

        Booking booking = new Booking();
        booking.setProduct(product);
        booking.setCustomer(customer);
        booking.setStartDate(dto.getStartDate());
        booking.setEndDate(dto.getEndDate());
        booking.setDiscount(dto.getDiscount());
        booking.setSecurityDeposit(dto.getSecurityDeposit());

        booking.setTotalAmount(totalAmount);
        booking.setBookingStatus(BookingStatus.RESERVED);
        booking.setPaymentStatus(PaymentStatus.PAYMENT_DUE);
        booking.setCreatedAt(LocalDate.now());

        return mapToResponseDTO(bookingRepo.save(booking));
    }

    @Transactional
    public BookingResponseDTO updateBooking(Long bookingId, BookingUpdateRequestDTO dto) {
        Booking booking = bookingRepo.findById(bookingId).orElseThrow(() -> new RuntimeException("Booking not found"));

        Product product = productRepo.findById(booking.getProduct().getId()).orElseThrow(() -> new RuntimeException("Product not found"));

        boolean available = isProductAvailable(product.getId(), dto.getStartDate(), dto.getEndDate());

        if (!available) {
            throw new RuntimeException("Product not available for selected dates");
        }

        // Don't rely on frontend calculated value for totalAmount, Recalculate here...
        long days = ChronoUnit.DAYS.between(dto.getStartDate(), dto.getEndDate()) + 1;
        BigDecimal totalAmount = calculateTotalAmount(days, product.getPricePerDay(), dto.getDiscount());

        booking.setStartDate(dto.getStartDate());
        booking.setEndDate(dto.getEndDate());
        booking.setDiscount(dto.getDiscount());
        booking.setSecurityDeposit(dto.getSecurityDeposit());

        booking.setTotalAmount(totalAmount);

        return mapToResponseDTO(bookingRepo.save(booking));
    }

    @Transactional
    public BookingResponseDTO pickupProduct(Long bookingId) {
        Booking booking = bookingRepo.findById(bookingId).orElseThrow(() -> new RuntimeException("Booking not found"));

        if (booking.getBookingStatus() != BookingStatus.RESERVED) {
            throw new RuntimeException("Product already picked up or booking invalid");
        }

        LocalDate today = LocalDate.now();

        if (today.isBefore(booking.getStartDate())) {
            throw new RuntimeException("Product cannot be picked up before booking start date");
        }

        booking.setBookingStatus(BookingStatus.PICKED_UP);
        booking.setPickedUpAt(LocalDate.now());

        return mapToResponseDTO(bookingRepo.save(booking));
    }

    @Transactional
    public BookingResponseDTO returnProduct(Long bookingId, BookingReturnRequestDTO dto) {
        Booking booking = bookingRepo.findById(bookingId).orElseThrow(() -> new RuntimeException("Booking not found"));

        if (booking.getBookingStatus() != BookingStatus.PICKED_UP) {
            throw new RuntimeException("Product not yet picked up or already returned");
        }

        BigDecimal deposit = booking.getSecurityDeposit() != null ? booking.getSecurityDeposit() : BigDecimal.ZERO;

        BigDecimal keptAmount = dto.getKeptSecurityDeposit() != null ? dto.getKeptSecurityDeposit() : BigDecimal.ZERO;

        if (keptAmount.compareTo(BigDecimal.ZERO) < 0) {
            throw new RuntimeException("Kept security deposit cannot be negative");
        }

        if (keptAmount.compareTo(deposit) > 0) {
            throw new RuntimeException("Kept amount cannot exceed security deposit");
        }

        // Store kept deposit info
        booking.setKeptSecurityDeposit(keptAmount);
        booking.setKeptDepositReason(dto.getKeptDepositReason());

        booking.setBookingStatus(BookingStatus.RETURNED);
        booking.setReturnedAt(LocalDate.now());

        return mapToResponseDTO(bookingRepo.save(booking));
    }
}
