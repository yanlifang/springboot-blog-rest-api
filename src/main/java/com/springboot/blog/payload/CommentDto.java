package com.springboot.blog.payload;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Api(value = "Comment model information")
@Data
public class CommentDto {

    @ApiModelProperty(value = "Comment id")
    private long id;

    @ApiModelProperty(value = "Comment name")
    @NotEmpty(message = "Name can not be null or empty")
    private String name;

    @ApiModelProperty(value = "Comment email")
    @NotEmpty
    @Email(message = "Email should not be null or empty")
    private String email;
    @ApiModelProperty(value = "Comment boy")
    @NotEmpty
    @Size(min = 10, message = "Body must be minium 10 characters")
    private String body;

}
