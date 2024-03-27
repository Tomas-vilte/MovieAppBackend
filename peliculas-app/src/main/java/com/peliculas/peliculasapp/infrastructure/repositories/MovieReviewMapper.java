package com.peliculas.peliculasapp.infrastructure.repositories;
import com.peliculas.peliculasapp.application.ports.incoming.EntityMapper;
import com.peliculas.peliculasapp.domain.models.MovieReview;
import com.peliculas.peliculasapp.infrastructure.entities.MovieReviewEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MovieReviewMapper implements EntityMapper<MovieReviewEntity, MovieReview> {
    private final ModelMapper modelMapper;
    @Autowired
    public MovieReviewMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }


    @Override
    public MovieReview toDomainModel(MovieReviewEntity entity) {
        return modelMapper.map(entity, MovieReview.class);
    }

    @Override
    public MovieReviewEntity toEntity(MovieReview model) {
        return modelMapper.map(model, MovieReviewEntity.class);
    }
}