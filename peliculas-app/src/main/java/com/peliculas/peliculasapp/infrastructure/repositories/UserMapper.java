package com.peliculas.peliculasapp.infrastructure.repositories;
import com.peliculas.peliculasapp.application.ports.incoming.EntityMapper;
import com.peliculas.peliculasapp.domain.models.User;
import com.peliculas.peliculasapp.infrastructure.entities.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserMapper implements EntityMapper<UserEntity, User> {
    public UserMapper() {}

    @Override
    public User toDomainModel(UserEntity userEntity) {
        return new User(
                userEntity.getId(),
                userEntity.getUsername(),
                userEntity.getEmail(),
                userEntity.getPassword()
        );
    }

    @Override
    public UserEntity toEntity(User user) {
        return new UserEntity(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getPassword()
        );
    }
}
