package com.nextecommerce.commerce.repositories;

import com.nextecommerce.commerce.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<Address,Long> {

    Optional<Address> findByUserId(long l);

    Boolean existsByUserId(long l);
}
