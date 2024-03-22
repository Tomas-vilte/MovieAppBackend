package com.peliculas.peliculasapp.application.ports.out;
import com.peliculas.peliculasapp.domain.models.User;
import com.peliculas.peliculasapp.infrastructure.entities.UserEntity;

import java.util.Optional;

public interface UserRepositoryPort {
    Optional<User> saveUser(User user);
    Optional<User> findUserByID(long userId);
    Optional<User> deleteUser(long userId);
    Optional<User> updateUser(User user);
}
