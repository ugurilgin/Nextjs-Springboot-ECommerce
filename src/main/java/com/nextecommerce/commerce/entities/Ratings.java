package com.nextecommerce.commerce.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "ratings")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Ratings extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ratings_generator")
    private Long id;

    private Double rate;

    private Long count;

}
