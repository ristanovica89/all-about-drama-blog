package com.artist.blog_app.service;

import com.artist.blog_app.repository.PostRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PostService {

    private PostRepository postRepository;


}
