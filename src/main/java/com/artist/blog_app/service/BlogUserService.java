package com.artist.blog_app.service;

import com.artist.blog_app.entities.BlogUser;
import com.artist.blog_app.mapper.BlogUserMapper;
import com.artist.blog_app.payload.BlogUserDto;
import com.artist.blog_app.repository.BlogUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BlogUserService {

    private final BlogUserRepository blogUserRepository;
    private final BlogUserMapper mapper;

    BlogUserDto createBlogUser(BlogUserDto user){
        BlogUser blogUser = mapper.toEntity(user);
        blogUserRepository.save(blogUser);
        return mapper.toDto(blogUser);
    }

    BlogUserDto updateBlogUser(BlogUserDto blogUserDto, Integer id){
        return null;
    }

    BlogUserDto getBlogUserById(Integer id){
        return null;
    }

    List<BlogUserDto> getAllBlogUsers(){
        return List.of();
    }

    String deleteBlogUser(Integer id){
        blogUserRepository.deleteById(id);
        return "Successfully deleted";
    }

}
