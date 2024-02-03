package com.nextecommerce.commerce.dtos.responses;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PhotoResponseDTO {

    private String id;

    private Long userId;

    private String title;

    private String url;

}
