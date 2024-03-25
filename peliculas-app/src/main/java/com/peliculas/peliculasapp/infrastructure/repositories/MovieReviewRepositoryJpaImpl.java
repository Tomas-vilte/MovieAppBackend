package com.peliculas.peliculasapp.infrastructure.repositories;
import com.peliculas.peliculasapp.application.ports.out.MovieReviewRepositoryPort;
import com.peliculas.peliculasapp.domain.models.MovieReview;
import com.peliculas.peliculasapp.infrastructure.entities.MovieReviewEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public class MovieReviewRepositoryJpaImpl implements MovieReviewRepositoryPort {
    private final MovieReviewRepository movieReviewRepository;

    @Autowired
    public MovieReviewRepositoryJpaImpl(MovieReviewRepository movieReviewRepository)  {
        this.movieReviewRepository = movieReviewRepository;
    }

    @Override
    public Optional<MovieReview> createMovieReview(MovieReview movieReview) {
        // TODO: Implement
        Optional<MovieReviewEntity> existingMovie = movieReviewRepository.findById(movieReview.getId());

        return Optional.empty();
    }

    @Override
    public Optional<MovieReview> updateMovieReview(MovieReview review) {
        // TODO: Implement
        return Optional.empty();
    }

    @Override
    public Optional<MovieReview> findReviewsByMovieId(long reviewId) {
        // TODO: Implement
        return Optional.empty();
    }

    @Override
    public Optional<MovieReview> deleteReviewById(long reviewId) {
        // TODO: Implement
        return Optional.empty();
    }
}
