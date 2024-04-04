package com.peliculas.peliculasapp.application.ports.in;
import com.peliculas.peliculasapp.domain.models.User;
import com.peliculas.peliculasapp.domain.dto.UserDTO;
import java.util.Optional;

public interface UserCaseUse {
    Optional<UserDTO> saveUser(User user);
    Optional<UserDTO> findUserById(long userId);
    Optional<UserDTO> deleteUser(long userId);
    Optional<UserDTO> updateUser(User user);
}
