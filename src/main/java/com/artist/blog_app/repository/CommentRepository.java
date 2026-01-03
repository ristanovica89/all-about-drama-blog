package com.artist.blog_app.repository;

import com.artist.blog_app.entities.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

    Page<Comment> findByPost_IdAndParentIsNull(Integer postId, Pageable pageable);
    Page<Comment> findByPost_Id(Integer postId, Pageable pageable);

    List<Comment> findByParent_Id(Integer parentId);

}
