package com.artist.blog_app.repository;

import com.artist.blog_app.entities.BlogUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BlogUserRepository extends JpaRepository<BlogUser, Integer> {

    Optional<BlogUser> findByEmail(String email);
}
