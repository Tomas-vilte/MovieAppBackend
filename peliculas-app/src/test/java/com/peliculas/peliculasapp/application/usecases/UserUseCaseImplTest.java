package com.peliculas.peliculasapp.application.usecases;
import com.peliculas.peliculasapp.application.ports.out.UserRepositoryPort;
import com.peliculas.peliculasapp.domain.dto.UserDTO;
import com.peliculas.peliculasapp.domain.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class UserUseCaseImplTest {
    private UserRepositoryPort userRepositoryPort;
    private ModelMapper modelMapper;
    private UserUseCaseImpl userUseCase;

    @BeforeEach
    public void setUp() {
        userRepositoryPort = Mockito.mock(UserRepositoryPort.class);
        modelMapper = new ModelMapper();
        userUseCase = new UserUseCaseImpl(userRepositoryPort, modelMapper);
    }

    private User createUser(long id, String username, String password, String email) {
        User user = new User();
        user.setId(id);
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        return user;
    }

    @Test
    public void testFindUserById() {
        // arrange
        long userId = 1;
        User user = createUser(userId, "test", "test", "test@email.com");
        Mockito.when(userRepositoryPort.findUserByID(userId)).thenReturn(Optional.of(user));

        // act
        Optional<UserDTO> result = userUseCase.findUserById(userId);

        // assert
        assertTrue(result.isPresent());
        assertEquals(user.getId(), result.get().getId());
        assertEquals(user.getUsername(), result.get().getUsername());
        assertEquals(user.getEmail(), result.get().getEmail());
    }

    @Test
    public void testDeleteUser() {
        // arrange
        long userId = 1;
        User user = createUser(userId, "testuser", "password123", "test@example.com");
        Mockito.when(userRepositoryPort.deleteUser(userId)).thenReturn(Optional.of(user));

        // act
        Optional<UserDTO> result = userUseCase.deleteUser(userId);

        // assert
        assertTrue(result.isPresent());
        assertEquals(user.getId(), result.get().getId());
        assertEquals(user.getUsername(), result.get().getUsername());
        assertEquals(user.getEmail(), result.get().getEmail());
    }

    @Test
    public void testUpdateUser() {
        // Arrange
        User user = createUser(1L, "testuser", "password123", "test@example.com");
        Mockito.when(userRepositoryPort.updateUser(user)).thenReturn(Optional.of(user));

        // act
        Optional<UserDTO> result = userUseCase.updateUser(user);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(user.getId(), result.get().getId());
        assertEquals(user.getUsername(), result.get().getUsername());
        assertEquals(user.getEmail(), result.get().getEmail());
    }

    @Test
    public void testSaveUser() {
        // Arrange
        User user = createUser(1L, "testuser", "password123", "test@example.com");
        Mockito.when(userRepositoryPort.saveUser(user)).thenReturn(Optional.of(user));

        // Act
        Optional<UserDTO> result = userUseCase.saveUser(user);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(user.getId(), result.get().getId());
        assertEquals(user.getUsername(), result.get().getUsername());
        assertEquals(user.getEmail(), result.get().getEmail());
    }

}