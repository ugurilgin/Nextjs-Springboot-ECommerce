package com.nextecommerce.commerce.repositories;

import com.nextecommerce.commerce.entities.Ratings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingsRepository extends JpaRepository<Ratings,Long> {

}
