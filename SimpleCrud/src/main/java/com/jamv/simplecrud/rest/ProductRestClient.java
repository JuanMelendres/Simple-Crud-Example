package com.jamv.simplecrud.rest;

import com.jamv.simplecrud.dto.ProductDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.client.RestClient;

@Slf4j
@Configuration
public class ProductRestClient {

    private static final String PRODUCT_REST_URL = "https://dummyjson.com/products";

    private final RestClient restClient;

    public ProductRestClient() {
        this.restClient = RestClient.builder()
                .baseUrl(PRODUCT_REST_URL)
                .build();
    }

    public ProductDTO getRandomProduct(int id) {
        return restClient.get()
                .uri("/{id}", id)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError,
                        (req, res) -> {
                            log.error("Client Error: {}", res.getStatusCode());
                            log.error("Client Error: {}", res.getStatusText());
                        })
                .onStatus(HttpStatusCode::is5xxServerError,
                (req, res) -> {
                    log.error("Server error: {}", res.getStatusCode());
                    log.error("Server error: {}", res.getStatusText());
                })
                .onStatus(HttpStatusCode::is2xxSuccessful,
                (req, res) -> {
                    log.info("Successfully got product with id: {}", id);
                })
                .body(ProductDTO.class);
    }
}
