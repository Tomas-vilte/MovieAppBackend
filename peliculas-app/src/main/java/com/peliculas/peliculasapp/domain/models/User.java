package com.peliculas.peliculasapp.domain.models;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Date;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private long id;
    private String username;
    private String password;
    private String email;
    private LocalDateTime createdAt;
    private Date updatedAt;

    public User(long id, String username, String email, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.createdAt = LocalDateTime.now();
    }
}