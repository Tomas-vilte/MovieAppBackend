package com.peliculas.peliculasapp.domain.models;
import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.List;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int userId;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "created_at", updatable = false, nullable = false)
    private Timestamp createdAt;

    @Column(name = "updated_at", nullable = false)
    private Timestamp updatedAt;

    @OneToMany(mappedBy = "user")
    private List<Review> reviews;

    // Constructor privado para builder
    public User() {}

    // Getters
    public int getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    // Clase builder
    public static class Builder {
        private User user;

        public Builder() {
            this.user = new User();
        }

        public Builder setUser_id(int user_id) {
            this.user.userId = user_id;
            return this;
        }

        public Builder setUsername(String username) {
            this.user.username = username;
            return this;
        }

        public Builder setPassword(String password) {
            this.user.password = password;
            return this;
        }

        public Builder setEmail(String email) {
            this.user.email = email;
            return this;
        }

        public Builder setCreatedAt(Timestamp created_at) {
            this.user.createdAt = created_at;
            return this;
        }

        public Builder setUpdateAt(Timestamp update_at) {
            this.user.updatedAt = update_at;
            return this;
        }

        public User build() {
            return this.user;
        }
    }
}

