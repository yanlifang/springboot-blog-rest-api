package com.springboot.blog.payload;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Set;

@Data

public class PostDto2 {
    private long id;

    @NotEmpty
    @Size(min = 2, message = "Post title should have at least 2 characters")
    private String title;

    @NotEmpty
    @Size(min = 10, message = "Post title should have at least 10 characters")
    private String description;
    @NotEmpty
    @Size(message = "content cannot be empty")
    private String content;
    private Set<CommentDto> comments;
    private List<String> tags;

}