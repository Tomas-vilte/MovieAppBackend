package com.peliculas.peliculasapp.infrastructure.adapter.repositories;
import com.peliculas.peliculasapp.domain.models.User;
import com.peliculas.peliculasapp.infrastructure.adapter.entities.UserEntity;
import com.peliculas.peliculasapp.infrastructure.adapter.exceptions.EmailAlreadyExistsException;
import com.peliculas.peliculasapp.infrastructure.adapter.exceptions.UserNotFoundException;
import com.peliculas.peliculasapp.infrastructure.adapter.mapper.UserMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class UserRepositoryJpaImplTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private UserMapper userMapper;
    @InjectMocks
    private UserRepositoryJpaImpl userRepositoryJpaImpl;
    private User user;
    private UserEntity userEntity;

    @BeforeEach
    public void setUp() {
        user = createUser();
        userEntity = createUserEntity();
    }

    private User createUser() {
        User user = new User();
        user.setId(1L);
        user.setUsername("testUser");
        user.setEmail("test@example.com");
        user.setPassword("password");
        return user;
    }

    private UserEntity createUserEntity() {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(1L);
        userEntity.setUsername("testUser");
        userEntity.setEmail("test@example.com");
        userEntity.setPassword("password");
        return userEntity;
    }

    @Test
    public void saveUserSuccess() {
        // arrange

        Mockito.when(userMapper.toEntity(user)).thenReturn(userEntity);
        Mockito.when(userRepository.existsByEmail(user.getEmail())).thenReturn(false);
        Mockito.when(userRepository.save(userEntity)).thenReturn(userEntity);
        Mockito.when(userMapper.toDomainModel(userEntity)).thenReturn(user);

        // act
        Optional<User> savedUser = userRepositoryJpaImpl.saveUser(user);

        // assert
        assertTrue(savedUser.isPresent());
        assertEquals(user.getId(), savedUser.get().getId());
        assertEquals(user.getEmail(), savedUser.get().getEmail());

        Mockito.verify(userRepository).existsByEmail("test@example.com");
        Mockito.verify(userRepository).save(any(UserEntity.class));
    }

    @Test
    public void saveUserEmailAlreadyExistsExceptionThrown() {

        Mockito.when(userRepository.existsByEmail(user.getEmail())).thenReturn(true);

        Exception exception = assertThrows(EmailAlreadyExistsException.class, () -> {
            userRepositoryJpaImpl.saveUser(user);
        });

        assertEquals("Ya existe un usuario con el correo electrónico proporcionado: " + user.getEmail(), exception.getMessage());

        Mockito.verify(userRepository, Mockito.never()).save(any(UserEntity.class));
    }

    @Test
    public void testFindUserByIdSuccess() {
        // arrange
        long userId = 1;
        Mockito.when(userRepository.findById(userId)).thenReturn(Optional.of(userEntity));
        User expectedUser = createUser();
        Mockito.when(userMapper.toDomainModel(userEntity)).thenReturn(expectedUser);

        // act y assert
        Optional<User> foundUser = userRepositoryJpaImpl.findUserByID(userId);

        assertTrue(foundUser.isPresent());
        assertEquals(expectedUser, foundUser.get());
        assertEquals(1, foundUser.get().getId());
        Mockito.verify(userRepository).findById(userId);
        Mockito.verify(userMapper).toDomainModel(userEntity);
    }

    @Test
    public void testFindUserByIDUserNotFound() {
        // arrange
        long userId = 1;
        Mockito.when(userRepository.findById(userId)).thenReturn(Optional.empty());

        Exception exception = assertThrows(UserNotFoundException.class, () -> {
            userRepositoryJpaImpl.findUserByID(1);
        });

        assertEquals("Usuario no encontrado con este ID: 1", exception.getMessage());
    }

    @Test
    public void deleteUserSuccess() {
        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.of(userEntity));
        Mockito.when(userMapper.toDomainModel(userEntity)).thenReturn(user);

        Optional<User> deleteUser = userRepositoryJpaImpl.deleteUser(1L);

        assertTrue(deleteUser.isPresent());
        assertEquals(user.getId(), deleteUser.get().getId());

        Mockito.verify(userRepository).findById(1L);
        Mockito.verify(userRepository).delete(userEntity);
    }

    @Test
    public void deleteUserUserNotFound() {
        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(UserNotFoundException.class, () -> {
            userRepositoryJpaImpl.deleteUser(1L);
        });

        assertEquals("No se encontró ningún usuario con el ID: 1", exception.getMessage());
        Mockito.verify(userRepository).findById(1L);
        Mockito.verify(userRepository, Mockito.never()).delete(any(UserEntity.class));
    }

    @Test
    public void updateUserSuccess() {
        User user = new User();
        user.setId(1L);
        user.setUsername("newUsername");
        user.setEmail("newemail@example.com");
        user.setPassword("newPassword");
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());

        UserEntity existingUserEntity = new UserEntity();
        existingUserEntity.setId(1L);
        existingUserEntity.setUsername("oldUsername");
        existingUserEntity.setEmail("oldemail@example.com");
        existingUserEntity.setPassword("oldPassword");
        existingUserEntity.setCreatedAt(LocalDateTime.now());
        existingUserEntity.setUpdatedAt(LocalDateTime.now());


        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.of(existingUserEntity));
        Mockito.when(userRepository.save(any(UserEntity.class))).thenReturn(existingUserEntity);
        Mockito.when(userMapper.toDomainModel(existingUserEntity)).thenReturn(user);


        Optional<User> updatedUser = userRepositoryJpaImpl.updateUser(user);


        assertTrue(updatedUser.isPresent());
        assertEquals(user.getId(), updatedUser.get().getId());
        assertEquals(user.getUsername(), updatedUser.get().getUsername());
        assertEquals(user.getEmail(), updatedUser.get().getEmail());
        assertEquals(user.getPassword(), updatedUser.get().getPassword());
        assertNotNull(updatedUser.get().getUpdatedAt());

        Mockito.verify(userRepository).findById(1L);
        Mockito.verify(userRepository).save(any(UserEntity.class));
    }

    @Test
    public void updateUserUserNotFoundThrowsException() {
        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(UserNotFoundException.class, () -> userRepositoryJpaImpl.updateUser(user));
        assertEquals("Usuario no encontrado con ID: 1", exception.getMessage());
        Mockito.verify(userRepository).findById(1L);
        Mockito.verify(userRepository, Mockito.never()).delete(any(UserEntity.class));
    }
}