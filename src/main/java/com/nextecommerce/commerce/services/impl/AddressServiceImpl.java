package com.nextecommerce.commerce.services.impl;

import com.nextecommerce.commerce.dtos.requests.AddressRequestDTO;
import com.nextecommerce.commerce.dtos.responses.AddressResponseDTO;
import com.nextecommerce.commerce.entities.Address;
import com.nextecommerce.commerce.exceptions.EntityNotFoundException;
import com.nextecommerce.commerce.mappers.AddressMapper;
import com.nextecommerce.commerce.repositories.AddressRepository;
import com.nextecommerce.commerce.services.AddressService;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    private final AddressMapper addressMapper;

    @Override
    public List<AddressResponseDTO> getAllAddresses() {

        List<Address> addresses = addressRepository.findAll();

        return addresses.stream().map(addressMapper::toResponse).collect(Collectors.toList());

    }

    @Override
    public AddressResponseDTO getAdressById(Long id) {

        Address address=addressRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Address not found with id " + id ));

        return addressMapper.toResponse(address);

    }

    @Override
    @Transactional
    public AddressResponseDTO createAddress(AddressRequestDTO request) {

        Address exist = addressRepository.findByUserId(1L).orElse(null);

        if( exist!=null )
        {
            AddressResponseDTO response =  updateAddress(exist.getId(), request);
            return response;
        }

        Address address  = addressMapper.toObjFromRequest(request);
        Address response = addressRepository.save(address);

        return addressMapper.toResponse(response);

    }

    @Override
    @Transactional
    public AddressResponseDTO updateAddress(Long id, AddressRequestDTO request) {

        Address address = addressRepository.findById(id)
               .orElseThrow(() -> new EntityNotFoundException("Address not found with id " + id ));

        addressMapper.convert(address,request);

        Address response = addressRepository.save(address);

        return addressMapper.toResponse(response);

    }

    @Override
    @Transactional
    public void deleteAddress(Long id) {

        Address address = addressRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Address not found with id " + id ));

        address.setIsDeleted(true);
        addressRepository.save(address);


    }

}
