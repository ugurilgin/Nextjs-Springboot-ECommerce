package com.nextecommerce.commerce.dtos.responses;

import com.nextecommerce.commerce.entities.Address;
import com.nextecommerce.commerce.entities.Products;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponseDTO {

    private Long id;

    private Long userId;

    private List<Products> products;

    private Double sumPrice;

    private Address address;
}
