package com.jamv.simplecrud.service;

import com.jamv.simplecrud.model.ProductDTO;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    public List<ProductDTO> findAll();
    public Optional<ProductDTO> findById(int id);
    public void save(ProductDTO product);
    public void update(ProductDTO product);
    public void delete(int id);
    public void saveRandomProduct();

}
