package com.peliculas.peliculasapp.application.ports.in;
import com.peliculas.peliculasapp.domain.models.MovieReview;
import com.peliculas.peliculasapp.dto.MovieReviewDTO;

import java.util.Optional;

public interface MovieReviewUseCase {
    Optional<MovieReviewDTO> createMovieReview(MovieReview movieReview);
    Optional<MovieReview> updateMovieReview(MovieReview review);
    Optional<MovieReview> findReviewsByMovieId(long reviewId);
    Optional<MovieReview> deleteReviewById(long reviewId);
}
