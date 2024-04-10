package com.peliculas.peliculasapp.application.services;
import com.peliculas.peliculasapp.application.ports.in.UserCaseUse;
import com.peliculas.peliculasapp.domain.dto.UserDTO;
import com.peliculas.peliculasapp.domain.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {
    private UserCaseUse userCaseUse;
    private UserService userService;

    @BeforeEach
    public void setUp() {
        userCaseUse = Mockito.mock(UserCaseUse.class);
        userService = new UserService(userCaseUse);
    }

    @Test
    public void testFindUserById() {
        // arrange
        long userId = 1;
        UserDTO expectedDto = createDummyUserDTO();
        Mockito.when(userCaseUse.findUserById(userId)).thenReturn(Optional.of(expectedDto));

        // act
        Optional<UserDTO> result = userService.findUserById(userId);

        // assert
        assertTrue(result.isPresent());
        UserDTO actualDto = result.get();
        assertEquals(expectedDto.getId(), actualDto.getId());
        assertEquals(expectedDto.getUsername(), actualDto.getUsername());
        assertEquals(expectedDto.getEmail(), actualDto.getEmail());
    }

    @Test
    public void testSaveUser() {
        // arrange
        User user = createUser();
        UserDTO expectedDto = createDummyUserDTO();
        Mockito.when(userCaseUse.saveUser(user)).thenReturn(Optional.of(expectedDto));

        // act
        Optional<UserDTO> result = userService.saveUser(user);

        // assert
        assertTrue(result.isPresent());
        UserDTO actualDto = result.get();
        assertEquals(expectedDto.getId(), actualDto.getId());
        assertEquals(expectedDto.getUsername(), actualDto.getUsername());
        assertEquals(expectedDto.getEmail(), actualDto.getEmail());

    }

    @Test
    public void testDeleteUser() {
        // arrange
        long userId = 1;
        UserDTO expectedDto = createDummyUserDTO();
        Mockito.when(userCaseUse.deleteUser(userId)).thenReturn(Optional.of(expectedDto));

        // act
        Optional<UserDTO> result = userService.deleteUser(userId);

        // Assert
        assertTrue(result.isPresent());
        UserDTO actualDto = result.get();
        assertEquals(expectedDto.getId(), actualDto.getId());
        assertEquals(expectedDto.getUsername(), actualDto.getUsername());
        assertEquals(expectedDto.getEmail(), actualDto.getEmail());
    }

    @Test
    public void testUpdateUser() {
        // arrange
        User user = createUser();
        UserDTO expectedDto = createDummyUserDTO();
        Mockito.when(userCaseUse.updateUser(user)).thenReturn(Optional.of(expectedDto));

        // act
        Optional<UserDTO> result = userService.updateUser(user);

        // assert
        assertTrue(result.isPresent());
        UserDTO actualDto = result.get();
        assertEquals(expectedDto.getId(), actualDto.getId());
        assertEquals(expectedDto.getUsername(), actualDto.getUsername());
        assertEquals(expectedDto.getEmail(), actualDto.getEmail());
    }

    private UserDTO createDummyUserDTO() {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(1L);
        userDTO.setUsername("dummy_username");
        userDTO.setEmail("dummy@example.com");
        return userDTO;
    }

    private User createUser() {
        User user = new User();
        user.setId(1);
        user.setUsername("textUser");
        user.setPassword("passwordUser");
        user.setEmail("user@email.com");
        return user;
    }
}