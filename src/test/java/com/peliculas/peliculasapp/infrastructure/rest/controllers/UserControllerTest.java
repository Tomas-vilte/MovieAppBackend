package com.peliculas.peliculasapp.infrastructure.rest.controllers;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.peliculas.peliculasapp.application.services.UserService;
import com.peliculas.peliculasapp.domain.dto.UserDTO;
import com.peliculas.peliculasapp.domain.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith({SpringExtension.class, MockitoExtension.class})
@WebMvcTest(UserControllerTest.class)
class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserService userService;
    private long userId;
    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(new UserController(userService)).build();
        userId = 1;
    }

    @Test
    public void findUserByIdSuccess() throws Exception {
        // arrange
        UserDTO userDTO = createUserDto();
        Mockito.when(userService.findUserById(userId)).thenReturn(Optional.of(userDTO));

        // act y assert
        mockMvc.perform(get("/api/user/{userId}", userId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Usuario obtenido con exito"))
                .andExpect(jsonPath("$.data.id").value(userId))
                .andExpect(jsonPath("$.data.username").value("userNameTest"))
                .andExpect(jsonPath("$.data.email").value("userTest@mail.com"));
    }

    @Test
    public void createUserSuccess() throws Exception {
        // arrange
        User user = createUser();
        UserDTO userDTO = createUserDto();
        Mockito.when(userService.saveUser(user)).thenReturn(Optional.of(userDTO));
        String requestBody = objectMapper.writeValueAsString(user);

        // act y assert
        mockMvc.perform(post("/api/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Usuario creado con exito"))
                .andExpect(jsonPath("$.data.id").value(userId))
                .andExpect(jsonPath("$.data.username").value("userNameTest"))
                .andExpect(jsonPath(".data.email").value("userTest@mail.com"));
    }

    @Test
    public void deleteUserSuccess() throws Exception {
        // arrange
        UserDTO userDTO = createUserDto();
        Mockito.when(userService.deleteUser(userId)).thenReturn(Optional.of(userDTO));

        // act y assert
        mockMvc.perform(delete("/api/users/{userId}", userId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Usuario eliminado con exito"))
                .andExpect(jsonPath("$.data.id").value(userId))
                .andExpect(jsonPath("$.data.username").value("userNameTest"))
                .andExpect(jsonPath("$.data.email").value("userTest@mail.com"));
    }

    @Test
    public void updateUserSuccess() throws Exception {
        // arrange
        User updatedUser = updateUser();
        UserDTO updatedUserDTO = createUserDto();
        Mockito.when(userService.updateUser(updatedUser)).thenReturn(Optional.of(updatedUserDTO));
        String requestBody = objectMapper.writeValueAsString(updatedUser);

        // act y assert
        mockMvc.perform(put("/api/users/{userId}", updatedUser.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Usuario actualizado con exito"))
                .andExpect(jsonPath("$.data.id").value(updatedUserDTO.getId()))
                .andExpect(jsonPath("$.data.username").value(updatedUserDTO.getUsername()))
                .andExpect(jsonPath("$.data.email").value(updatedUserDTO.getEmail()));

        Mockito.verify(userService).updateUser(updatedUser);
    }

    private UserDTO createUserDto() {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(userId);
        userDTO.setUsername("userNameTest");
        userDTO.setEmail("userTest@mail.com");
        return userDTO;
    }

    private User updateUser() {
        User user = new User();
        user.setId(userId);
        user.setUsername("updateUserNameTest123");
        user.setEmail("updateUserTest@mail.com");
        user.setPassword("passwordUpdate");
        return user;
    }

    private User createUser() {
        User user = new User();
        user.setId(userId);
        user.setUsername("userNameTest");
        user.setEmail("userTest@mail.com");
        user.setPassword("passwordUser");
        return user;
    }
}