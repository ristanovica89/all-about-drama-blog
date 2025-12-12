package com.artist.blog_app.mapper;

import com.artist.blog_app.entities.Category;
import com.artist.blog_app.payload.CategoryDto;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CategoryMapper {

    private final ModelMapper modelMapper;

    public CategoryDto toDto(Category category){
        return modelMapper.map(category, CategoryDto.class);
    }

    public Category toEntity(CategoryDto categoryDto){
        return  modelMapper.map(categoryDto, Category.class);
    }

    public void updateEntity(CategoryDto dto, Category category) {
        modelMapper.map(dto, category);
    }
}
