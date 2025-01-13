package com.jamv.simplecrud.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

@EqualsAndHashCode(callSuper=false)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Table(name="TBL_PRODUCT")
public class Product  implements Serializable {
    @Id
    @Column(name = "ProductID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqProducts")
    @SequenceGenerator(name = "seqProducts", allocationSize = 1, sequenceName = "SEQ_PRODUCTS")
    Long productID;

    @NonNull @NotBlank(message = "The product name is required")
    String productName;

    String productDescription;

    @NotNull @NotBlank(message = "The product price is required")
    Double productPrice;

    @NonNull @NotBlank(message = "The product stock is required")
    Integer productStock;

    @NonNull @NotBlank(message = "The product category is required")
    String productCategory;

    Date createDate;

    Date updateDate;
}
