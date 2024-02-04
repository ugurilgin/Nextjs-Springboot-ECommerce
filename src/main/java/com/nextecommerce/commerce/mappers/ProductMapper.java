package com.nextecommerce.commerce.mappers;

import com.nextecommerce.commerce.dtos.requests.ProductRequestDTO;
import com.nextecommerce.commerce.dtos.responses.ProductResponseDTO;
import com.nextecommerce.commerce.entities.Categories;
import com.nextecommerce.commerce.entities.Products;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = IGNORE)
public interface ProductMapper {

    @Mapping(target = "category", expression = "java(mapCategoriesToStrings(products.getCategory()))")
    ProductResponseDTO toResponse(Products products);

    Products toObjFromRequest(ProductRequestDTO request);

    abstract void convert(@MappingTarget Products products, ProductRequestDTO request);

    default List<String> mapCategoriesToStrings(Set<Categories> categories) {
        return categories.stream()
                .map(category -> category.getCategory())
                .collect(Collectors.toList());
    }

}
