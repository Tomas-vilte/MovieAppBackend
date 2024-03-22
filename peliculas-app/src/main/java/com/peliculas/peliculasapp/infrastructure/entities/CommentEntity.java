package com.peliculas.peliculasapp.infrastructure.entities;
import jakarta.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "comments")
public class CommentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "review_id")
    private ReviewEntity review;
    private String commentText;
    private Date publicationDate;
}
