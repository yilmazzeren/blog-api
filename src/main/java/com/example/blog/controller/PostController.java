package com.example.blog.controller;

import com.example.blog.model.Post;
import com.example.blog.model.enums.PostSortMethod;
import com.example.blog.model.request.AddPostRequest;
import com.example.blog.model.request.UpdatePostRequest;
import com.example.blog.service.handler.PostHandler;
import com.example.blog.utilities.result.SuccessDataResult;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping(value = "/api/post")
@RequiredArgsConstructor
public class PostController {
    private final PostHandler postHandler;

    @Operation(summary = "Get all posts", description = "asd")
    @GetMapping
    public SuccessDataResult<List<Post>> getPosts() {
        return postHandler.getPosts();
    }

    @Operation(summary = "Get post by id", description = "asd")
    @RequestMapping(method = RequestMethod.GET, value = "{id}")
    public SuccessDataResult<Post> getPostById(@PathVariable("id") String id) {
        return postHandler.getPostById(id);
    }

    @Operation(summary = "Add post", description = "asd")
    @RequestMapping(method = RequestMethod.POST)
    public SuccessDataResult<Post> addPost(@RequestBody @Valid() AddPostRequest addPostRequest) {
        log.info("Add post request => {}", addPostRequest);
        return postHandler.addPost(addPostRequest);
    }

    @Operation(summary = "Delete post", description = "asd")
    @RequestMapping(method = RequestMethod.DELETE, value = {"{id}"})
    public SuccessDataResult<Boolean> deletePost(@PathVariable("id") String id) {
        return postHandler.deletePost(id);
    }

    @Operation(summary = "Update post", description = "asd")
    @PatchMapping
    public SuccessDataResult<Post> updatePost(@Valid() @RequestBody UpdatePostRequest updatePostRequest) {
        return postHandler.updatePost(updatePostRequest);
    }

    @Operation(summary = "Get last 5 posts")
    @GetMapping("/lastPosts")
    public SuccessDataResult<List<Post>> lastPosts(PostSortMethod sortBy) {
        return postHandler.lastPosts(sortBy);
    }

    @Operation(summary = "Change post category", description = "asd")
    @PatchMapping("/changeCategory")
    public SuccessDataResult<Post> changeCategory(@RequestParam String postId, @RequestParam String categoryId) {
        return postHandler.changeCategory(postId, categoryId);
    }

    @Operation(summary = "Get posts by category id", description = "asd")
    @GetMapping("/byCategoryId")
    public SuccessDataResult<List<Post>> getPostsByCategoryId(@RequestParam String categoryId) {
        return postHandler.getPostsByCategoryId(categoryId);
    }
}
