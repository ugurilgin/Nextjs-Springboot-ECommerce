package com.nextecommerce.commerce.mappers;

import com.nextecommerce.commerce.dtos.requests.AddressRequestDTO;
import com.nextecommerce.commerce.dtos.responses.AddressResponseDTO;
import com.nextecommerce.commerce.entities.Address;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = IGNORE)
public interface AddressMapper {

    AddressResponseDTO toResponse(Address address);

    Address toObjFromRequest(AddressRequestDTO request);

    abstract void convert(@MappingTarget Address address, AddressRequestDTO request);


}
