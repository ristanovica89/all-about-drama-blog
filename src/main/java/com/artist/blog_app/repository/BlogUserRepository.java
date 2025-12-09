package com.artist.blog_app.repository;

import com.artist.blog_app.entities.BlogUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogUserRepository extends JpaRepository<BlogUser, Integer> {
}
