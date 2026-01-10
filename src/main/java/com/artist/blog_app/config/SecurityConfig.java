package com.artist.blog_app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) {
        http
                .authorizeHttpRequests(auth -> auth

                        .requestMatchers(HttpMethod.GET,
                                "/posts",
                                "/posts/user/**",
                                "/posts/category/**",
                                "/posts/search"
                        ).permitAll()

                        .requestMatchers(HttpMethod.GET,
                                "/categories/**"
                        ).permitAll()

                        .requestMatchers(HttpMethod.GET,
                                "/comments/post/**",
                                "/comments/*/replies"
                        ).permitAll()
                        .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }
}
