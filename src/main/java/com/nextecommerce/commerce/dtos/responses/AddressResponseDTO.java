package com.nextecommerce.commerce.dtos.responses;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressResponseDTO {

    private  Long id;

    private String address;

    private String city;

    private String country;

}
