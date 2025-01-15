package com.jamv.simplecrud.controller;

import com.jamv.simplecrud.dto.ProductDTO;
import com.jamv.simplecrud.exception.ProductBadRequestException;
import com.jamv.simplecrud.service.ProductServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.List;

@ExtendWith(MockitoExtension.class)
class ProductControllerTest {

    private final ProductDTO productDTOA = ProductDTO.builder()
            .id(1L)
            .title("productA")
            .description("productA description")
            .price(100.0)
            .category("category")
            .stock(10)
            .build();

    private final ProductDTO productDTOB = ProductDTO.builder()
            .id(1L)
            .title("productB")
            .description("productB description")
            .price(100.0)
            .category("category")
            .stock(10)
            .build();

    private final List<ProductDTO> productDTOList = List.of(productDTOA, productDTOB);

    @InjectMocks
    private ProductController productController;

    @Mock
    private ProductServiceImpl productService;

    @BeforeAll
    public static void beforeAll() {
        MockitoAnnotations.openMocks(ProductControllerTest.class);
    }

    @Test
    void getAllProductsOk() {
        Mockito.when(productService.findAll()).thenReturn(productDTOList);

        ResponseEntity<List<ProductDTO>> response = productController.getAllProducts();

        Mockito.verify(productService, Mockito.times(1)).findAll();

        Assertions.assertEquals(200, response.getStatusCode().value());
        Assertions.assertEquals(productDTOList, response.getBody());
    }

    @Test
    void getAllProductsBadRequestException() {
        Mockito.when(productService.findAll()).thenThrow(new ProductBadRequestException("Bad Request"));

        ResponseEntity<List<ProductDTO>> response = productController.getAllProducts();

        Mockito.verify(productService, Mockito.times(1)).findAll();

        Assertions.assertEquals(400, response.getStatusCode().value());
        Assertions.assertNull(response.getBody());
    }


}