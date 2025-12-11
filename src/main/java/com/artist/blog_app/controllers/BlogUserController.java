package com.artist.blog_app.controllers;

import com.artist.blog_app.payload.ApiResponse;
import com.artist.blog_app.payload.BlogUserDto;
import com.artist.blog_app.service.BlogUserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/users")
public class BlogUserController {

    private final BlogUserService blogUserService;

    @GetMapping()
    public ResponseEntity<List<BlogUserDto>> getAllUsers(){
        var users = blogUserService.getAllBlogUsers();

        return ResponseEntity.ok(users);
    }

    @GetMapping("{id}")
    public ResponseEntity<BlogUserDto> getUserById(@PathVariable Integer id){
        var user = blogUserService.getBlogUserById(id);

        return ResponseEntity.ok(user);
    }

    @PostMapping()
    public ResponseEntity<BlogUserDto> createUser(@RequestBody BlogUserDto user){
        var newUser = blogUserService.createBlogUser(user);

        return ResponseEntity
                .created(URI.create("/users/" + newUser.getId()))
                .body(newUser);
    }

    @PutMapping("{id}")
    public ResponseEntity<BlogUserDto> updateUserById(@PathVariable Integer id,
                                                      @RequestBody BlogUserDto user){
        var updatedUser = blogUserService.updateBlogUser(user, id);

        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ApiResponse> deleteUserById(@PathVariable Integer id){
        blogUserService.deleteBlogUser(id);

        return ResponseEntity.ok(new ApiResponse("Successfully deleted",true));
    }


}
