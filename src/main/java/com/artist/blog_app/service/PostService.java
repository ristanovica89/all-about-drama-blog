package com.artist.blog_app.service;

import com.artist.blog_app.entities.BlogUser;
import com.artist.blog_app.entities.Category;
import com.artist.blog_app.entities.Post;
import com.artist.blog_app.exceptions.ResourceNotFoundException;
import com.artist.blog_app.mapper.PostMapper;
import com.artist.blog_app.payload.PostDto;
import com.artist.blog_app.repository.BlogUserRepository;
import com.artist.blog_app.repository.CategoryRepository;
import com.artist.blog_app.repository.PostRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final BlogUserRepository blogUserRepository;
    private final CategoryRepository categoryRepository;
    private final PostMapper mapper;

    public List<PostDto> getAllPosts(){
        return postRepository.findAll()
                .stream()
                .map(mapper::toDto)
                .toList();
    }

    public PostDto getPostById(Integer id){
        var post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post ","Post Id",id));

        return mapper.toDto(post);
    }

    public PostDto getPostByTitle(String title){
        var post = postRepository.findPostByTitle(title)
                .orElseThrow(() -> new ResourceNotFoundException("Post ","Post Id",title));

        return mapper.toDto(post);
    }

    public List<PostDto> getAllPostsFromUser(BlogUser user){
        return postRepository.findByBlogUser(user)
                .stream()
                .map(mapper::toDto)
                .toList();

    }

    public List<PostDto> getAllPostsOfCategory(Category category){
        return postRepository.findByCategory(category)
                .stream()
                .map(mapper::toDto)
                .toList();
    }


    public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId){
        BlogUser blogUser = blogUserRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User ","User Id",userId));
        Category category = categoryRepository.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category ","Category Id",categoryId));

        Post post = mapper.toEntity(postDto, blogUser, category);
        post.setImgPath("/default.png");
        postRepository.save(post);

        return mapper.toDto(post);
    }

    public PostDto updatePost(PostDto postDto, Integer id){
        var existingPost = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category ","Category Id",id));

        mapper.updateEntity(postDto,existingPost);
        postRepository.save(existingPost);

        return mapper.toDto(existingPost);
    }



    public void deletePost(Integer id){
        postRepository.deleteById(id);
    }

}
