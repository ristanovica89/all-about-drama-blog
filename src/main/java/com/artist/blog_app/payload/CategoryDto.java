package com.artist.blog_app.payload;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {

    private Integer categoryId;

    @NotBlank
    private String title;

    @NotBlank
    @Size(max = 200, message = "The description must not be more than 200 characters long.")
    private String description;
}
