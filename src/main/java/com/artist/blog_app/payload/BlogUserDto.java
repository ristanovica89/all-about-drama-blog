package com.artist.blog_app.payload;

import lombok.*;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BlogUserDto {

    private Integer id;

    private String name;

    private String email;

    private String password;

    private Integer age;

    private String gender;
}
