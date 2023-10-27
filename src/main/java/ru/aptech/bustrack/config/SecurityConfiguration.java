package ru.aptech.bustrack.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    private static final String[] AUTH_LIST = {
            // -- swagger ui
            "**/swagger-resources/**",
            "/swagger-ui.html",
            "/v2/api-docs",
            "/webjars/**"
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests
                        .antMatchers("/css/**").permitAll()//
                        .antMatchers("/img/**").permitAll()
                        .antMatchers("/js/**").permitAll()
                        .antMatchers("/reg").permitAll()
                        .antMatchers("/api/**").permitAll()
                        .antMatchers("/user").permitAll()
                        .antMatchers("/admin").permitAll()
                        .antMatchers("/").permitAll()
                        .antMatchers(AUTH_LIST).authenticated()
                        .anyRequest().authenticated()
                )
                .formLogin((form) -> form
                        .permitAll()
                )
                .logout((logout) -> logout.permitAll());

        http.csrf().disable();
        http.headers().frameOptions().disable();
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user =
                User.withDefaultPasswordEncoder()
                        .username("user")
                        .password("password")
                        .roles("USER")
                        .build();

        return new InMemoryUserDetailsManager(user);
    }
}
