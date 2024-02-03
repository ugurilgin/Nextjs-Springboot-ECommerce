package com.nextecommerce.commerce.repositories;

import com.nextecommerce.commerce.entities.Photo;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface PhotoRepository extends MongoRepository<Photo, String> {
}
