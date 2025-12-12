package com.artist.blog_app.payload;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {

    private Integer categoryId;

    private String title;

    private String description;
}
