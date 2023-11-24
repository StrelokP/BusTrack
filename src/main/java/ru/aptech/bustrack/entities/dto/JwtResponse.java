package ru.aptech.bustrack.entities.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ru.aptech.bustrack.entities.Role;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class JwtResponse {
    private String token;
    private String login;
    private Role role;
    private Long id;
}
