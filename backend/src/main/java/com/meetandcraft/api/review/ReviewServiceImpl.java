package com.meetandcraft.api.review;

import com.meetandcraft.api.exceptions.PokemonNotFoundException;
import com.meetandcraft.api.exceptions.ReviewNotFoundException;
import com.meetandcraft.api.pokemon.Pokemon;
import com.meetandcraft.api.pokemon.PokemonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
@Service
public class ReviewServiceImpl implements ReviewService {
    private ReviewRepository reviewRepository;
    private PokemonRepository pokemonRepository;

    @Autowired
    public ReviewServiceImpl(ReviewRepository reviewRepository, PokemonRepository pokemonRepository) {
        this.reviewRepository = reviewRepository;
        this.pokemonRepository = pokemonRepository;
    }

    @Override
    public List<ReviewDto> getReviewsByPokemonId(int id) {
        List<Review> reviews = reviewRepository.findByPokemonId(id);
        return reviews.stream().map(this::mapToDto).toList();
    }

    @Override
    public ReviewDto createReview(int pokemonId, ReviewDto reviewDto) {
        Review review = mapToEntity (reviewDto);
        Pokemon pokemon = pokemonRepository.findById((long) pokemonId).orElseThrow(() -> new PokemonNotFoundException("Pokemon associated with the review is not found"));
        review.setPokemon(pokemon);
        Review newReview = reviewRepository.save(review);
        return mapToDto(newReview);
    }

    @Override
    public ReviewDto getReviewById (int reviewId, int pokemonId) {
        Pokemon pokemon = pokemonRepository.findById((long) pokemonId).orElseThrow(() -> new PokemonNotFoundException("Pokemon associated with the review is not found"));
        Review review = reviewRepository.findById(reviewId).orElseThrow(() -> new ReviewNotFoundException("Review with pokemon " + pokemon.getName() + " could not be found"));
        if(review.getPokemon().getId() != pokemon.getId()){
            throw new ReviewNotFoundException("This review does not belong to a pokemon");
        }
        return mapToDto(review);
    }

    @Override
    public ReviewDto updateReviewById(int pokemonId, int reviewId, ReviewDto reviewDto) {
        Pokemon pokemon = pokemonRepository.findById((long) pokemonId).orElseThrow(() -> new PokemonNotFoundException("Pokemon associated with the review is not found"));
        Review review = reviewRepository.findById(reviewId).orElseThrow(() -> new ReviewNotFoundException("Review with pokemon " + pokemon.getName() + " could not be found"));
        if(review.getPokemon().getId() != pokemon.getId()){
            throw new ReviewNotFoundException("This review does not belong to a pokemon");
        }

        review.setTitle(reviewDto.getTitle());
        review.setContent(reviewDto.getContent());
        review.setStars(reviewDto.getStars());

        Review updatedReview = reviewRepository.save(review);
        return mapToDto(updatedReview);

    }

    @Override
    public void deleteReviewById(int pokemonId, int reviewId) {
        Pokemon pokemon = pokemonRepository.findById((long) pokemonId).orElseThrow(() -> new PokemonNotFoundException("Pokemon associated with the review is not found"));
        Review review = reviewRepository.findById(reviewId).orElseThrow(() -> new ReviewNotFoundException("Review with pokemon " + pokemon.getName() + " could not be found"));
        if(review.getPokemon().getId() != pokemon.getId()){
            throw new ReviewNotFoundException("This review does not belong to a pokemon");
        }

        reviewRepository.delete(review);
    }

    private ReviewDto mapToDto(Review review){
        ReviewDto reviewDto= new ReviewDto();
        reviewDto.setId(review.getId());
        reviewDto.setTitle(review.getTitle());
        reviewDto.setContent(review.getContent());
        reviewDto.setStars(review.getStars());
        return reviewDto;
    }
    private Review mapToEntity(ReviewDto reviewDto){
        Review review = new Review();
        review.setId(reviewDto.getId());
        review.setTitle(reviewDto.getTitle());
        review.setContent(reviewDto.getContent());
        review.setStars(reviewDto.getStars());
        return review;
    }
}
