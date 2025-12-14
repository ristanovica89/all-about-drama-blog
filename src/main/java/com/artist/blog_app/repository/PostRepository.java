package com.artist.blog_app.repository;

import com.artist.blog_app.entities.BlogUser;
import com.artist.blog_app.entities.Category;
import com.artist.blog_app.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Integer> {

    Optional<Post> findPostByTitle(String title);

    List<Post> findByBlogUser(BlogUser blogUser);

    List<Post> findByCategory(Category category);
}
