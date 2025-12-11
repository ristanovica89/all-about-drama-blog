package com.artist.blog_app.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BlogUserDto {

    private Integer id;

    @NotEmpty
    @Size(min = 4, message = "Name has to be minimum 4 characters long!")
    private String name;

    @NotEmpty
    @Email(message = "Email address is not valid!")
    private String email;

    @NotEmpty
    @Size(min = 4, max = 15, message = "Password has to be minimum 4 and maximum 15 characters long!")
    private String password;

    @NotNull
    private Integer age;

    @NotEmpty
    private String gender;
}
