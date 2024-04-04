package com.peliculas.peliculasapp.application.mapper;
import com.peliculas.peliculasapp.domain.dto.MovieReviewDTO;
import com.peliculas.peliculasapp.domain.models.MovieReview;
import org.springframework.stereotype.Component;

@Component
public class MovieReviewDtoMapper {
    public MovieReviewDTO toDto(MovieReview movieReview) {
        MovieReviewDTO dto = new MovieReviewDTO();
        dto.setId(movieReview.getId());
        dto.setReviewText(movieReview.getReviewText());
        dto.setRating(movieReview.getRating());
        dto.setCreatedAt(movieReview.getCreatedAt());
        dto.setUpdatedAt(movieReview.getUpdatedAt());
        dto.setMovieId(movieReview.getMovieId());
        dto.setTitle(movieReview.getMovie().getTitle());
        dto.setUserId(movieReview.getUser().getId());
        dto.setUsername(movieReview.getUser().getUsername());
        return dto;
    }
}
