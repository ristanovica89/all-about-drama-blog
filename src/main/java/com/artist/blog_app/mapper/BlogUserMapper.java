package com.artist.blog_app.mapper;

import com.artist.blog_app.entities.BlogUser;
import com.artist.blog_app.payload.BlogUserDto;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;

@AllArgsConstructor
public class BlogUserMapper {

    private final ModelMapper modelMapper;

    public BlogUserDto toDto(BlogUser user) {
        return modelMapper.map(user, BlogUserDto.class);
    }

    public BlogUser toEntity(BlogUserDto dto) {
        return modelMapper.map(dto, BlogUser.class);
    }
}