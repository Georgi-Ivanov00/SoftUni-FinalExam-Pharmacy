package main.User.service;

import jakarta.transaction.Transactional;
import main.User.model.User;
import main.User.repository.UserRepository;
import main.exception.EmailAlreadyExistException;
import main.web.dto.registerRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
@Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    @CacheEvict(value = "users", allEntries = true)
    public User register(registerRequest registerRequest) {

        Optional<User> optionalUser = userRepository.findByEmail(registerRequest.getEmail());
        if (optionalUser.isPresent()) {
            throw new EmailAlreadyExistException("User with [%s] email already exist.".formatted(registerRequest.getEmail()));
        }

        User user = User.builder()
                .firstName(registerRequest.getFirstName())
                .lastName(registerRequest.getLastName())
                .password(registerRequest.getPassword())
                .email(registerRequest.getEmail())
                .isAdmin(false)
                .build();

        user = userRepository.save(user);

        return user;
    }
}
