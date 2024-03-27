package com.peliculas.peliculasapp.domain.models;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieReview {
    private long id;
    @JsonProperty("movie_id")
    private long movieId;
    @JsonProperty("user_id")
    private long userId;
    private int rating;
    @JsonProperty("review_text")
    private String reviewText;
    private LocalDateTime createdAt;
}