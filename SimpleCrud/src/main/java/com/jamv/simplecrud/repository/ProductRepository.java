package com.jamv.simplecrud.repository;

import com.jamv.simplecrud.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    Optional<Product> findByProductNameIgnoreCase(String name);

    List<Product> findByCategoryIgnoreCase(String category);

    List<Product> findByPriceGreaterThan(double price);

    List<Product> findByPriceLessThan(double price);

    List<Product> findByStockGreaterThan(int stock);

    List<Product> findByStockLessThan(int stock);

}
