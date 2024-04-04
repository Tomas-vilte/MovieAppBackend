package com.peliculas.peliculasapp.infrastructure.adapter.entities;
import jakarta.persistence.*;

@Entity
@Table(name = "ratings")
public class RatingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String type;
    private Long ratedItemId;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
    private int score;
}
