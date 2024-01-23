package com.peliculas.peliculasapp.models;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.GenerationType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import java.sql.Timestamp;

@Entity
@Table(name = "users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int user_id;
    private String username;
    private String password;
    private String email;
    private Timestamp created_at;
    private Timestamp updated_at;

    // Constructor privado para builder
    public Users() {}

    // Getters
    public int getUser_id() {
        return user_id;
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

    public Timestamp getCreated_at() {
        return created_at;
    }

    public Timestamp getUpdated_at() {
        return updated_at;
    }

    // Clase builder
    public static class Builder {
        private Users user;

        public Builder() {
            this.user = new Users();
        }

        public Builder setUser_id(int user_id) {
            this.user.user_id = user_id;
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
            this.user.created_at = created_at;
            return this;
        }

        public Builder setUpdateAt(Timestamp update_at) {
            this.user.updated_at = update_at;
            return this;
        }

        public Users build() {
            return this.user;
        }
    }
}

