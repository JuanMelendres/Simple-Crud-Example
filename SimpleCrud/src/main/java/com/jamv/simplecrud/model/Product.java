package com.jamv.simplecrud.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@EqualsAndHashCode(callSuper=false)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Table(name="TBL_PRODUCT")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PRODUCT_ID", nullable = false, updatable = false)
    private Long productID;

    @NonNull @NotBlank(message = "The product name is required")
    @Column(name = "PRODUCT_NAME")
    private String productName;

    @Column(name = "PRODUCT_DESCRIPTION")
    private String productDescription;

    @NotNull(message = "The product price is required")
    @Column(name = "PRODUCT_PRICE")
    private Double productPrice;

    @NotNull(message = "The product stock is required")
    @Column(name = "PRODUCT_STOCK")
    private Integer productStock;

    @NotNull(message = "The product category is required")
    @Column(name = "PRODUCT_CATEGORY")
    private String productCategory;

    @Column(name = "CREATED_AT", updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "UPDATED_AT")
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
