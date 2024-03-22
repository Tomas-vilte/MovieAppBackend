package com.peliculas.peliculasapp.infrastructure.repositories;
import com.peliculas.peliculasapp.application.ports.out.MovieReviewRepositoryPort;
import com.peliculas.peliculasapp.domain.models.Review;

import java.util.Optional;

public class MovieReviewRepositoryJpaImpl implements MovieReviewRepositoryPort {
    @Override
    public Optional<Review> createMovieReview(Review review) {
        // TODO: Implement
        return Optional.empty();
    }

    @Override
    public Optional<Review> updateMovieReview(Review review) {
        // TODO: Implement
        return Optional.empty();
    }

    @Override
    public Optional<Review> findReviewsByMovieId(long reviewId) {
        // TODO: Implement
        return Optional.empty();
    }

    @Override
    public Optional<Review> deleteReviewById(long reviewId) {
        // TODO: Implement
        return Optional.empty();
    }
}
