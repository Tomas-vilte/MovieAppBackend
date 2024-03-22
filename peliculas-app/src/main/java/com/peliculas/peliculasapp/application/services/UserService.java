package com.peliculas.peliculasapp.application.services;
import com.peliculas.peliculasapp.application.ports.in.UserCaseUse;
import com.peliculas.peliculasapp.domain.models.User;
import com.peliculas.peliculasapp.dto.UserDTO;
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

    public void saveUser(User user) {
        userCaseUse.saveUser(user);
    }

    public void deleteUser(long userId) {
        userCaseUse.deleteUser(userId);
    }

}
