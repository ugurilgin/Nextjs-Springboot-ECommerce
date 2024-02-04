package com.nextecommerce.commerce.mappers;

import com.nextecommerce.commerce.dtos.responses.PhotoResponseDTO;
import com.nextecommerce.commerce.entities.Photo;
import org.mapstruct.Mapper;

import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = IGNORE)
public interface PhotoMapper {

    PhotoResponseDTO toResponse(Photo photo);


}
