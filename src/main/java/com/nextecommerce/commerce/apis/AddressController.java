package com.nextecommerce.commerce.apis;

import com.nextecommerce.commerce.dtos.requests.AddressRequestDTO;
import com.nextecommerce.commerce.dtos.responses.AddressResponseDTO;
import com.nextecommerce.commerce.services.AddressService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/address")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@AllArgsConstructor
public class AddressController {

    private final AddressService addressService;

    @GetMapping
    public ResponseEntity<List<AddressResponseDTO>> getAllAddresses() {

        return new ResponseEntity<>(addressService.getAllAddresses(),HttpStatus.OK);

    }

    @GetMapping("/{id}")
    public ResponseEntity<AddressResponseDTO> getAdressById(@PathVariable Long id) {

        return new ResponseEntity<>(addressService.getAdressById(id),HttpStatus.OK);

    }

    @PostMapping
    public ResponseEntity<AddressResponseDTO> createAddress(@Valid @RequestBody AddressRequestDTO request) {

        return new ResponseEntity<>(addressService.createAddress(request),HttpStatus.CREATED);

    }

    @PutMapping("/{id}")
    public ResponseEntity<AddressResponseDTO> updateAddress(@PathVariable Long id,@RequestBody @Valid AddressRequestDTO request) {

        return new ResponseEntity<>(addressService.updateAddress(id,request),HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteAddress(@PathVariable Long id) {

        addressService.deleteAddress(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }
}
