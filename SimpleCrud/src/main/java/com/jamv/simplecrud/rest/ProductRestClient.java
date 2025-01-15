package com.jamv.simplecrud.rest;

import com.jamv.simplecrud.dto.ProductDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Slf4j
@Configuration
public class ProductRestClient {

    private static final String PRODUCT_REST_URL = "https://dummyjson.com/products";

    private final WebClient webClient;

    public ProductRestClient() {
        this.webClient = WebClient.builder()
                .baseUrl(PRODUCT_REST_URL)
                .build();
    }

    public ProductDTO getRandomProduct(int id) {
        return webClient.get()
                .uri("/{id}", id)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError,
                        error -> Mono.error(new RuntimeException("API not found")))
                .onStatus(HttpStatusCode::is5xxServerError,
                        error -> Mono.error(new RuntimeException("Server is not responding")))
                .bodyToMono(ProductDTO.class).block();
    }
}
