package com.myproject.rentalshopms.service;

import com.myproject.rentalshopms.dto.CustomerRequestDTO;
import com.myproject.rentalshopms.dto.CustomerResponseDTO;
import com.myproject.rentalshopms.model.Customer;
import com.myproject.rentalshopms.repository.CustomerRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    private final CustomerRepo customerRepo;

    public CustomerService(CustomerRepo customerRepo) {
        this.customerRepo = customerRepo;
    }

    public CustomerResponseDTO mapToResponseDTO(Customer customer) {
        CustomerResponseDTO customerResponseDTO = new CustomerResponseDTO();
        customerResponseDTO.setId(customer.getId());
        customerResponseDTO.setCustomerName(customer.getCustomerName());
        customerResponseDTO.setPhoneNumber(customer.getPhoneNumber());
        customerResponseDTO.setAddress(customer.getAddress());

        return customerResponseDTO;
    }

    public List<CustomerResponseDTO> getCustomers() {
        return customerRepo.findAll()
                .stream().map(this::mapToResponseDTO).toList();
    }

    public CustomerResponseDTO getCustomerById(Long customerId) {
        Customer customer = customerRepo.findById(customerId).orElseThrow(() -> new RuntimeException("Customer not found"));
        return mapToResponseDTO(customer);
    }

    public CustomerResponseDTO addCustomer(CustomerRequestDTO dto) {
        Customer customer = new Customer();
        customer.setCustomerName(dto.getCustomerName());
        customer.setPhoneNumber(dto.getPhoneNumber());
        customer.setAddress(dto.getAddress());

        Customer saved = customerRepo.save(customer);
        return mapToResponseDTO(saved);
    }

    public CustomerResponseDTO updateCustomer(Long customerId, CustomerRequestDTO dto) {
        Customer customer = customerRepo.findById(customerId).orElseThrow(() -> new RuntimeException("Customer not found"));
        customer.setCustomerName(dto.getCustomerName());
        customer.setPhoneNumber(dto.getPhoneNumber());
        customer.setAddress(dto.getAddress());

        Customer updated = customerRepo.save(customer);
        return mapToResponseDTO(updated);
    }

//    public List<CustomerResponseDTO> searchCustomers(String keyword) {
//        return customerRepo.searchCustomers(keyword)
//                .stream().map(this::mapToResponseDTO).toList();
//    }
}
