package ru.aptech.bustrack.config;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomAuthFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response,
                                        AuthenticationException exception) throws IOException {
        if (exception instanceof UsernameNotFoundException) {
            response.sendError(HttpStatus.BAD_REQUEST.value(), Constants.USER_NOT_FOUND_MESSAGE);
        } else if (exception instanceof BadCredentialsException) {
            response.sendError(HttpStatus.BAD_REQUEST.value(), Constants.BAD_CREDENTIALS_MESSAGE);
        }
    }
}
