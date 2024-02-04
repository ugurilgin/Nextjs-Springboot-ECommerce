package com.nextecommerce.commerce.services.impl;


import com.nextecommerce.commerce.enums.EventType;
import com.nextecommerce.commerce.event.events.BaseEvent;
import com.nextecommerce.commerce.services.KafkaProducerService;
import com.nextecommerce.commerce.utils.ObjectMapperUtil;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.function.BiConsumer;

@Service
@Slf4j
@RequiredArgsConstructor
public class KafkaProducerServiceImpl<T> implements KafkaProducerService<T> {
    
    private final KafkaTemplate<String, String> kafkaTemplate;

    public void send(String topic, EventType eventType, BaseEvent<T> data) {
        kafkaTemplate.send(topic, data.getKey().toString(), ObjectMapperUtil.toJsonString(data))
                .whenComplete(getSendResultThrowableBiConsumer());
    }

    @NotNull
    private BiConsumer<SendResult<String, String>, Throwable> getSendResultThrowableBiConsumer() {
        return (result, throwable) -> {
            if (Objects.nonNull(throwable)) {
                log.error(throwable.getMessage());
            }
        };
    }

}
