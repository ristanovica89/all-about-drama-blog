package com.artist.blog_app.mapper;

import com.artist.blog_app.entities.Comment;
import com.artist.blog_app.entities.Post;
import com.artist.blog_app.payload.CommentDto;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CommentMapper {

    private final ModelMapper modelMapper;

    public CommentDto toDto(Comment comment){
        if (comment == null) return null;

        CommentDto dto = modelMapper.map(comment, CommentDto.class);
        dto.setParentId(comment.getParent() != null ? comment.getParent().getId() : null);

        return dto;
    }

    public Comment toEntity(CommentDto dto, Post post, Comment parent) {
        Comment comment = new Comment();
        comment.setContent(dto.getContent());
        comment.setPost(post);
        comment.setParent(parent);
        return comment;
    }

    public Comment toEntity(CommentDto dto, Post post) {
        Comment comment = new Comment();
        comment.setContent(dto.getContent());
        comment.setPost(post);
        return comment;
    }

    public void updateEntity(CommentDto dto, Comment comment) {
        if (dto.getContent() != null) {
            comment.setContent(dto.getContent());
        }
    }
}

