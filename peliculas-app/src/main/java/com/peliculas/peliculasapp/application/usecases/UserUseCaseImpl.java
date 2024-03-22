package com.peliculas.peliculasapp.application.usecases;
import com.peliculas.peliculasapp.application.ports.in.UserCaseUse;
import com.peliculas.peliculasapp.application.ports.out.UserRepositoryPort;
import com.peliculas.peliculasapp.domain.models.User;
import com.peliculas.peliculasapp.dto.UserDTO;
import org.modelmapper.ModelMapper;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserUseCaseImpl implements UserCaseUse {

    private final UserRepositoryPort userRepositoryPort;
    private final ModelMapper modelMapper;
    private final ValueOperations<String, Object> valueOperations;

    public UserUseCaseImpl(UserRepositoryPort userRepositoryPort, ModelMapper modelMapper,
                           ValueOperations<String, Object> valueOperations)
    {
        this.userRepositoryPort = userRepositoryPort;
        this.modelMapper = modelMapper;
        this.valueOperations = valueOperations;
    }
    @Override
    public void saveUser(User user) {
        User userMapper = modelMapper.map(user, User.class);
        userRepositoryPort.saveUser(userMapper);
    }

    @Override
    public Optional<UserDTO> findUserById(long userId) {
        Optional<User> user = userRepositoryPort.findUserByID(userId);
        return Optional.of(modelMapper.map(user, UserDTO.class));
    }

    @Override
    public void deleteUser(long userId) {
        userRepositoryPort.deleteUser(userId);
    }

    @Override
    public Optional<User> updateUser(User user) {
        return userRepositoryPort.updateUser(user);
    }
}
