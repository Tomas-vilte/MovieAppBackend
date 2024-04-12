package com.peliculas.peliculasapp.infrastructure.adapter.mapper;
import com.peliculas.peliculasapp.application.ports.incoming.EntityMapper;
import com.peliculas.peliculasapp.domain.models.User;
import com.peliculas.peliculasapp.infrastructure.adapter.entities.UserEntity;
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
                userEntity.getPassword(),
                userEntity.getCreatedAt(),
                userEntity.getUpdatedAt()
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
