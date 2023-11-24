package ru.aptech.bustrack.entities.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class JwtRequest {
    private String login;
    private String password;
}
