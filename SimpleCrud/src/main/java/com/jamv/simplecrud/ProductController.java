package com.jamv.simplecrud;

import com.jamv.simplecrud.exception.ProductBadRequestException;
import com.jamv.simplecrud.exception.ProductNotFoundException;
import com.jamv.simplecrud.dto.ProductDTO;
import com.jamv.simplecrud.service.ProductService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/products")
@Slf4j
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        try {
            List<ProductDTO> products = productService.findAll();
            log.info("Found {} products", products.size());
            return ResponseEntity.status(HttpStatus.OK).body(products);
        } catch (ProductBadRequestException e) {
            log.error("Bad request: {}", e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable int id){
        try {
            ProductDTO product = productService.findById(id).orElseThrow(() -> new ProductNotFoundException("Product not found"));
            log.info("Found product: {}", product);
            return ResponseEntity.status(HttpStatus.OK).body(product);
        } catch (ProductBadRequestException e) {
            log.error("Bad request: {}", e.getMessage());
            return ResponseEntity.badRequest().build();
        } catch (ProductNotFoundException e) {
            log.error("Product not found: {}", e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<ProductDTO> saveProduct(@Valid @RequestBody ProductDTO product) {
        try {
            productService.save(product);
            log.info("Saved product: {}", product);
            return ResponseEntity.status(HttpStatus.CREATED).body(product);
        } catch (ProductBadRequestException e) {
            log.error("Bad request: {}", e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable int id, @Valid @RequestBody ProductDTO product) {
        try {
            if (productService.findById(id).isEmpty()) {
                log.error("Product not found");
                return ResponseEntity.notFound().build();
            }
            productService.update(product);
            log.info("Updated product with id: {}", id);
            return ResponseEntity.status(HttpStatus.OK).body(product);
        } catch (ProductBadRequestException e) {
            log.error("Bad request: {}", e.getMessage());
            return ResponseEntity.badRequest().build();
        } catch (ProductNotFoundException e) {
            log.error("Product not found: {}", e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ProductDTO> deleteProduct(@PathVariable int id) {
        try {
            productService.delete(id);
            log.info("Deleted product with id: {}", id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (ProductBadRequestException e) {
            log.error("Bad request: {}", e.getMessage());
            return ResponseEntity.badRequest().build();
        } catch (ProductNotFoundException e) {
            log.error("Product not found: {}", e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/randomProduct")
    public ResponseEntity<ProductDTO> saveRandomProduct() {
        try {
            productService.saveRandomProduct();
            log.info("Saved random product");
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (ProductBadRequestException e) {
            log.error("Bad request: {}", e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }
}
