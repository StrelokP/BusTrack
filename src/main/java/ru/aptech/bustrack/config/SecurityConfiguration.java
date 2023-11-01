package ru.aptech.bustrack.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import ru.aptech.bustrack.services.CustomUserDetailsService;

@SuppressWarnings("unused")
@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Autowired
    private CustomAuthProvider customAuthProvider;

    @Bean
    public CustomUserDetailsService userDetailsService() {
        return new CustomUserDetailsService();
    }

    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler() {
        return new CustomAuthFailureHandler();
    }

    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        return new CustomAuthSuccessHandler();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authProvider() {
        CustomAuthProvider authProvider = new CustomAuthProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests
                        .antMatchers("/css/**").permitAll()
                        .antMatchers("/img/**").permitAll()
                        .antMatchers("/js/**").permitAll()
                        .antMatchers("/api/user").permitAll()
                        .antMatchers("/user").hasAuthority(Constants.ROLE_USER_NAME)
                        .antMatchers("/admin").hasAuthority(Constants.ROLE_ADMIN_NAME)
                        .antMatchers("/").permitAll()
                        .anyRequest().authenticated()
                )
                .authenticationProvider(authProvider())
                .formLogin((form) -> form
                        .failureHandler(authenticationFailureHandler())
                        .successHandler(authenticationSuccessHandler())
                        .loginPage("/")
                        .loginProcessingUrl("/login")
                        .usernameParameter("login")
                        .permitAll()
                )
                .logout((logout) -> logout.permitAll());

        http.csrf().disable();
        http.cors().disable();
        return http.build();
    }
}
