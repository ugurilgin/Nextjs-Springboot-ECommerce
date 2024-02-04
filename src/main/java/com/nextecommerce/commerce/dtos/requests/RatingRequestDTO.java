package com.nextecommerce.commerce.dtos.requests;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RatingRequestDTO {

    private Double rate;

    private Long count;

}
