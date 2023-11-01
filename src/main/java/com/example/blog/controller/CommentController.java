package com.example.blog.controller;

import com.example.blog.config.ControllerConfig;
import com.example.blog.model.Post;
import com.example.blog.model.request.AddCommentRequest;
import com.example.blog.service.handler.CommentHandler;
import com.example.blog.utilities.result.SuccessDataResult;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@ControllerConfig
@Slf4j
@RequestMapping(value = "/api/comment")
@RequiredArgsConstructor
public class CommentController {
    private final CommentHandler commentHandler;

    @Operation(summary = "Add comment", description = "asd")
    @RequestMapping(method = RequestMethod.POST)
    public SuccessDataResult<Post> addPost(@RequestBody @Valid() AddCommentRequest addCommentRequest) {
        log.info("Add comment request => {}", addCommentRequest);
        return commentHandler.addComment(addCommentRequest);
    }
}
