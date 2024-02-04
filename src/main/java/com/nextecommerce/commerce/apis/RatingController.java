package com.nextecommerce.commerce.apis;

import com.nextecommerce.commerce.dtos.requests.RatingRequestDTO;
import com.nextecommerce.commerce.dtos.responses.RatingResponseDTO;
import com.nextecommerce.commerce.services.RatingService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ratings")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@AllArgsConstructor
public class RatingController  {

    private final RatingService ratingService;
    @GetMapping
    public ResponseEntity<List<RatingResponseDTO>> getAllRatings() {

        return new ResponseEntity<>(ratingService.getAllRatings(),HttpStatus.OK);

    }

    @GetMapping("/{id}")
    public  ResponseEntity<RatingResponseDTO> getRatingById(@PathVariable Long id) {

        return new ResponseEntity<>(ratingService.getRatingById(id),HttpStatus.OK);

    }

    @PostMapping
    public  ResponseEntity<RatingResponseDTO> createRating(@Valid @RequestBody RatingRequestDTO request) {

        return new ResponseEntity<>(ratingService.createRating(request),HttpStatus.CREATED);

    }

    @PutMapping("/{id}")
    public  ResponseEntity<RatingResponseDTO> updateRating(@PathVariable Long id, @Valid @RequestBody RatingRequestDTO request) {

        return new ResponseEntity<>(ratingService.updateRating(id,request),HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteRating(@PathVariable Long id) {

        ratingService.deleteRating(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }


}
