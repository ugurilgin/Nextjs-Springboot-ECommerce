package com.nextecommerce.commerce.services.impl;

import com.nextecommerce.commerce.dtos.requests.OrderRequestDTO;
import com.nextecommerce.commerce.dtos.responses.OrderResponseDTO;
import com.nextecommerce.commerce.entities.Address;
import com.nextecommerce.commerce.entities.Orders;
import com.nextecommerce.commerce.exceptions.EntityNotFoundException;
import com.nextecommerce.commerce.mappers.OrderMapper;
import com.nextecommerce.commerce.repositories.AddressRepository;
import com.nextecommerce.commerce.repositories.OrderRepository;
import com.nextecommerce.commerce.services.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    @Override
    public List<OrderResponseDTO> getAllOrders() {

        List<Orders> orders = orderRepository.findAll();

        return orders.stream().map(orderMapper::toResponse).collect(Collectors.toList());
    }

    @Override
    public OrderResponseDTO getOrderById(Long id) {

        Orders orders=orderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Order not found with id " + id ));

        return orderMapper.toResponse(orders);

    }

    @Override
    public OrderResponseDTO createOrder(OrderRequestDTO request) {

        Orders orders  = orderMapper.toObjFromRequest(request);
        Orders response = orderRepository.save(orders);

        return orderMapper.toResponse(response);

    }

    @Override
    public OrderResponseDTO updateOrder(Long id, OrderRequestDTO request) {

        Orders orders = orderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Orders not found with id " + id ));

        orderMapper.convert(orders,request);

        Orders response = orderRepository.save(orders);

        return orderMapper.toResponse(response);

    }

    @Override
    public void deleteOrder(Long id) {

        Orders orders = orderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Orders not found with id " + id ));

        orders.setIsDeleted(true);
        orderRepository.save(orders);

    }
}
