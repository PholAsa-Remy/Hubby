package com.meetandcraft.api.review;

import java.util.List;
public interface ReviewService {
    List<ReviewDto> getReviewsByPokemonId(int id);
    ReviewDto createReview (int pokemonId, ReviewDto reviewDto);
    ReviewDto getReviewById (int reviewId, int pokemonId);
    ReviewDto updateReviewById (int pokemonId, int reviewId, ReviewDto reviewDto);

    void deleteReviewById(int pokemonId, int reviewId);
}
