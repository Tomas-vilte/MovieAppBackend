package com.peliculas.peliculasapp.application.services;
import com.peliculas.peliculasapp.application.ports.in.UserCaseUse;
import com.peliculas.peliculasapp.domain.models.User;
import com.peliculas.peliculasapp.domain.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserService {
    private final UserCaseUse userCaseUse;

    @Autowired
    public UserService(UserCaseUse userCaseUse) {
        this.userCaseUse = userCaseUse;
    }

    public Optional<UserDTO> findUserById(long userId) {
        return userCaseUse.findUserById(userId);
    }

    public Optional<UserDTO> saveUser(User user) {
        return userCaseUse.saveUser(user);
    }

    public Optional<UserDTO> deleteUser(long userId) {
       return userCaseUse.deleteUser(userId);
    }

    public Optional<UserDTO> updateUser(User user) {
        return userCaseUse.updateUser(user);
    }

}
