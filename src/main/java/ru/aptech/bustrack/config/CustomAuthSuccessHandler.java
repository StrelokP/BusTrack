package ru.aptech.bustrack.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomAuthSuccessHandler implements AuthenticationSuccessHandler {

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        if (authentication.getAuthorities().stream()
                .anyMatch(e -> e.getAuthority().equals(Constants.ROLE_ADMIN_NAME))) {
            redirectStrategy.sendRedirect(request, response, Constants.ADMIN_PAGE_URL);
        } else if (authentication.getAuthorities().stream()
                .anyMatch(e -> e.getAuthority().equals(Constants.ROLE_MODERATOR_NAME))){
            //TODO: сделать модераторов
        } else {
            redirectStrategy.sendRedirect(request, response, Constants.USER_PAGE_URL);
        }
    }
}
