package com.peliculas.peliculasapp.infrastructure.repositories;
import com.peliculas.peliculasapp.application.ports.out.MovieReviewRepositoryPort;
import com.peliculas.peliculasapp.domain.models.MovieReview;
import com.peliculas.peliculasapp.infrastructure.entities.MovieEntity;
import com.peliculas.peliculasapp.infrastructure.entities.MovieReviewEntity;
import com.peliculas.peliculasapp.infrastructure.entities.UserEntity;
import com.peliculas.peliculasapp.infrastructure.exceptions.MovieNotFoundException;
import com.peliculas.peliculasapp.infrastructure.exceptions.UserNotFoundException;
import com.peliculas.peliculasapp.infrastructure.mapper.MovieReviewMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Repository
@Transactional
public class MovieReviewRepositoryJpaImpl implements MovieReviewRepositoryPort {
    private final MovieReviewRepository movieReviewRepository;
    private final MovieReviewMapper movieReviewMapper;
    private final MovieRepository movieRepository;
    private final UserRepository userRepository;

    @Autowired
    public MovieReviewRepositoryJpaImpl(MovieReviewRepository movieReviewRepository, MovieReviewMapper movieReviewMapper,
                                        MovieRepository movieRepository,
                                        UserRepository userRepository)  {
        this.movieReviewMapper = movieReviewMapper;
        this.movieRepository = movieRepository;
        this.movieReviewRepository = movieReviewRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Optional<MovieReview> createMovieReview(MovieReview review) {
        MovieEntity movie = findMovieById(review.getMovieId());
        UserEntity user = findUserById(review.getUserId());

        MovieReviewEntity reviewEntity = movieReviewMapper.fromDomainModel(review, movie, user);
        MovieReviewEntity savedEntity = movieReviewRepository.save(reviewEntity);

        return Optional.of(movieReviewMapper.toDomainModel(savedEntity));
    }

    private MovieEntity findMovieById(long movieId) {
        return movieRepository.findById(movieId)
                .orElseThrow(() -> new MovieNotFoundException("Pelicula no encontrada"));
    }

    private UserEntity findUserById(long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("Usuario no encontrado"));
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
