package com.peliculas.peliculasapp.models;
import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.List;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;
    private String username;
    private String password;
    private String email;
    private Timestamp createdAt;

    // EAGER = Ansioso
    // Lazy = Peresozo
    @OneToMany(mappedBy = "user")
    private List<Review> reviews;
    private Timestamp updatedAt;

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

