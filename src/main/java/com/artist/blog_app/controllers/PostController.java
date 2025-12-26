package com.artist.blog_app.controllers;

import com.artist.blog_app.payload.ApiResponse;
import com.artist.blog_app.payload.PostDto;
import com.artist.blog_app.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    @GetMapping()
    public ResponseEntity<List<PostDto>> getAllPosts(
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "5", required = false)   Integer pageSize){

        var allPosts = postService.getAllPosts(pageNumber, pageSize);
        return ResponseEntity
                .ok(allPosts);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<PostDto>> getAllPostsUserId(@PathVariable Integer userId){

        var posts = postService.getAllPostsByUserId(userId);
        return ResponseEntity
                .ok(posts);
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<PostDto>> getAllPostsByCategoryId(@PathVariable Integer categoryId){

        var posts = postService.getAllPostsByCategoryId(categoryId);
        return ResponseEntity
                .ok(posts);
    }

    @PostMapping("/user/{userId}/category/{categoryId}")
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

    @PutMapping("{postId}")
    public ResponseEntity<PostDto> updatePost(
            @RequestBody PostDto postDto,
            @PathVariable Integer postId
    ){
        var updatedPost = postService.updatePost(postDto, postId);

        return ResponseEntity
                .ok(updatedPost);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ApiResponse> deletePostById(@PathVariable Integer id){
        postService.deletePost(id);

        return ResponseEntity.ok(new ApiResponse("Successfully deleted",true));
    }

}
