package com.artist.blog_app.repository;

import com.artist.blog_app.entities.BlogUser;
import com.artist.blog_app.entities.Category;
import com.artist.blog_app.entities.Post;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface PostRepository extends JpaRepository<Post, Integer> {


    Page<Post> findByBlogUser(BlogUser blogUser, Pageable pageable);
    Page<Post> findByCategory(Category category, Pageable pageable);

    @Query("""
        SELECT p FROM Post p
        JOIN p.category c
        WHERE (
            LOWER(p.title) LIKE LOWER(CONCAT('%', :keyword, '%'))
            OR LOWER(p.content) LIKE LOWER(CONCAT('%', :keyword, '%'))
            OR LOWER(c.title) LIKE LOWER(CONCAT('%', :keyword, '%'))
        )
        """)
    Page<Post> searchPosts(Pageable pageable, @Param("keyword") String keyword);

}
