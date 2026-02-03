package com.myproject.rentalshopms.controller;

import com.myproject.rentalshopms.dto.CustomerRequestDTO;
import com.myproject.rentalshopms.dto.CustomerResponseDTO;
import com.myproject.rentalshopms.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/customers")
    public ResponseEntity<List<CustomerResponseDTO>> getCustomers() {
        return new ResponseEntity<>(customerService.getCustomers(), HttpStatus.OK);
    }

    @GetMapping("/customers/{id}")
    public ResponseEntity<CustomerResponseDTO> getCustomerById(@PathVariable Long id) {
        return new ResponseEntity<>(customerService.getCustomerById(id), HttpStatus.OK);
    }

    @PostMapping("/addcustomer")
    public ResponseEntity<CustomerResponseDTO> addCustomer(@Valid @RequestBody CustomerRequestDTO requestDTO) {
        return new ResponseEntity<>(customerService.addCustomer(requestDTO), HttpStatus.OK);
    }

    @PutMapping("/updatecustomer/{id}")
    public ResponseEntity<CustomerResponseDTO> updateCustomer(@PathVariable Long id, @Valid @RequestBody CustomerRequestDTO updatedRequestDTO) {
        return new ResponseEntity<>(customerService.updateCustomer(id, updatedRequestDTO), HttpStatus.OK);
    }

    // "/customers/search?keyword=${keyword}"
//    @GetMapping("/customers/search")
//    public ResponseEntity<List<CustomerResponseDTO>> searchCustomers(@RequestParam String keyword) {
//        return new ResponseEntity<>(customerService.searchCustomers(keyword), HttpStatus.OK);
//    }
}
