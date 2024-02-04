package com.nextecommerce.commerce.event.handlers;

import com.nextecommerce.commerce.event.events.OrderEvent;
import com.nextecommerce.commerce.services.KafkaProducerService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ApplicationEventHandler {

    private final KafkaProducerService<UUID> kafkaProducerService;

    @EventListener(OrderEvent.class)
    public void onApplicationEvent(OrderEvent event) {
        kafkaProducerService.send(event.getTopic(), event.getEventType(), event);
    }


}
