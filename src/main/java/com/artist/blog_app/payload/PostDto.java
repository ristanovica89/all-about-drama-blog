package com.artist.blog_app.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {

    private Integer id;

    private String title;

    private String content;

    private String imgPath;

    private LocalDateTime addedDate;

    private CategoryDto category;

    private BlogUserDto blogUser;
}
