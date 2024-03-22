package com.peliculas.peliculasapp.infrastructure.controllers;
import com.peliculas.peliculasapp.domain.models.User;
import com.peliculas.peliculasapp.dto.UserDTO;
import com.peliculas.peliculasapp.application.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Optional<UserDTO> findUserById(@PathVariable long userId) {
        return userService.findUserById(userId);
    }

    @PostMapping("/users")
    public void saveUser(@RequestBody User user) {
        userService.saveUser(user);
    }

    @DeleteMapping("/users/{userId}")
    public void deleteUser(@PathVariable long userId) {
        userService.deleteUser(userId);
    }
}
