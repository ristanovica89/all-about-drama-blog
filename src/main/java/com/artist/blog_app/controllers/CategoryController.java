package com.artist.blog_app.controllers;

import com.artist.blog_app.payload.ApiResponse;
import com.artist.blog_app.payload.CategoryDto;
import com.artist.blog_app.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping()
    public ResponseEntity<List<CategoryDto>> getAllCategories(){
        var categories = categoryService.getAllCategories();

        return ResponseEntity.ok(categories);
    }

    @GetMapping("{id}")
    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable Integer id){
        var category = categoryService.getCategoryById(id);

        return ResponseEntity.ok(category);
    }

    @GetMapping("/title/{title}")
    public ResponseEntity<CategoryDto> getCategoryByTitle(@PathVariable String title){
        var category = categoryService.getCategoryByTitle(title);

        return ResponseEntity.ok(category);
    }

    @PostMapping()
    public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto){
        var newCategory = categoryService.createCategory(categoryDto);

        return ResponseEntity
                .created(URI.create("/categories/" + newCategory.getCategoryId()))
                .body(newCategory);
    }

    @PutMapping("{id}")
    public ResponseEntity<CategoryDto> updateCategoryById(@PathVariable Integer id,
                                                          @RequestBody CategoryDto categoryDto){
        var updatedCategory = categoryService.updateCategory(categoryDto, id);

        return ResponseEntity.ok(updatedCategory);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ApiResponse> deleteCategoryById(@PathVariable Integer id){
        categoryService.deleteCategory(id);

        return ResponseEntity.ok(new ApiResponse("Successfully deleted",true));
    }


}
