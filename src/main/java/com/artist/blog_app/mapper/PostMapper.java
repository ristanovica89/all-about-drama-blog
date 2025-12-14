package com.artist.blog_app.mapper;

import com.artist.blog_app.entities.BlogUser;
import com.artist.blog_app.entities.Category;
import com.artist.blog_app.entities.Post;
import com.artist.blog_app.payload.PostDto;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class PostMapper {

    private final ModelMapper modelMapper;

    public PostDto toDto(Post post){
        return modelMapper.map(post, PostDto.class);
    }

//    public Post toEntity(PostDto postDto){
//        return  modelMapper.map(postDto, Post.class);
//    }

    public Post toEntity(PostDto dto, BlogUser user, Category category) {
        Post post = modelMapper.map(dto, Post.class);
        post.setBlogUser(user);
        post.setCategory(category);
        return post;
    }

    public void updateEntity(PostDto dto, Post post) {
        modelMapper.map(dto, post);
    }
}
