package com.jamv.simplecrud.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductDTO {

    Long id;

    @NotNull(message = "The product name is required")
    String title;

    String description;

    @NotNull(message = "The product price is required")
    Double price;

    @NotNull(message = "The product stock is required")
    Integer stock;

    @NotNull(message = "The product category is required")
    String category;

    Date createdAt;

    Date updatedAt;
}
