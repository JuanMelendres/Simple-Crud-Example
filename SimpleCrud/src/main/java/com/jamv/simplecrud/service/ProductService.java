package com.jamv.simplecrud.service;

import com.jamv.simplecrud.dto.ProductDTO;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<ProductDTO> findAll();
    Optional<ProductDTO> findById(int id);
    void save(ProductDTO product);
    void update(ProductDTO product);
    void delete(int id);
    void saveRandomProduct();
    Optional<ProductDTO> findByName(String name);
    List<ProductDTO> findByCategory(String category);
    List<ProductDTO> findByPriceGreaterThan(double price);
    List<ProductDTO> findByPriceLessThan(double price);
    List<ProductDTO> findByStockGreaterThan(int stock);
    List<ProductDTO> findByStockLessThan(int stock);
}
