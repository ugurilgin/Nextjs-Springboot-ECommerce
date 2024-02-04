package com.nextecommerce.commerce.event.publisher;

import com.nextecommerce.commerce.enums.EventType;
import com.nextecommerce.commerce.event.events.OrderEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class EventPublisher {

    private final ApplicationEventPublisher applicationEventPublisher;

    public void publishApplicationEvent(String topic, EventType eventType, OrderEvent event) {
        event.setKey(UUID.randomUUID());
        event.setTopic(topic);
        event.setEventType(eventType);
        applicationEventPublisher.publishEvent(event);
    }

}
