package ru.aptech.bustrack.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.aptech.bustrack.entities.Station;
import ru.aptech.bustrack.entities.User;
import ru.aptech.bustrack.repositories.UserRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    /**
     * <b>Возвращает сущность пользователя из БД по его UUID</b>
     * @param id идентификатор пользователя
     * @return сущность пользователя {@link User}
     */
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public String saveUser(User user) {
        boolean isUserExists = userRepository.existsByLoginIgnoreCase(user.getLogin());

        if (isUserExists) {
            throw new RuntimeException("Пользователь с таким логином уже существует");
        }

        userRepository.save(user);
        return user.getLogin();
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }
}
