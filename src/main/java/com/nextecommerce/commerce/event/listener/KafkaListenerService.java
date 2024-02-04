package com.nextecommerce.commerce.event.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nextecommerce.commerce.event.events.OrderEvent;
import com.nextecommerce.commerce.services.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class KafkaListenerService {

    private final OrderService orderService;
    @KafkaListener(topics = "order-create", groupId = "kafka-group-id")
    public void listen(String message) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        OrderEvent event=objectMapper.readValue(message, OrderEvent.class);
        orderService.createOrder(event.getRequest());

    }
}
