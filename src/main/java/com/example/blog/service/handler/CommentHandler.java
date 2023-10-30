package com.example.blog.service.handler;

import com.example.blog.model.Post;
import com.example.blog.model.request.AddCommentRequest;
import com.example.blog.utilities.result.SuccessDataResult;

public interface CommentHandler {
    SuccessDataResult<Post> addComment(AddCommentRequest addCommentRequest);
}
