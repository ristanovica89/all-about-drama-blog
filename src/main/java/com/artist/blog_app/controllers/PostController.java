package com.artist.blog_app.controllers;

import com.artist.blog_app.payload.PostDto;
import com.artist.blog_app.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@AllArgsConstructor
@RestController
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    @PostMapping("/users/{userId}/categories/{categoryId}")
    public ResponseEntity<PostDto> createPost(
            @RequestBody  PostDto postDto,
            @PathVariable Integer userId,
            @PathVariable Integer categoryId
    ) {
        PostDto newPost = postService.createPost(postDto, userId, categoryId);

        return ResponseEntity
                .created(URI.create("/posts/" + newPost.getId()))
                .body(newPost);
    }
}
