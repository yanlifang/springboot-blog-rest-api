package com.springboot.blog.payload;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class CommentDto {

    private long id;

    @NotEmpty(message = "Name can not be null or empty")
    private String name;

    @NotEmpty
    @Email(message = "Email should not be null or empty")
    private String email;
    @NotEmpty
    @Size(min = 10, message = "Body must be minium 10 characters")
    private String body;

}
