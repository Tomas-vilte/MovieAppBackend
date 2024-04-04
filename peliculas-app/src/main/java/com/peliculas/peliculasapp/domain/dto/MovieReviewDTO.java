package com.peliculas.peliculasapp.domain.dto;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.peliculas.peliculasapp.domain.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieReviewDTO {
    private long id;
    @JsonProperty("movie_id")
    private String title;
    private Long movieId;
    @JsonProperty("user_id")
    private Long userId;
    private int rating;
    @JsonProperty("review_text")
    private String reviewText;
    @JsonProperty("created_at")
    private LocalDateTime createdAt;
    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;
    private String username;
}

