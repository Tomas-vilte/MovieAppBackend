package com.peliculas.peliculasapp.application.ports.in;
import com.peliculas.peliculasapp.domain.models.User;
import com.peliculas.peliculasapp.dto.UserDTO;
import java.util.Optional;

public interface UserCaseUse {
    void saveUser(User user);
    Optional<UserDTO> findUserById(long userId);
    void deleteUser(long userId);
    void updateUser(User user);
}
