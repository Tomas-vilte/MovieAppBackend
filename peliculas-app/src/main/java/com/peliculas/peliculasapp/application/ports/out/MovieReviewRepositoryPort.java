package com.peliculas.peliculasapp.application.ports.out;
import com.peliculas.peliculasapp.domain.models.MovieReview;

import java.util.List;
import java.util.Optional;

public interface MovieReviewRepositoryPort {
    Optional<MovieReview> createMovieReview(MovieReview review);
    Optional<MovieReview> updateMovieReview(MovieReview review);
    List<MovieReview> findReviewsByMovieId(long reviewId);
    Optional<MovieReview> deleteReviewById(long reviewId);
}
