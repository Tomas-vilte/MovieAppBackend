package com.peliculas.peliculasapp.application.usecases;
import com.peliculas.peliculasapp.application.mapper.MovieReviewDtoMapper;
import com.peliculas.peliculasapp.application.ports.in.MovieReviewUseCase;
import com.peliculas.peliculasapp.application.ports.out.MovieReviewRepositoryPort;
import com.peliculas.peliculasapp.domain.models.MovieReview;
import com.peliculas.peliculasapp.domain.dto.MovieReviewDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MovieReviewUseCaseImpl implements MovieReviewUseCase {
    private final MovieReviewDtoMapper movieReviewMapper;
    private final MovieReviewRepositoryPort movieReviewRepositoryPort;
    private final ModelMapper modelMapper;
    @Autowired
    public MovieReviewUseCaseImpl(MovieReviewDtoMapper movieReviewMapper, MovieReviewRepositoryPort movieReviewRepositoryPort, ModelMapper modelMapper) {
        this.movieReviewMapper = movieReviewMapper;
        this.movieReviewRepositoryPort = movieReviewRepositoryPort;
        this.modelMapper = modelMapper;
    }
    @Override
    public Optional<MovieReviewDTO> createMovieReview(MovieReview movieReview) {
        Optional<MovieReview> optionalMovieReview = movieReviewRepositoryPort.createMovieReview(movieReview);
        return Optional.of(modelMapper.map(optionalMovieReview, MovieReviewDTO.class));
    }

    @Override
    public Optional<MovieReview> updateMovieReview(MovieReview review) {
        return Optional.empty();
    }

    @Override
    public List<MovieReviewDTO> findReviewsByMovieId(long reviewId) {
        List<MovieReview> movieReview = movieReviewRepositoryPort.findReviewsByMovieId(reviewId);
        return movieReview.stream()
                .map(movieReviewMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<MovieReview> deleteReviewById(long reviewId) {
        return Optional.empty();
    }
}
