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

    Long productID;

    @NotBlank(message = "The product name is required")
    String productName;

    String productDescription;

    @NotBlank(message = "The product price is required")
    Double productPrice;

    @NotBlank(message = "The product stock is required")
    Integer productStock;

    @NotBlank(message = "The product category is required")
    String productCategory;

    Date createDate;

    Date updateDate;
}
