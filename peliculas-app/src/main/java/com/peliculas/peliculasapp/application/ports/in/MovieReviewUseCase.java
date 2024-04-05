package com.peliculas.peliculasapp.application.ports.in;
import com.peliculas.peliculasapp.domain.models.MovieReview;
import com.peliculas.peliculasapp.domain.dto.MovieReviewDTO;
import java.util.List;
import java.util.Optional;

public interface MovieReviewUseCase {
    Optional<MovieReviewDTO> createMovieReview(MovieReview movieReview);
    Optional<MovieReview> updateMovieReview(MovieReview review);
    List<MovieReviewDTO> findAllReviewsByMovieId(long reviewId);
    void deleteReviewById(long reviewId);
}
