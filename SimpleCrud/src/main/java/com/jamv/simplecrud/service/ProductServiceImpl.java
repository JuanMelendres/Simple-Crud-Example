package com.jamv.simplecrud.service;

import com.jamv.simplecrud.mappers.ProductMappers;
import com.jamv.simplecrud.model.Product;
import com.jamv.simplecrud.dto.ProductDTO;
import com.jamv.simplecrud.repository.ProductRepository;
import com.jamv.simplecrud.rest.ProductRestClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMappers productMappers;
    private final ProductRestClient productRestClient;

    // Create instance of Random
    Random random = new Random();

    public ProductServiceImpl(
            ProductRepository productRepository,
            ProductMappers productMappers,
            ProductRestClient productRestClient
    ) {
        this.productRepository = productRepository;
        this.productMappers = productMappers;
        this.productRestClient = productRestClient;
    }

    @Override
    public List<ProductDTO> findAll() {
        log.info("Finding all products");

        final List<Product> products = productRepository.findAll();

        final List<ProductDTO> productDTOs = mapProductsToDTOs(products);

        log.info("Products found: {}", productDTOs);

        return productDTOs;
    }

    @Override
    public Optional<ProductDTO> findById(int id) {
        log.info("Finding product with id: {}", id);

        Optional<Product> product = productRepository.findById(id);

        log.info("Product found: {}", product);

        return product.map(this.productMappers::productEntitytToProductDTO);

    }

    @Override
    public void save(ProductDTO product) {

        log.info("Saving product: {}", product);

        productRepository.saveAndFlush(this.productMappers.productDTOToProductEntity(product));

    }

    @Override
    public void update(ProductDTO product) {

        log.info("Updating product: {}", product);

        productRepository.save(this.productMappers.productDTOToProductEntity(product));

    }

    @Override
    public void delete(int id) {

        log.info("Deleting product: {}", id);

        productRepository.deleteById(id);

    }

    @Override
    public void saveRandomProduct() {

        log.info("Saving random product:");

        int randomId = random.nextInt(195);

        ProductDTO randomProduct = productRestClient.getRandomProduct(randomId);

        log.info("Random product: {}", randomProduct);

        productRepository.saveAndFlush(this.productMappers.productDTOToProductEntity(randomProduct));

    }

    private List<ProductDTO> mapProductsToDTOs(List<Product> products) {
        return products.stream()
                .map(productMappers::productEntitytToProductDTO) // Updated method name for correctness
                .toList();
    }
}
