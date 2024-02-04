package com.nextecommerce.commerce.dtos.requests;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressRequestDTO {

    private String address;

    private String city;

    private String country;

}
