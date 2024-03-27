package com.peliculas.peliculasapp.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieReviewDTO {
    private long userId;
    private long movieId;
    private String reviewText;
    private int rating;
}
