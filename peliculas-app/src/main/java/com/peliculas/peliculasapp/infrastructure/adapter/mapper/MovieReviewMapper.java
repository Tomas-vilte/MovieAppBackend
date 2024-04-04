package com.peliculas.peliculasapp.infrastructure.adapter.mapper;
import com.peliculas.peliculasapp.domain.models.MovieReview;
import com.peliculas.peliculasapp.infrastructure.adapter.entities.MovieEntity;
import com.peliculas.peliculasapp.infrastructure.adapter.entities.UserEntity;
import com.peliculas.peliculasapp.infrastructure.adapter.entities.MovieReviewEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;

@Component
public class MovieReviewMapper {
    private final ModelMapper modelMapper;

    @Autowired
    public MovieReviewMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public MovieReviewEntity fromDomainModel(MovieReview movieReview, MovieEntity movie, UserEntity user) {
        MovieReviewEntity reviewEntity = modelMapper.map(movieReview, MovieReviewEntity.class);
        reviewEntity.setMovie(movie);
        reviewEntity.setUser(user);
        reviewEntity.setCreatedAt(LocalDateTime.now());
        return reviewEntity;
    }

    public MovieReview toDomainModel(MovieReviewEntity movieReview) {
        return modelMapper.map(movieReview, MovieReview.class);
    }
}
