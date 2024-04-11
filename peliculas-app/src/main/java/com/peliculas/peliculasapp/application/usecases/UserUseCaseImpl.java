package com.peliculas.peliculasapp.application.usecases;
import com.peliculas.peliculasapp.application.ports.in.UserCaseUse;
import com.peliculas.peliculasapp.application.ports.out.UserRepositoryPort;
import com.peliculas.peliculasapp.domain.models.User;
import com.peliculas.peliculasapp.domain.dto.UserDTO;
import com.peliculas.peliculasapp.infrastructure.adapter.entities.UserEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserUseCaseImpl implements UserCaseUse {

    private final UserRepositoryPort userRepositoryPort;
    private final ModelMapper modelMapper;

    public UserUseCaseImpl(UserRepositoryPort userRepositoryPort, ModelMapper modelMapper)
    {
        this.userRepositoryPort = userRepositoryPort;
        this.modelMapper = modelMapper;
    }
    @Override
    public Optional<UserDTO> saveUser(User user) {
        Optional<User> saveUser = userRepositoryPort.saveUser(user);
        return Optional.of(modelMapper.map(saveUser, UserDTO.class));
    }

    @Override
    public Optional<UserDTO> findUserById(long userId) {
        Optional<User> user = userRepositoryPort.findUserByID(userId);
        return Optional.of(modelMapper.map(user, UserDTO.class));
    }

    @Override
    public Optional<UserDTO> deleteUser(long userId) {
        Optional<User> deleteUser = userRepositoryPort.deleteUser(userId);
        return Optional.of(modelMapper.map(deleteUser, UserDTO.class));
    }

    @Override
    public Optional<UserDTO> updateUser(User user) {
        Optional<User> updateUser = userRepositoryPort.updateUser(user);
        return updateUser.map(u -> modelMapper.map(u, UserDTO.class));
    }
}
