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
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final AddressRepository addressRepository;
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
    @Transactional
    public OrderResponseDTO createOrder(OrderRequestDTO request) {

        Orders orders  = orderMapper.toObjFromRequest(request);
        Address address = addressRepository.findById(request.getAddress().getId()).orElse(null);
        if(address==null)
        {
            addressRepository.save(request.getAddress());
        }
        Orders response = orderRepository.save(orders);

        return orderMapper.toResponse(response);

    }

    @Override
    @Transactional
    public OrderResponseDTO updateOrder(Long id, OrderRequestDTO request) {

        Orders orders = orderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Orders not found with id " + id ));

        orderMapper.convert(orders,request);

        Orders response = orderRepository.save(orders);

        return orderMapper.toResponse(response);

    }

    @Override
    @Transactional
    public void deleteOrder(Long id) {

        Orders orders = orderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Orders not found with id " + id ));

        orders.setIsDeleted(true);
        orderRepository.save(orders);

    }
}
