package com.nextecommerce.commerce.dtos.responses;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponseDTO {

    private Long id;

    private String title;

    private Double price;

    private String description;

    private String category;

    private String image;

    private RatingResponseDTO ratings;
}
