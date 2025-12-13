package com.artist.blog_app.payload;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {

    private Integer categoryId;

    @NotEmpty
    private String title;

    @NotEmpty
    @Size(max = 200, message = "The description must not be more than 200 characters long.")
    private String description;
}
