package com.artist.blog_app.controllers;


import com.artist.blog_app.payload.CommentDto;
import com.artist.blog_app.payload.CommentResponse;
import com.artist.blog_app.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/comments")
public class CommentController {

    private final CommentService commentService;

    @GetMapping()
    public ResponseEntity<CommentResponse> getRootComments(
            @RequestParam Integer postId,
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "50", required = false) Integer pageSize
    ) {
        var comments = commentService.getRootComments(postId, pageNumber, pageSize);

        return ResponseEntity.ok(comments);
    }

    @GetMapping("/admin/all")
    public  ResponseEntity<CommentResponse> getAllComments(
            @RequestParam Integer postId,
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "50", required = false) Integer pageSize
    ) {
        var comments = commentService.getRootComments(postId, pageNumber, pageSize);

        return ResponseEntity.ok(comments);
    }

    @GetMapping("/{parentId}/replies")
    public ResponseEntity<List<CommentDto>> getReplies(@PathVariable Integer parentId){
        var replies = commentService.getRepliesForComment(parentId);

        return ResponseEntity.ok(replies);
    }


}
