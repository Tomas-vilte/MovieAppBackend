package com.peliculas.peliculasapp.application.ports.out;
import com.peliculas.peliculasapp.domain.models.MovieReview;
import java.util.List;
import java.util.Optional;

public interface MovieReviewRepositoryPort {
    Optional<MovieReview> createMovieReview(MovieReview review);
    Optional<MovieReview> updateMovieReview(MovieReview review);
    List<MovieReview> findAllReviewsByMovieId(long reviewId);
    void deleteReviewById(long reviewId);
    Optional<MovieReview> getMovieReviewById(long reviewId);
}
