package com.springboot.blog.controller;

import com.springboot.blog.payload.PostDto;
import com.springboot.blog.payload.PostResponse;
import com.springboot.blog.service.PostService;
import com.springboot.blog.utils.AppConstants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(value = "CRUD REST API for post resources")
@RestController
@RequestMapping()
public class PostController {

    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }



    @ApiOperation(value = "Create post rest api")
    //create blog post
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/api/v1/posts")
    public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto) {
        return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Get all post rest api")
    @GetMapping(value = "/api/v1/posts")
    //@GetMapping(value = "/api/v1/posts", headers = "X-API-VERSION=1")
    //@GetMapping(value = "/api/v1/posts", params = "version=1")
    //@GetMapping(value = "/api/v1/posts", produces = "application/vnd.javaguides.v1+json")
    public PostResponse getAllPosts(
            @RequestParam(value="pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value="pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value="sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
    ) {
        return postService.getAllPosts(pageNo, pageSize, sortBy, sortDir);
    }

    @ApiOperation(value = "Get post rest api by Id")

    @GetMapping(value = "/api/v1/posts/{id}")
    //@GetMapping(value = "/api/v1/posts/{id}", params = "version=2")
    public ResponseEntity<PostDto> getPostByIdV1(@PathVariable(name="id") long id) {
        return ResponseEntity.ok(postService.getPostById(id));
    }

    /*
    @GetMapping("/api/v1/posts/{id}")
    public ResponseEntity<PostDto2> getPostByIdV2(@PathVariable(name="id") long id) {
        PostDto postDto = postService.getPostById(id);
        PostDto2 postDto2 = new PostDto2();
        postDto2.setId(postDto.getId());
        postDto2.setTitle(postDto.getTitle());
        postDto2.setDescription(postDto.getDescription());
        postDto2.setContent(postDto.getContent());

        List<String> tags = new ArrayList<>();
        tags.add("Java");
        tags.add("Spring Boot");
        tags.add("AWS");
        postDto2.setTags(tags);
        return ResponseEntity.ok(postDto2);
    }*/

    @ApiOperation(value = "Update post rest api")
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/api/v1/posts/{id}")
    public ResponseEntity<PostDto> updatePost(@Valid @RequestBody PostDto postDto, @PathVariable(name="id") long id) {

        PostDto postResponse = postService.updatePost(postDto, id);

        return new ResponseEntity<>(postResponse, HttpStatus.OK);
    }

    @ApiOperation(value = "Delete post rest api")
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/api/v1/posts/{id}")
    //delete post rest api
    public ResponseEntity<String> deletePost(@PathVariable(name="id") long id){
        postService.deletePostById(id);
        return new ResponseEntity<>("Post entitty deleted successfully.", HttpStatus.OK);
    }



}
