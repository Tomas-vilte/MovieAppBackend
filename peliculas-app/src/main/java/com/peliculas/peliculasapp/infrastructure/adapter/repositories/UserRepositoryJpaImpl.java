package com.peliculas.peliculasapp.infrastructure.adapter.repositories;
import com.peliculas.peliculasapp.application.ports.out.UserRepositoryPort;
import com.peliculas.peliculasapp.domain.models.User;
import com.peliculas.peliculasapp.infrastructure.adapter.entities.UserEntity;
import com.peliculas.peliculasapp.infrastructure.adapter.exceptions.EmailAlreadyExistsException;
import com.peliculas.peliculasapp.infrastructure.adapter.exceptions.UserNotFoundException;
import com.peliculas.peliculasapp.infrastructure.adapter.mapper.UserMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public class UserRepositoryJpaImpl implements UserRepositoryPort {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserRepositoryJpaImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    @Transactional(readOnly = false)
    public Optional<User> saveUser(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new EmailAlreadyExistsException("Ya existe un usuario con el correo electrónico proporcionado: " + user.getEmail());
        }
        UserEntity userEntity = userMapper.toEntity(user);
        UserEntity saveUser = userRepository.save(userEntity);
        return Optional.of(userMapper.toDomainModel(saveUser));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> findUserByID(long userId) {
        return Optional.of(userRepository.findById(userId)
                .map(userMapper::toDomainModel)
                .orElseThrow(() -> new UserNotFoundException("Usuario no encontrado con este ID: " + userId)));
    }

    @Override
    @Transactional(readOnly = false)
    public Optional<User> deleteUser(long userId) {
        Optional<UserEntity> existingUser = userRepository.findById(userId);
        return Optional.ofNullable(existingUser.map(userEntity -> {
            userRepository.delete(userEntity);
            return userMapper.toDomainModel(userEntity);
        }).orElseThrow(() -> new UserNotFoundException("No se encontró ningún usuario con el ID: " + userId)));
    }

    @Override
    @Transactional(readOnly = false)
    public Optional<User> updateUser(User user) {
        Optional<UserEntity> existingUserOptional = userRepository.findById(user.getId());
        return Optional.of(existingUserOptional.map(existingUser -> {
            updateUserFields(user, existingUser);
            return userMapper.toDomainModel(userRepository.save(existingUser));
        }).orElseThrow(() -> new UserNotFoundException("Usuario no encontrado con ID: " + user.getId())));
    }

    public Optional<UserEntity> findUserById(long userId) {
        return Optional.of(userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException("Usuario no encontrado con este ID: " + userId)
        ));
    }

    private void updateUserFields(User source, UserEntity target) {
        target.setUsername(source.getUsername());
        target.setEmail(source.getEmail());
        target.setUpdatedAt(LocalDateTime.now());

        if (source.getPassword() != null && !source.getPassword().isEmpty()) {
            target.setPassword(source.getPassword());
        }
    }
}
