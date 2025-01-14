package com.jamv.simplecrud.mappers;

import com.jamv.simplecrud.model.Product;
import com.jamv.simplecrud.dto.ProductDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProductMappers {

    ProductMappers INSTANCE = Mappers.getMapper(ProductMappers.class);

    @Mapping(source = "productID", target = "id")
    @Mapping(source = "productName", target = "title")
    @Mapping(source = "productDescription", target = "description")
    @Mapping(source = "productPrice", target = "price")
    @Mapping(source = "productStock", target = "stock")
    @Mapping(source = "productCategory", target = "category")
    ProductDTO productEntitytToProductDTO(Product product);

    @Mapping(source = "id", target = "productID")
    @Mapping(source = "title", target = "productName")
    @Mapping(source = "description", target = "productDescription")
    @Mapping(source = "price", target = "productPrice")
    @Mapping(source = "stock", target = "productStock")
    @Mapping(source = "category", target = "productCategory")
    Product productDTOToProductEntity(ProductDTO productDTO);
}
