package com.peliculas.peliculasapp.infrastructure.rest.controllers;
import com.peliculas.peliculasapp.domain.models.User;
import com.peliculas.peliculasapp.domain.dto.UserDTO;
import com.peliculas.peliculasapp.application.services.UserService;
import com.peliculas.peliculasapp.infrastructure.common.SuccessResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping("api")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> findUserById(@PathVariable long userId) {
        Optional<UserDTO> user = userService.findUserById(userId);
        SuccessResponse successResponseUser = new SuccessResponse(HttpStatus.OK.value(), "Usuario obtenido con exito", user);
        return ResponseEntity.ok(successResponseUser);
    }

    @PostMapping("/users")
    public ResponseEntity<?> createUser(@RequestBody User user) {
        Optional<UserDTO> saveUser = userService.saveUser(user);
        SuccessResponse successResponseUser = new SuccessResponse(HttpStatus.OK.value(), "Usuario creado con exito", saveUser);
        return ResponseEntity.ok(successResponseUser);
    }

    @DeleteMapping("/users/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable long userId) {
        Optional<UserDTO> userDelete = userService.deleteUser(userId);
        SuccessResponse successResponseUser = new SuccessResponse(HttpStatus.OK.value(), "Usuario eliminado con exito", userDelete);
        return ResponseEntity.ok(successResponseUser);
    }

    @PutMapping("/users/{userId}")
    public ResponseEntity<?> updateUser(@PathVariable long userId, @RequestBody User user) {
        user.setId(userId);
        Optional<UserDTO> updateUser = userService.updateUser(user);
        SuccessResponse successResponse = new SuccessResponse(HttpStatus.OK.value(), "Usuario actualizado con exito", updateUser);
        return ResponseEntity.ok(successResponse);
    }
}
