package com.nextecommerce.commerce.repositories;

import com.nextecommerce.commerce.entities.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Products,Long> {

}
