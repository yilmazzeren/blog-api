package com.example.blog.service.impl;

import com.example.blog.exceptions.DocumentNotFoundException;
import com.example.blog.exceptions.DuplicateDocumentException;
import com.example.blog.model.Comment;
import com.example.blog.model.Post;
import com.example.blog.model.request.AddCommentRequest;
import com.example.blog.repository.CommentRepository;
import com.example.blog.repository.PostRepository;
import com.example.blog.service.handler.CommentHandler;
import com.example.blog.utilities.result.SuccessDataResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CommentHandlerImpl implements CommentHandler {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final ModelMapper modelMapper;

    @Override
    public SuccessDataResult<Post> addComment(AddCommentRequest addCommentRequest) {
        Post post = postRepository.findById(addCommentRequest.getPostId()).orElseThrow(() -> new DocumentNotFoundException("Post with ID " + addCommentRequest.getPostId() + " not found"));
        Comment comment = modelMapper.map(addCommentRequest, Comment.class);

        post.getComments().add(comment);

        Post addedComment;
        try {
            commentRepository.save(comment);
            addedComment = postRepository.save(post);
        } catch (DuplicateKeyException exception) {
            throw new DuplicateDocumentException("Comment");
        }
        return new SuccessDataResult<>(addedComment);
    }
}
