package com.nextecommerce.commerce.dtos.responses;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponseForOrderDTO {

    private Long id;

    private String title;

    private Double price;

    private String description;

    private String image;

    private RatingResponseDTO ratings;
}
