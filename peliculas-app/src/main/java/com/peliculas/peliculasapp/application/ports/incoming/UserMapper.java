package com.peliculas.peliculasapp.application.ports.incoming;
import com.peliculas.peliculasapp.domain.models.User;
import com.peliculas.peliculasapp.infrastructure.entities.UserEntity;

public interface UserMapper {
    User toDomainModel(UserEntity userEntity);
    UserEntity toEntity(User user);
}