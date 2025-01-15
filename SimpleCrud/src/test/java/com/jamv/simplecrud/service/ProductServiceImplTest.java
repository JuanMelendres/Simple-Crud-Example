package com.jamv.simplecrud.service;

import com.jamv.simplecrud.dto.ProductDTO;
import com.jamv.simplecrud.mappers.ProductMappers;
import com.jamv.simplecrud.model.Product;
import com.jamv.simplecrud.repository.ProductRepository;
import com.jamv.simplecrud.rest.ProductRestClient;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ProductMappers productMappers;

    @Mock
    private ProductRestClient productRestClient;

    @InjectMocks
    private ProductServiceImpl productService;

    public ProductServiceImplTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findAll_ShouldReturnListOfProductDTOs_WhenProductsExist() {
        // Arrange
        List<Product> products = List.of(
                new Product(1L, "Product 1", "Description 1", 10.0, 5, "Category 1", null, null),
                new Product(2L, "Product 2", "Description 2", 20.0, 15, "Category 2", null, null)
        );

        List<ProductDTO> productDTOs = List.of(
                ProductDTO.builder().id(1L).title("Product 1").price(10.0).stock(5).category("Category 1").build(),
                ProductDTO.builder().id(2L).title("Product 2").price(20.0).stock(15).category("Category 2").build()
        );

        when(productRepository.findAll()).thenReturn(products);
        when(productMappers.productEntitytToProductDTO(products.get(0))).thenReturn(productDTOs.get(0));
        when(productMappers.productEntitytToProductDTO(products.get(1))).thenReturn(productDTOs.get(1));

        // Act
        List<ProductDTO> result = productService.findAll();

        // Assert
        assertEquals(productDTOs, result);
        verify(productRepository, times(1)).findAll();
        verify(productMappers, times(1)).productEntitytToProductDTO(products.get(0));
        verify(productMappers, times(1)).productEntitytToProductDTO(products.get(1));
    }

    @Test
    void findAll_ShouldReturnEmptyList_WhenNoProductsExist() {
        // Arrange
        when(productRepository.findAll()).thenReturn(new ArrayList<>());

        // Act
        List<ProductDTO> result = productService.findAll();

        // Assert
        assertEquals(0, result.size());
        verify(productRepository, times(1)).findAll();
        verifyNoInteractions(productMappers);
    }
}