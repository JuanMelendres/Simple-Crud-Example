package com.jamv.simplecrud.model;


import jakarta.validation.constraints.NotBlank;
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

    @NotBlank(message = "The product name is required")
    String title;

    String description;

    @NotBlank(message = "The product price is required")
    Double price;

    @NotBlank(message = "The product stock is required")
    Integer stock;

    @NotBlank(message = "The product category is required")
    String category;

    Date createDate;

    Date updateDate;
}
