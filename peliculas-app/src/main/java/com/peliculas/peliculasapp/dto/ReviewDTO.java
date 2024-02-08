package com.peliculas.peliculasapp.dto;
import lombok.Getter;
import lombok.Setter;
import java.sql.Timestamp;

@Getter
@Setter
public class ReviewDTO {
    int review_id;
    float rating;
    String comment;
    String username;
    String title;
    Timestamp created_at;
    Timestamp updated_at;

    public ReviewDTO(int review_id, float rating, String comment, String username, String title, Timestamp createdAt, Timestamp updatedAt) {
        this.review_id = review_id;
        this.rating = rating;
        this.comment = comment;
        this.username = username;
        this.title = title;
        this.created_at = updatedAt;
        this.updated_at = createdAt;
    }

}