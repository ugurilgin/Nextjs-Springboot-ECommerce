package com.nextecommerce.commerce.mappers;

import com.nextecommerce.commerce.dtos.requests.ProductRequestDTO;
import com.nextecommerce.commerce.dtos.responses.ProductResponseDTO;
import com.nextecommerce.commerce.entities.Products;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = IGNORE)
public interface ProductMapper {

    ProductResponseDTO toResponse(Products products);

    Products toObjFromRequest(ProductRequestDTO request);

    abstract void convert(@MappingTarget Products products, ProductRequestDTO request);


}
