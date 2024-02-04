package com.nextecommerce.commerce.event.events;

import com.nextecommerce.commerce.enums.EventType;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class BaseEvent<T> {

    private T key;

    private String topic;

    private EventType eventType;

}
