package com.nextecommerce.commerce.services;


import com.nextecommerce.commerce.enums.EventType;
import com.nextecommerce.commerce.event.events.BaseEvent;

public interface KafkaProducerService<T> {

    void send(String topic, EventType eventType, BaseEvent<T> event);

}
