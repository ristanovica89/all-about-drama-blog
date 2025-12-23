package com.artist.blog_app.service;

import com.artist.blog_app.entities.BlogUser;
import com.artist.blog_app.exceptions.ResourceNotFoundException;
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

    public BlogUserDto createBlogUser(BlogUserDto user){
        BlogUser blogUser = mapper.toEntity(user);
        blogUserRepository.save(blogUser);

        return mapper.toDto(blogUser);
    }

    public BlogUserDto updateBlogUser(BlogUserDto blogUserDto, Integer id){
        var existingBlogUser = blogUserRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User ","UserId",id));

        mapper.updateEntity(blogUserDto,existingBlogUser);
        blogUserRepository.save(existingBlogUser);

        return mapper.toDto(existingBlogUser);
    }

    public BlogUserDto getBlogUserById(Integer id){
        var blogUser = blogUserRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User ","UserId",id));

        return mapper.toDto(blogUser);
    }

    public List<BlogUserDto> getAllBlogUsers(){
        return blogUserRepository.findAll()
                .stream()
                .map(mapper::toDto)
                .toList();
    }

    public void deleteBlogUser(Integer id){

        var blogUser = blogUserRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User ","UserId",id));

        blogUserRepository.delete(blogUser);
    }

}
