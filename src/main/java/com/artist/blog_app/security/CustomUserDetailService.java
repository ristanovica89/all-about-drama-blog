package com.artist.blog_app.security;

import com.artist.blog_app.exceptions.ResourceNotFoundException;
import com.artist.blog_app.repository.BlogUserRepository;
import lombok.AllArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    private final BlogUserRepository blogUserRepository;

    @Override
    @NonNull
    public UserDetails loadUserByUsername(@NonNull String email) throws UsernameNotFoundException {


        return blogUserRepository.findByEmail(email)
                .orElseThrow(()-> new ResourceNotFoundException("User", "email: " + email, 0));

    }
}
