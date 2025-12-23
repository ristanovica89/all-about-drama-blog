package com.artist.blog_app.service;

import com.artist.blog_app.entities.Category;
import com.artist.blog_app.exceptions.ResourceNotFoundException;
import com.artist.blog_app.mapper.CategoryMapper;
import com.artist.blog_app.payload.CategoryDto;
import com.artist.blog_app.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper mapper;


    public List<CategoryDto> getAllCategories(){
        return categoryRepository.findAll()
                .stream()
                .map(mapper::toDto)
                .toList();
    }

    public CategoryDto getCategoryById(Integer id){
        var category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category ","Category Id",id));

        return mapper.toDto(category);
    }

    public CategoryDto getCategoryByTitle(String title){
        var category = categoryRepository.findCategoryByTitle(title)
                .orElseThrow(() -> new ResourceNotFoundException("Category ","Title",title));

        return mapper.toDto(category);
    }

    public CategoryDto createCategory(CategoryDto categoryDto){
        Category category = mapper.toEntity(categoryDto);
        categoryRepository.save(category);

        return mapper.toDto(category);
    }

    public CategoryDto updateCategory(CategoryDto categoryDto, Integer id){
        var existingCategory = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category ","Category Id",id));

        mapper.updateEntity(categoryDto,existingCategory);
        categoryRepository.save(existingCategory);

        return mapper.toDto(existingCategory);
    }



    public void deleteCategory(Integer id){

        var category = categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category ","Category Id",id));
        categoryRepository.delete(category);
    }

}
