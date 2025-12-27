package com.artist.blog_app.repository;

import com.artist.blog_app.entities.BlogUser;
import com.artist.blog_app.entities.Category;
import com.artist.blog_app.entities.Post;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Integer> {

    Optional<Post> findPostByTitle(String title);

    Page<Post> findByBlogUser(BlogUser blogUser, Pageable pageable);
    Page<Post> findByCategory(Category category, Pageable pageable);
}
