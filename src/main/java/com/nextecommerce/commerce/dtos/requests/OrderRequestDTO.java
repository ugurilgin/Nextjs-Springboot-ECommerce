package com.nextecommerce.commerce.dtos.requests;

import com.nextecommerce.commerce.entities.Address;
import com.nextecommerce.commerce.entities.Products;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequestDTO {

    private List<Products> products;

    private Double sumPrice;

    private Address address;

}
