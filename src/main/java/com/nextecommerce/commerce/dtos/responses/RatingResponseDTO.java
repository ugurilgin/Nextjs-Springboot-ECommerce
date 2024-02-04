package com.nextecommerce.commerce.dtos.responses;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RatingResponseDTO {

    private Long id;

    private Double rate;

    private Long count;

}
