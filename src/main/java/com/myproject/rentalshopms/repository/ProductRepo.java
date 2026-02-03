package com.myproject.rentalshopms.repository;

import com.myproject.rentalshopms.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {
    @Query("SELECT p FROM Product p WHERE p.active = true")
    List<Product> getActiveProducts();

//    @Query("SELECT p FROM Product p WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :keyword, '%')) OR CAST(p.productCode AS string) LIKE CONCAT('%', :keyword, '%')")
//    List<Product> searchProducts(@Param("keyword") String keyword);
}
