package com.artist.blog_app.payload;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class JwtRequest {

    @NotBlank
    private String username;

    @NotBlank
    private String password;

}
