package com.peliculas.peliculasapp.application.mapper;
import com.peliculas.peliculasapp.domain.dto.MovieReviewDTO;
import com.peliculas.peliculasapp.domain.models.MovieReview;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MovieReviewDtoMapper {

    private final MovieDtoMapper movieDtoMapper;
    private final UserDtoMapper userDtoMapper;

    @Autowired
    public MovieReviewDtoMapper(MovieDtoMapper movieDtoMapper, UserDtoMapper userDtoMapper) {
        this.movieDtoMapper = movieDtoMapper;
        this.userDtoMapper = userDtoMapper;
    }


    public MovieReviewDTO toDto(MovieReview movieReview) {
        MovieReviewDTO dto = new MovieReviewDTO();
        dto.setId(movieReview.getId());
        dto.setReviewText(movieReview.getReviewText());
        dto.setRating(movieReview.getRating());
        dto.setCreatedAt(movieReview.getCreatedAt());
        dto.setUpdatedAt(movieReview.getUpdatedAt());

        if (movieReview.getMovie() != null) {
            dto.setMovie(movieDtoMapper.toDto(movieReview.getMovie()));
        }

        if (movieReview.getUser() != null) {
            dto.setUser(userDtoMapper.toDto(movieReview.getUser()));
        }

        return dto;
    }
}
