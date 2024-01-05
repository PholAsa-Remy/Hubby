package com.meetandcraft.api.review;

import com.meetandcraft.api.review.ReviewDto;
import com.meetandcraft.api.review.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class ReviewController {
    private ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("pokemon/{pokemonId}/review")
    public ResponseEntity<List<ReviewDto>> getReviewsByPokemonId (
            @PathVariable(value = "pokemonId") int pokemonId
    ) {
        System.out.println("GETTT");
        System.out.println(pokemonId);
        List<ReviewDto> reviews = reviewService.getReviewsByPokemonId(pokemonId);
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }

    @PostMapping("pokemon/{pokemonId}/review")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ReviewDto> createReview (
            @PathVariable(value = "pokemonId") int pokemonId,
            @RequestBody ReviewDto reviewDto
    ){
        return new ResponseEntity<>(reviewService.createReview(pokemonId,reviewDto), HttpStatus.CREATED);
    }
    @GetMapping("/pokemon/{pokemonId}/review/{reviewId}")
    public ResponseEntity<ReviewDto> getReviewById (
            @PathVariable(value = "pokemonId") int pokemonId,
            @PathVariable(value = "reviewId") int reviewId
    ) {
        return new ResponseEntity<>(reviewService.getReviewById(reviewId,pokemonId), HttpStatus.OK);
    }

    @PutMapping("/pokemon/{pokemonId}/review/{reviewId}")
    public ResponseEntity<ReviewDto> updateReviewById (
            @PathVariable(value = "pokemonId") int pokemonId,
            @PathVariable(value = "reviewId") int reviewId,
            @RequestBody ReviewDto reviewDto
    ){
        return new ResponseEntity<>(reviewService.updateReviewById(pokemonId,reviewId,reviewDto),HttpStatus.CREATED);
    }

    @DeleteMapping("/pokemon/{pokemonId}/review/{reviewId}")
    public ResponseEntity<String> deleteReviewById (
            @PathVariable(value = "pokemonId") int pokemonId,
            @PathVariable(value = "reviewId") int reviewId
    ){
        reviewService.deleteReviewById(pokemonId,reviewId);
        return new ResponseEntity<>("Review deleted successfully", HttpStatus.OK);
    }

}
