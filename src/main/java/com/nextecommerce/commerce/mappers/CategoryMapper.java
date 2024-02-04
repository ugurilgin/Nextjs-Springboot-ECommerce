package com.nextecommerce.commerce.mappers;

import com.nextecommerce.commerce.dtos.requests.CategoryRequestDTO;
import com.nextecommerce.commerce.dtos.responses.CategoryResponseDTO;
import com.nextecommerce.commerce.entities.Categories;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = IGNORE)
public interface CategoryMapper {

    CategoryResponseDTO toResponse(Categories categories);

    Categories toObjFromRequest(CategoryRequestDTO request);

    abstract void convert(@MappingTarget Categories categories, CategoryRequestDTO request);


}
