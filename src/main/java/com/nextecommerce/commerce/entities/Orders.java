package com.nextecommerce.commerce.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.List;


@Entity
@Table(name = "orders")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Orders extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "orders_generator")
    private Long id;

    private Long userId;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "result", columnDefinition = "json")
    private List<Products> products;

    private Double sumPrice;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;


}
