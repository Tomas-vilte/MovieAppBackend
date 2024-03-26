package com.peliculas.peliculasapp.infrastructure.repositories;
import com.peliculas.peliculasapp.application.ports.out.MovieReviewRepositoryPort;
import com.peliculas.peliculasapp.domain.models.MovieReview;
import com.peliculas.peliculasapp.infrastructure.entities.MovieReviewEntity;
import com.peliculas.peliculasapp.infrastructure.entities.UserEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public class MovieReviewRepositoryJpaImpl implements MovieReviewRepositoryPort {
    private final MovieReviewRepository movieReviewRepository;
    private final ModelMapper movieReviewMapper;
    private final UserRepository userRepository;

    @Autowired
    public MovieReviewRepositoryJpaImpl(MovieReviewRepository movieReviewRepository,
                                        ModelMapper movieReviewMapper,
                                        UserRepository userRepository)  {
        this.movieReviewRepository = movieReviewRepository;
        this.movieReviewMapper = movieReviewMapper;
        this.userRepository = userRepository;
    }

    @Override
    public Optional<MovieReview> createMovieReview(MovieReview movieReview) {
        UserEntity userEntity = verifyUserExists(movieReview.getMovie().getId());

        MovieReviewEntity reviewEntity = movieReviewMapper.map(movieReview, MovieReviewEntity.class);
        reviewEntity.setUser(userEntity);

        reviewEntity = movieReviewRepository.save(reviewEntity);

        return Optional.of(movieReviewMapper.map(reviewEntity, MovieReview.class));
    }

    private UserEntity verifyUserExists(long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));
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
