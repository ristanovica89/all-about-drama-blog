package com.artist.blog_app.service;

import com.artist.blog_app.entities.BlogUser;
import com.artist.blog_app.entities.Category;
import com.artist.blog_app.entities.Post;
import com.artist.blog_app.exceptions.ResourceNotFoundException;
import com.artist.blog_app.mapper.PostMapper;
import com.artist.blog_app.payload.PostDto;
import com.artist.blog_app.payload.PostResponse;
import com.artist.blog_app.repository.BlogUserRepository;
import com.artist.blog_app.repository.CategoryRepository;
import com.artist.blog_app.repository.PostRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final BlogUserRepository blogUserRepository;
    private final CategoryRepository categoryRepository;
    private final PostMapper mapper;

    private static final List<String> ALLOWED_SORT_FIELDS =
            List.of("addedDate", "title", "id");

    public PostResponse getAllPosts(Integer pageNumber, Integer pageSize, String sortBy, String sortDir){

        sortBy = validateSortBy(sortBy);

        Sort sort = sortDir.equalsIgnoreCase("asc")
                ? Sort.by(Sort.Order.asc(sortBy), Sort.Order.asc("id"))
                : Sort.by(Sort.Order.desc(sortBy), Sort.Order.desc("id"));

        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        Page<Post> pagePost = postRepository.findAll(pageable);

        List<PostDto> postsDto =  pagePost.getContent()
                .stream()
                .map(mapper::toDto)
                .toList();

        return buildPostResponse(postsDto, pagePost);
    }

//    public PostDto getPostById(Integer id){
//        var post = postRepository.findById(id)
//                .orElseThrow(() -> new ResourceNotFoundException("Post ","Post Id",id));
//
//        return mapper.toDto(post);
//    }
//
//    public PostDto getPostByTitle(String title){
//        var post = postRepository.findPostByTitle(title)
//                .orElseThrow(() -> new ResourceNotFoundException("Post ","Post Id",title));
//
//        return mapper.toDto(post);
//    }

    public PostResponse getAllPostsByUserId(Integer userId, Integer pageNumber, Integer pageSize, String sortBy, String sortDir){

        BlogUser user = blogUserRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User ","User Id",userId));

        sortBy = validateSortBy(sortBy);

        Sort sort = sortDir.equalsIgnoreCase("asc")
                ? Sort.by(Sort.Order.asc(sortBy), Sort.Order.asc("id"))
                : Sort.by(Sort.Order.desc(sortBy), Sort.Order.desc("id"));

        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        Page<Post> pagePost = postRepository.findByBlogUser(user, pageable);

        List<PostDto> postsDto = pagePost.getContent()
                .stream()
                .map(mapper::toDto)
                .toList();

        return buildPostResponse(postsDto, pagePost);

    }

    public PostResponse getAllPostsByCategoryId(Integer categoryId, Integer pageNumber, Integer pageSize, String sortBy, String sortDir){

        Category category = categoryRepository.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category ","Category Id",categoryId));

        sortBy = validateSortBy(sortBy);

        Sort sort = sortDir.equalsIgnoreCase("asc")
                ? Sort.by(Sort.Order.asc(sortBy), Sort.Order.asc("id"))
                : Sort.by(Sort.Order.desc(sortBy), Sort.Order.desc("id"));

        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        Page<Post> pagePost = postRepository.findByCategory(category,pageable);

       List<PostDto> postsDto = pagePost.getContent()
                .stream()
                .map(mapper::toDto)
                .toList();

        return buildPostResponse(postsDto, pagePost);
    }


    public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId){
        BlogUser blogUser = blogUserRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User ","User Id",userId));
        Category category = categoryRepository.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category ","Category Id",categoryId));

        Post post = mapper.toEntity(postDto, blogUser, category);
        post.setImgPath("/default.png");
        postRepository.save(post);

        return mapper.toDto(post);
    }

    public PostDto updatePost(PostDto postDto, Integer id){
        var existingPost = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post ","Post Id",id));

        mapper.updateEntity(postDto,existingPost);
        postRepository.save(existingPost);

        return mapper.toDto(existingPost);
    }



    public void deletePost(Integer id){
        var post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post ","Post Id",id));

        postRepository.delete(post);
    }


    // Building PostResponse helper method

    private PostResponse buildPostResponse(List<PostDto> postsDto, Page<Post> pagePost){
        PostResponse postResponse = new PostResponse();
        postResponse.setContent(postsDto);
        postResponse.setPageNumber(pagePost.getNumber());
        postResponse.setPageSize(pagePost.getSize());
        postResponse.setTotalElements(pagePost.getTotalElements());
        postResponse.setTotalPages(pagePost.getTotalPages());
        postResponse.setLastPage(pagePost.isLast());

        return postResponse;
    }

    // Controlling method

    private String validateSortBy(String sortBy) {
        if (sortBy == null || !ALLOWED_SORT_FIELDS.contains(sortBy)) {
            return "addedDate";
        }
        return sortBy;
    }
}
