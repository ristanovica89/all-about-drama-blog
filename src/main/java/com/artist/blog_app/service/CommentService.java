package com.artist.blog_app.service;

import com.artist.blog_app.entities.Comment;
import com.artist.blog_app.entities.Post;
import com.artist.blog_app.exceptions.ResourceNotFoundException;
import com.artist.blog_app.mapper.CommentMapper;
import com.artist.blog_app.payload.CommentDto;
import com.artist.blog_app.payload.CommentResponse;
import com.artist.blog_app.repository.CommentRepository;
import com.artist.blog_app.repository.PostRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final CommentMapper mapper;

    public CommentResponse getRootComments(Integer postId, Integer pageNumber, Integer pageSize){

        Post existingPost = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post ","Post Id",postId));

        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("createdAt").descending());
        Page<Comment> pageRootComment = commentRepository.findByPost_IdAndParentIsNull(postId, pageable);

        List<CommentDto> commentsDto =  pageRootComment.getContent()
                .stream()
                .map(mapper::toDto)
                .toList();

        return buildCommentResponse(commentsDto, pageRootComment);
    }

    public CommentResponse getAllComments(Integer postId, Integer pageNumber, Integer pageSize) {

        Post existingPost = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post ","Post Id",postId));

        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("createdAt").descending());
        Page<Comment> pageComment = commentRepository.findByPost_Id(postId, pageable);

        List<CommentDto> commentsDto = pageComment.getContent()
                .stream()
                .map(mapper::toDto)
                .toList();

        return buildCommentResponse(commentsDto, pageComment);
    }


    public List<CommentDto> getRepliesForComment(Integer parentId){
        Comment existingComment = commentRepository.findById(parentId)
                .orElseThrow(() -> new ResourceNotFoundException("Comment ","Comment Id", parentId));

        var replies = commentRepository.findByParent_Id(parentId);

        return replies.stream()
                .map(mapper::toDto)
                .toList();
    }

    private CommentResponse buildCommentResponse(List<CommentDto> commentsDto, Page<Comment> pageRootComment){
        CommentResponse commentResponse = new CommentResponse();
        commentResponse.setContent(commentsDto);
        commentResponse.setPageNumber(pageRootComment.getNumber());
        commentResponse.setPageSize(pageRootComment.getSize());
        commentResponse.setTotalElements(pageRootComment.getTotalElements());
        commentResponse.setTotalPages(pageRootComment.getTotalPages());
        commentResponse.setLastPage(pageRootComment.isLast());

        return commentResponse;
    }
}
