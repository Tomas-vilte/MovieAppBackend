package com.peliculas.peliculasapp.application.ports.out;
import com.peliculas.peliculasapp.domain.models.Review;
import java.util.Optional;

public interface MovieReviewRepositoryPort {
    Optional<Review> createMovieReview(Review review);
    Optional<Review> updateMovieReview(Review review);
    Optional<Review> findReviewsByMovieId(long reviewId);

    Optional<Review> deleteReviewById(long reviewId);
}
