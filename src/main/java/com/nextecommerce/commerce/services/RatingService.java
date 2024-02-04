package com.nextecommerce.commerce.services;

import com.nextecommerce.commerce.dtos.requests.RatingRequestDTO;
import com.nextecommerce.commerce.dtos.responses.ProductResponseDTO;
import com.nextecommerce.commerce.dtos.responses.RatingResponseDTO;

import java.util.List;

public interface RatingService {
     List<RatingResponseDTO> getAllRatings( );
     RatingResponseDTO getRatingById (Long id);
     RatingResponseDTO createRating (RatingRequestDTO request );
     RatingResponseDTO updateRating(Long id, RatingRequestDTO request);
     void deleteRating(Long id);

}
