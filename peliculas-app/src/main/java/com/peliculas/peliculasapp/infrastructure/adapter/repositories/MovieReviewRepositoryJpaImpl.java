package com.peliculas.peliculasapp.infrastructure.adapter.repositories;
import com.peliculas.peliculasapp.application.ports.out.MovieReviewRepositoryPort;
import com.peliculas.peliculasapp.domain.models.MovieReview;
import com.peliculas.peliculasapp.infrastructure.adapter.entities.MovieEntity;
import com.peliculas.peliculasapp.infrastructure.adapter.entities.UserEntity;
import com.peliculas.peliculasapp.infrastructure.adapter.entities.MovieReviewEntity;
import com.peliculas.peliculasapp.infrastructure.adapter.exceptions.MovieNotFoundException;
import com.peliculas.peliculasapp.infrastructure.adapter.exceptions.MovieReviewNotFoundException;
import com.peliculas.peliculasapp.infrastructure.adapter.exceptions.UserNotFoundException;
import com.peliculas.peliculasapp.infrastructure.adapter.mapper.MovieReviewMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;
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
       Optional<MovieReviewEntity> optionalMovieReview = movieReviewRepository.findById(review.getId());
       if (optionalMovieReview.isPresent()) {
           MovieReviewEntity movieReviewEntity = optionalMovieReview.get();
           movieReviewEntity.setReviewText(review.getReviewText());
           movieReviewEntity.setRating(review.getRating());
           movieReviewEntity.setUpdatedAt(LocalDateTime.now());

           movieReviewEntity = movieReviewRepository.save(movieReviewEntity);
           return Optional.of(movieReviewMapper.toDomainModel(movieReviewEntity));
       } else {
           throw new MovieReviewNotFoundException("No se pudo actualizar la revisión, la revisión con ID " + review.getId() + " no existe.");
       }
    }

    @Override
    public Optional<MovieReview> getMovieReviewById(long reviewId) {
       Optional<MovieReviewEntity> movieReview = movieReviewRepository.findById(reviewId);
       return movieReview.map(movieReviewMapper::toDomainModel);
    }

    @Override
    public List<MovieReview> findAllReviewsByMovieId(long reviewId) {
        List<MovieReviewEntity> movieReviewEntities = movieReviewRepository.findAllReviewsByMovieId(reviewId);
        return movieReviewEntities.stream()
                .map(movieReviewMapper::toDomainModel)
                .toList();
    }

    @Override
    public void deleteReviewById(long reviewId) {
        Optional<MovieReviewEntity> optionalMovieReview = movieReviewRepository.findById(reviewId);
        if (optionalMovieReview.isPresent()) {
            movieReviewRepository.deleteById(reviewId);
        } else {
            throw new MovieReviewNotFoundException("La reseña de película con el ID " + reviewId + " no se encontró.");
        }
    }
}
