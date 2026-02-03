package com.myproject.rentalshopms.repository;

import com.myproject.rentalshopms.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Long> {
//    @Query("SELECT c FROM Customer c WHERE LOWER(c.customerName) LIKE LOWER(CONCAT('%', :keyword, '%')) OR CAST(c.phoneNumber AS string) LIKE CONCAT('%', :keyword, '%')")
//    List<Customer> searchCustomers(@Param("keyword") String keyword);
}
