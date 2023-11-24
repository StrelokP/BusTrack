package ru.aptech.bustrack.services;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.aptech.bustrack.config.jwt.JwtTokenUtil;
import ru.aptech.bustrack.entities.Role;
import ru.aptech.bustrack.entities.User;
import ru.aptech.bustrack.entities.dto.JwtRequest;
import ru.aptech.bustrack.entities.dto.JwtResponse;
import ru.aptech.bustrack.repositories.UserRepository;

import javax.persistence.EntityNotFoundException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@SuppressWarnings("unused")
@Service
public class UserService {
    @SuppressWarnings("unused")
    @Autowired
    private UserRepository userRepository;

    @Autowired
    @Qualifier("userDetailsService")
    private CustomUserDetailsService userDetailsService;

    @Autowired
    @Qualifier("passwordEncoder")
    private PasswordEncoder passwordEncoder;

    @Autowired
    private MailerService mailerService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    @Qualifier("authenticationManager")
    private AuthenticationManager authenticationManager;

    /**
     * <b>Возвращает сущность пользователя из БД по его UUID</b>
     * @param id идентификатор пользователя
     * @return сущность пользователя {@link User}
     */
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public void registerUser(User user) {
        boolean isUserExists = userRepository.existsByLoginIgnoreCase(user.getLogin());

        if (isUserExists) {
            throw new EntityNotFoundException("Пользователь с таким логином уже существует");
        }

        if (StringUtils.isEmpty(user.getLogin())) {
            throw new IllegalArgumentException("Логин не может быть пустым");
        }

        if (StringUtils.isEmpty(user.getPassword())) {
            throw new IllegalArgumentException("Пароль не может быть пустым");
        }

        if (StringUtils.length(user.getLogin()) < 4 || StringUtils.length(user.getLogin()) > 12) {
            throw new IllegalArgumentException("Длина логина 4-12 символов");
        }

        if (StringUtils.length(user.getPassword()) < 8 || StringUtils.length(user.getPassword()) > 20) {
            throw new IllegalArgumentException("Длина пароля 8-20 символов");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Role userRole = new Role();
        userRole.setId(3L);
        user.setRoles(Collections.singleton(userRole));
        userRepository.save(user);

        /*mailerService.send("",
                "Спасибо за регистрацию!",
                "Текст письма");*/
    }

    public ResponseEntity auth(JwtRequest jwtRequest) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(
                jwtRequest.getLogin());
        if (userDetails == null) {
            throw new UsernameNotFoundException("User not found");
        }
        if (!passwordEncoder.matches(jwtRequest.getPassword(),
                userDetails.getPassword())) {
            throw new BadCredentialsException("Invalid credentials");
        }

        Authentication authentication =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                jwtRequest.getLogin(),
                                jwtRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtTokenUtil.generateJwtToken(authentication);

        return ResponseEntity.ok().body(new JwtResponse(jwt,
                jwtRequest.getLogin(),
                ((User) userDetails).getRoles().stream().findFirst().get(),
                ((User) userDetails).getId()));
    }

    public void updateUser(User user) {
        userRepository.save(user);
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }
}
