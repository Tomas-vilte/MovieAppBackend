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
    private final MovieReviewMapper movieReviewMapper;

    @Autowired
    public MovieReviewRepositoryJpaImpl(MovieReviewRepository movieReviewRepository, MovieReviewMapper movieReviewMapper)  {
        this.movieReviewRepository = movieReviewRepository;
        this.movieReviewMapper = movieReviewMapper;
    }

    @Override
    public Optional<MovieReview> createMovieReview(MovieReview movieReview) {
        MovieReviewEntity movieReviewEntity = movieReviewMapper.toEntity(movieReview);
        MovieReviewEntity saveEntity = movieReviewRepository.save(movieReviewEntity);
        return Optional.of(movieReviewMapper.toDomainModel(saveEntity));
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
