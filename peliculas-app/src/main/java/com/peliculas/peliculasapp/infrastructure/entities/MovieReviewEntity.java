package com.peliculas.peliculasapp.infrastructure.entities;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "movie_review")
@Setter
@Getter
public class MovieReviewEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    private MovieEntity movie;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @Column(name = "review_text")
    private String reviewText;
    @Column(name = "rating")
    private int rating;
    @Column(name = "created_at")
    @CreatedDate
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @LastModifiedDate
    private LocalDateTime updatedAt;

    public MovieReviewEntity() {}

    public MovieReviewEntity(MovieEntity movie, UserEntity user, String reviewText, int rating) {
        this.movie = movie;
        this.user = user;
        this.reviewText = reviewText;
        this.rating = rating;
        this.createdAt = LocalDateTime.now();
    }
}