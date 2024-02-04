package com.nextecommerce.commerce.mappers;

import com.nextecommerce.commerce.dtos.requests.RatingRequestDTO;
import com.nextecommerce.commerce.dtos.responses.RatingResponseDTO;
import com.nextecommerce.commerce.entities.Ratings;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = IGNORE)
public interface RatingMapper {

    RatingResponseDTO toResponse(Ratings ratings);

    Ratings toObjFromRequest(RatingRequestDTO request);

    abstract void convert(@MappingTarget Ratings ratings, RatingRequestDTO request);


}
