package com.artist.blog_app.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommentResponse {

    private List<CommentDto> content;

    private Integer pageNumber;

    private Integer pageSize;

    private Long totalElements;

    private Integer totalPages;

    private Boolean lastPage;
}