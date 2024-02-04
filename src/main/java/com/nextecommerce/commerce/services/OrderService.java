package com.nextecommerce.commerce.services;

import com.nextecommerce.commerce.dtos.requests.OrderRequestDTO;
import com.nextecommerce.commerce.dtos.responses.OrderResponseDTO;

import java.util.List;

public interface OrderService {
     List<OrderResponseDTO> getAllOrders( );
     OrderResponseDTO getOrderById (Long id);
     OrderResponseDTO createOrder (OrderRequestDTO request );
     OrderResponseDTO updateOrder(Long id, OrderRequestDTO request);
     void deleteOrder(Long id);

}
