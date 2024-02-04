package com.nextecommerce.commerce.event.events;

import com.nextecommerce.commerce.dtos.requests.OrderRequestDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class OrderEvent extends BaseEvent<UUID> {

    private OrderRequestDTO request;
}
