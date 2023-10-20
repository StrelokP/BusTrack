package ru.aptech.bustrack.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.aptech.bustrack.entities.User;
import ru.aptech.bustrack.services.UserService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    protected UserService userService;

    @GetMapping("/user")
    public User getUserById(@RequestParam(name = "id") Long id) {
        Optional<User> user = userService.getUserById(id);
        return user.orElse(null);
    }

    @GetMapping("/users")
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/cipher")
    public String testCipher(@RequestParam(name = "password") String password) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder.encode(password);
    }

    //TODO: login и reg свзать с стандартной логикой Spring Security

}
