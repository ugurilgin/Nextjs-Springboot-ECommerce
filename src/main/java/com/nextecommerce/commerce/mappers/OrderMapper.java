package com.nextecommerce.commerce.mappers;

import com.nextecommerce.commerce.dtos.requests.OrderRequestDTO;
import com.nextecommerce.commerce.dtos.responses.OrderResponseDTO;
import com.nextecommerce.commerce.entities.Orders;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = IGNORE)
public interface OrderMapper {

    OrderResponseDTO toResponse(Orders orders);

    Orders toObjFromRequest(OrderRequestDTO request);

    abstract void convert(@MappingTarget Orders orders, OrderRequestDTO request);


}
