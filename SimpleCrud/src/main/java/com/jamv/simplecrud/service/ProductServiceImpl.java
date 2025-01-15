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

        Product newProduct = this.productMappers.productDTOToProductEntity(product);

        productRepository.saveAndFlush(newProduct);

        log.info("Saved product: {}", newProduct);

    }

    @Override
    public void update(ProductDTO product) {

        log.info("Updating product: {}", product);

        Product updatedProduct = this.productMappers.productDTOToProductEntity(product);

        productRepository.save(updatedProduct);

        log.info("Updated product: {}", updatedProduct);

    }

    @Override
    public void delete(int id) {

        log.info("Deleting product: {}", id);

        productRepository.deleteById(id);

    }

    @Override
    public void saveRandomProduct() {

        log.info("Saving random product");

        int randomId = random.nextInt(195);

        ProductDTO randomProduct = productRestClient.getRandomProduct(randomId);

        log.info("Random product: {}", randomProduct);

        Product newProduct = this.productMappers.productDTOToProductEntity(randomProduct);

        productRepository.saveAndFlush(newProduct);

        log.info("Saved Random product: {}", newProduct);

    }

    @Override
    public Optional<ProductDTO> findByName(String name) {
        log.info("Finding product with name: {}", name);
        Optional<Product> product = productRepository.findByProductNameIgnoreCase(name);

        log.info("Product found: {}", product);

        return product.map(this.productMappers::productEntitytToProductDTO);
    }

    @Override
    public List<ProductDTO> findByCategory(String category) {
        log.info("Finding products in category: {}", category);

        List<Product> products = productRepository.findByCategoryIgnoreCase(category);

        List<ProductDTO> productDTOs = mapProductsToDTOs(products);

        log.info("Products found: {}", productDTOs);

        return productDTOs;
    }

    @Override
    public List<ProductDTO> findByPriceGreaterThan(double price) {
        log.info("Finding products with price greater than: {}", price);

        List<Product> products = productRepository.findByPriceGreaterThan(price);

        List<ProductDTO> productDTOs = mapProductsToDTOs(products);

        log.info("Products found: {}", productDTOs);

        return productDTOs;
    }

    @Override
    public List<ProductDTO> findByPriceLessThan(double price) {
        log.info("Finding products with price less than: {}", price);

        List<Product> products = productRepository.findByPriceLessThan(price);

        List<ProductDTO> productDTOs = mapProductsToDTOs(products);

        log.info("Products found: {}", productDTOs);

        return productDTOs;
    }

    @Override
    public List<ProductDTO> findByStockGreaterThan(int stock) {
        log.info("Finding products with stock greater than: {}", stock);

        List<Product> products = productRepository.findByStockGreaterThan(stock);

        List<ProductDTO> productDTOs = mapProductsToDTOs(products);

        log.info("Products found: {}", productDTOs);

        return productDTOs;
    }

    @Override
    public List<ProductDTO> findByStockLessThan(int stock) {
        log.info("Finding products with stock less than: {}", stock);

        List<Product> products = productRepository.findByStockLessThan(stock);

        List<ProductDTO> productDTOs = mapProductsToDTOs(products);

        log.info("Products found: {}", productDTOs);

        return productDTOs;
    }

    /**
     * Maps a list of Product entities to a list of ProductDTOs.
     *
     * @param products the list of Product entities to be mapped
     * @return a list of ProductDTOs containing the mapped data
     */
    private List<ProductDTO> mapProductsToDTOs(List<Product> products) {
        return products.stream()
                .map(productMappers::productEntitytToProductDTO) // Updated method name for correctness
                .toList();
    }
}
