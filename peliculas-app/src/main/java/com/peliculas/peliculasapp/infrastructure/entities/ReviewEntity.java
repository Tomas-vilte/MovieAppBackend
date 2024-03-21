package com.peliculas.peliculasapp.infrastructure.entities;
import jakarta.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "review")
public class ReviewEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    private MovieEntity movie;

    @ManyToOne
    @JoinColumn(name = "series_id")
    private TvSeriesEntity series;

    private String reviewText;
    private int rating;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}
