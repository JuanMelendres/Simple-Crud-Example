package com.jamv.simplecrud.rest;

import com.jamv.simplecrud.dto.ProductDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestClient;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.*;

@ExtendWith(MockitoExtension.class)
class ProductRestClientTest {

    private static final String PRODUCT_REST_URL = "https://dummyjson.com/products";

    @InjectMocks
    private ProductRestClient restClient;

    @Mock
    private RestClient restClientMock;

    @Test
    void getRandomProductReturnProductExists() {
        // Arrange
        int testId = 1;

        ProductDTO mockProduct = ProductDTO.builder()
                .id(1L)
                .title("Mock Product")
                .price(100.0)
                .stock(50)
                .category("Mock Category")
                .build();

        MockRestServiceServer server = MockRestServiceServer.createServer(restClientMock);
        server.expect(requestTo(PRODUCT_REST_URL + "/" + testId))
                .andRespond(
                        withSuccess("{\"id\":1,\"title\":\"Mock Product\",\"price\":100.0,\"stock\":50,\"category\":\"Mock Category\"}",
                                MediaType.APPLICATION_JSON));

        // Act
        ProductDTO result = restClient.getRandomProduct(testId);

        // Assert
        assertNotNull(result);
        assertEquals(mockProduct.getId(), result.getId());
        assertEquals(mockProduct.getTitle(), result.getTitle());
        assertEquals(mockProduct.getPrice(), result.getPrice());
        assertEquals(mockProduct.getCategory(), result.getCategory());

        server.verify();
    }

    @Test
    void getRandomProductExceptionNoProductFound() {
        // Arrange
        int testId = 999;

        MockRestServiceServer server = MockRestServiceServer.createServer(restClientMock);
        server.expect(requestTo(PRODUCT_REST_URL + "/" + testId))
                .andRespond(withStatus(HttpStatus.NOT_FOUND));

        // Act
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            restClient.getRandomProduct(testId);
        });

        // Assert
        assertTrue(exception.getMessage().contains("404 NOT_FOUND"));

        server.verify();
    }

    @Test
    void getRandomProductServerError() {
        // Arrange
        int testId = 500;

        MockRestServiceServer server = MockRestServiceServer.createServer(restClientMock);
        server.expect(requestTo(PRODUCT_REST_URL + "/" + testId))
                .andRespond(withServerError());

        // Act
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            restClient.getRandomProduct(testId);
        });

        // Assert
        assertTrue(exception.getMessage().contains("500 Internal Server Error"));

        server.verify();
    }
}