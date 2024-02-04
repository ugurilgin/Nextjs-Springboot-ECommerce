package com.nextecommerce.commerce.dtos.requests;

import com.nextecommerce.commerce.entities.Ratings;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequestDTO {

    private String title;

    private Double price;

    private String description;


}
