package com.nextecommerce.commerce.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.annotations.Where;


@Entity
@Table(name = "address")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Where(clause = "is_deleted = false")
public class Address extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "address_generator")
    private Long id;

    private Long userId;

    @NotBlank(message = "Address can not be null")
    @Column(name = "address", nullable = false)
    private String address;

    @NotBlank(message = "City can not be null")
    @Column(name = "city", nullable = false)
    private String city;

    @NotBlank(message = "Country can not be null")
    @Column(name = "country", nullable = false)
    private String country;

}
