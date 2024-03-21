package com.peliculas.peliculasapp.dto;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDTO {
    private long review_id;
    private float rating;
    private String comment;
    private String username;
    private String title;
    private String created_at;
    private String updated_at;
}