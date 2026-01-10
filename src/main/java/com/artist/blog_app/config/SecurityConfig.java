package com.artist.blog_app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) {

        http
                .csrf(AbstractHttpConfigurer::disable) // disable CSRF for test
                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers(HttpMethod.GET, "/api/v1/blog/posts/**").permitAll()
//                        .requestMatchers(HttpMethod.GET, "/api/v1/blog/categories/**").permitAll()
//                        .requestMatchers(HttpMethod.GET, "/api/v1/blog/comments/**").permitAll()
//                        .requestMatchers(HttpMethod.GET, "/api/v1/blog/users/**").permitAll()
                        .requestMatchers("/public").permitAll() // dummy test endpoint
                        .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults());
                //.anonymous(Customizer.withDefaults());

        return http.build();
    }
}
