package com.artist.blog_app.controllers;

import com.artist.blog_app.payload.ApiResponse;
import com.artist.blog_app.payload.PostDto;
import com.artist.blog_app.payload.PostResponse;
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

    @GetMapping()
    public ResponseEntity<PostResponse> getAllPosts(
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "5", required = false)   Integer pageSize,
            @RequestParam(value = "sortBy", defaultValue = "addedDate", required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "desc", required = false) String sortDir){

        var allPosts = postService.getAllPosts(pageNumber, pageSize, sortBy, sortDir);
        return ResponseEntity
                .ok(allPosts);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<PostResponse> getAllPostsUserId(
            @PathVariable Integer userId,
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "5", required = false)   Integer pageSize,
            @RequestParam(value = "sortBy", defaultValue = "addedDate", required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "desc", required = false) String sortDir){

        var posts = postService.getAllPostsByUserId(userId, pageNumber, pageSize, sortBy, sortDir);
        return ResponseEntity
                .ok(posts);
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<PostResponse> getAllPostsByCategoryId(
            @PathVariable Integer categoryId,
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "5", required = false)   Integer pageSize,
            @RequestParam(value = "sortBy", defaultValue = "addedDate", required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "desc", required = false) String sortDir
    ){

        var posts = postService.getAllPostsByCategoryId(categoryId,pageNumber, pageSize, sortBy, sortDir);
        return ResponseEntity
                .ok(posts);
    }

    @GetMapping("/search")
    public ResponseEntity<PostResponse> searchPosts(
            @RequestParam String keyword,
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "5", required = false)   Integer pageSize,
            @RequestParam(value = "sortBy", defaultValue = "addedDate", required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "desc", required = false) String sortDir){

        var allPosts = postService.searchPosts(keyword, pageNumber, pageSize, sortBy, sortDir);
        return ResponseEntity
                .ok(allPosts);
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
