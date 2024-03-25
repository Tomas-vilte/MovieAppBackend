package com.peliculas.peliculasapp.infrastructure.repositories;
import com.peliculas.peliculasapp.application.ports.incoming.EntityMapper;
import com.peliculas.peliculasapp.domain.models.MovieReview;
import com.peliculas.peliculasapp.infrastructure.entities.MovieReviewEntity;
import org.springframework.stereotype.Component;

@Component
public class MovieReviewMapper implements EntityMapper<MovieReviewEntity, MovieReview> {
    @Override
    public MovieReview toDomainModel(MovieReviewEntity entity) {
        return new MovieReview(
                entity.getId(),
                entity.getMovie(),
                entity.getUser(),
                entity.getReviewText(),
                entity.getRating(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }

    @Override
    public MovieReviewEntity toEntity(MovieReview model) {
        return new MovieReviewEntity(
                model.getId(),
                model.getMovie(),
                model.getUser(),
                model.getReviewText(),
                model.getRating(),
                model.getCreatedAt(),
                model.getUpdatedAt()
        );
    }
}
