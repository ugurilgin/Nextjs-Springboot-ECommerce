package com.nextecommerce.commerce.services.impl;

import com.nextecommerce.commerce.dtos.requests.RatingRequestDTO;
import com.nextecommerce.commerce.dtos.responses.RatingResponseDTO;
import com.nextecommerce.commerce.entities.Ratings;
import com.nextecommerce.commerce.exceptions.EntityNotFoundException;
import com.nextecommerce.commerce.mappers.RatingMapper;
import com.nextecommerce.commerce.repositories.RatingsRepository;
import com.nextecommerce.commerce.services.RatingService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RatingServiceImpl implements RatingService {

    private final RatingsRepository ratingsRepository;
    private final RatingMapper ratingMapper;

    @Override
    public List<RatingResponseDTO> getAllRatings() {

        List<Ratings> ratings = ratingsRepository.findAll();

        return ratings.stream().map(ratingMapper::toResponse).collect(Collectors.toList());

    }

    @Override
    public RatingResponseDTO getRatingById(Long id) {

        Ratings rating=ratingsRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Rating not found with id " + id ));

        return ratingMapper.toResponse(rating);

    }

    @Override
    public RatingResponseDTO createRating(RatingRequestDTO request) {

        Ratings rating  = ratingMapper.toObjFromRequest(request);
        Ratings response = ratingsRepository.save(rating);

        return ratingMapper.toResponse(response);

    }

    @Override
    public RatingResponseDTO updateRating(Long id, RatingRequestDTO request) {

        Ratings rating = ratingsRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Rating not found with id " + id ));

        ratingMapper.convert(rating,request);

        Ratings response = ratingsRepository.save(rating);

        return ratingMapper.toResponse(response);
    }

    @Override
    public void deleteRating(Long id) {

        Ratings rating = ratingsRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Rating not found with id " + id ));

        rating.setIsDeleted(true);
        ratingsRepository.save(rating);

    }
}
