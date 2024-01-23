package com.peliculas.peliculasapp.models;
import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;
    private String username;
    private String password;
    private String email;
    private Timestamp createdAt;
    private Timestamp updatedAt;


}
