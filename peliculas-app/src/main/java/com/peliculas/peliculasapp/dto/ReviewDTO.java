package com.peliculas.peliculasapp.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewDTO {

    int review_id;
    float rating;
    String comment;
    String username;
    String title;

    public ReviewDTO(int review_id, float rating, String comment, String username, String title) {
        this.review_id = review_id;
        this.rating = rating;
        this.comment = comment;
        this.username = username;
        this.title = title;
    }

    public ReviewDTO() {}

}