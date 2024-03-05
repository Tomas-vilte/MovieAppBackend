package com.peliculas.peliculasapp.dto;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDTO {
    int review_id;
    float rating;
    String comment;
    String username;
    String title;
    Timestamp created_at;
    Timestamp updated_at;
}