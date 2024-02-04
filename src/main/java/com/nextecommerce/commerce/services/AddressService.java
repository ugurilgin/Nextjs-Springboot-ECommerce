package com.nextecommerce.commerce.services;

import com.nextecommerce.commerce.dtos.requests.AddressRequestDTO;
import com.nextecommerce.commerce.dtos.responses.AddressResponseDTO;

import java.util.List;

public interface AddressService {
     List<AddressResponseDTO> getAllAddresses( );
     AddressResponseDTO getAdressById (Long id);
     AddressResponseDTO createAddress (AddressRequestDTO request );
     AddressResponseDTO updateAddress(Long id, AddressRequestDTO request);
     void deleteAddress(Long id);

}
