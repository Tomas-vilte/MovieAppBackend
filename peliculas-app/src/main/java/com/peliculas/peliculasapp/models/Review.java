package com.peliculas.peliculasapp.models;
import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "content")
    private Content content;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private float rating;
    private String comment;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    // Constructor privado para evitar la creacion directa de instancias
    private Review() {}

    // Getters
    public int getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Content getContent() {
        return content;
    }

    public float getRating() {
        return rating;
    }

    public String getComment() {
        return comment;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public static class Builder {
        private Review review;

        public Builder() {
            this.review = new Review();
        }

        public Builder setUser(User user) {
            this.review.user = user;
            return this;
        }

        public Builder setContent(Content content) {
            this.review.content = content;
            return this;
        }

        public Builder setRating(float rating) {
            this.review.rating = rating;
            return this;
        }

        public Builder setComment(String comment) {
            this.review.comment = comment;
            return this;
        }

        public Builder setCreatedAt(Timestamp createdAt) {
            this.review.createdAt = createdAt;
            return this;
        }

        public Builder setUpdatedAt(Timestamp updatedAt) {
            this.review.updatedAt = updatedAt;
            return this;
        }

        public Review build() {
            return this.review;
        }
    }
}
