package ru.aptech.bustrack.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.aptech.bustrack.entities.User;
import ru.aptech.bustrack.repositories.UserRepository;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User getUserById(UUID id) {
        Optional<User> user = userRepository.findById(id);

        if (user.isPresent()) {
            return user.get();
        }
        throw new EntityNotFoundException("Пользователя с таким id не существует");
    }
}
