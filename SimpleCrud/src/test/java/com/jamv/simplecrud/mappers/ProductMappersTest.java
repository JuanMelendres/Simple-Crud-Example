package com.jamv.simplecrud.mappers;

import com.jamv.simplecrud.dto.ProductDTO;
import com.jamv.simplecrud.model.Product;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class ProductMappersTest {

    private final ProductMappers productMappers = Mappers.getMapper(ProductMappers.class);

    /**
     * Test to verify all fields are correctly mapped from ProductDTO to Product.
     */
    @Test
    void productDTOToProductEntity_shouldMapAllFieldsCorrectly() {
        // Arrange
        ProductDTO productDTO = ProductDTO.builder()
                .id(1L)
                .title("Sample Product")
                .description("Sample Description")
                .price(99.99)
                .stock(10)
                .category("Sample Category")
                .build();

        // Act
        Product product = productMappers.productDTOToProductEntity(productDTO);

        // Assert
        assertEquals(productDTO.getId(), product.getProductID());
        assertEquals(productDTO.getTitle(), product.getProductName());
        assertEquals(productDTO.getDescription(), product.getProductDescription());
        assertEquals(productDTO.getPrice(), product.getProductPrice());
        assertEquals(productDTO.getStock(), product.getProductStock());
        assertEquals(productDTO.getCategory(), product.getProductCategory());
        assertNull(product.getCreatedAt());
        assertNull(product.getUpdatedAt());
    }

    /**
     * Test to verify that mapping works correctly when ProductDTO contains null fields.
     */
    @Test
    void productDTOToProductEntity_shouldHandleNullFields() {
        // Arrange
        ProductDTO productDTO = ProductDTO.builder()
                .id(null)
                .title(null)
                .description(null)
                .price(null)
                .stock(null)
                .category(null)
                .build();

        // Act
        Product product = productMappers.productDTOToProductEntity(productDTO);

        // Assert
        assertNull(product.getProductID());
        assertNull(product.getProductName());
        assertNull(product.getProductDescription());
        assertNull(product.getProductPrice());
        assertNull(product.getProductStock());
        assertNull(product.getProductCategory());
        assertNull(product.getCreatedAt());
        assertNull(product.getUpdatedAt());
    }
}