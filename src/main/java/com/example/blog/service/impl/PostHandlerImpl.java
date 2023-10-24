package com.example.blog.service.impl;

import com.example.blog.exceptions.DocumentNotFoundException;
import com.example.blog.exceptions.DuplicateDocumentException;
import com.example.blog.model.Category;
import com.example.blog.model.Post;
import com.example.blog.model.enums.PostSortMethod;
import com.example.blog.model.request.AddPostRequest;
import com.example.blog.model.request.UpdatePostRequest;
import com.example.blog.repository.CategoryRepository;
import com.example.blog.repository.PostRepository;
import com.example.blog.service.handler.PostHandler;
import com.example.blog.utilities.result.SuccessDataResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostHandlerImpl implements PostHandler {
    private final PostRepository postRepository;
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    @Override
    public SuccessDataResult<List<Post>> getPosts() {
        /*postRepository.getAllByCategoryIds("1", "2");*/
        return new SuccessDataResult<>(postRepository.findAll());
    }

    @Override
    public SuccessDataResult<Post> getPostById(String id) {
        return new SuccessDataResult<>(postRepository.findById(id).orElseThrow(() -> new DocumentNotFoundException("Post with id " + id + " was not found")));
    }

    @Override
    public SuccessDataResult<Post> addPost(AddPostRequest addPostRequest) {
        Category category = categoryRepository.findById(addPostRequest.getCategoryId()).orElseThrow(() -> new DocumentNotFoundException("Category with ID " + addPostRequest.getCategoryId() + " not found"));

        Post post = modelMapper.map(addPostRequest, Post.class);
        post.setCategory(category);

        Post addedPost;
        try {
            addedPost = postRepository.save(post);
        } catch (DuplicateKeyException exception) {
            throw new DuplicateDocumentException("Post");
        }
        return new SuccessDataResult<>(addedPost);
    }

    @Override
    public SuccessDataResult<Boolean> deletePost(String id) {
        if (!postRepository.existsById(id))
            throw new DocumentNotFoundException("Post with id " + id + " was not found");
        postRepository.deleteById(id);
        return new SuccessDataResult<>(true);
    }

    @Override
    public SuccessDataResult<Post> updatePost(UpdatePostRequest updatePostRequest) {
        Post post = postRepository.findById(updatePostRequest.getId()).orElseThrow(() -> new DocumentNotFoundException("Post with id " + updatePostRequest.getId() + " was not found"));
        modelMapper.map(updatePostRequest, post);
        return new SuccessDataResult<>(postRepository.save(post));
    }

    @Override
    public SuccessDataResult<List<Post>> lastPosts(PostSortMethod postSortMethod) {
        Sort sortBy = Sort.by(PostSortMethod.DATE_ASC.getValue()).ascending();
        if (postSortMethod != null) {
            switch (postSortMethod) {
                case DATE_ASC -> sortBy = Sort.by(postSortMethod.getValue()).ascending();
                case DATE_DESC -> sortBy = Sort.by(postSortMethod.getValue()).descending();
            }
        }
        Pageable pageable = PageRequest.of(0, 5, sortBy);
        Page<Post> first5PostsPage = postRepository.findAll(pageable);
        return new SuccessDataResult<>(first5PostsPage.stream().map(post -> modelMapper.map(post, Post.class)).toList());
    }

    @Override
    public SuccessDataResult<Post> changeCategory(String postId, String categoryId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new DocumentNotFoundException("Post with id " + postId + " was not found"));
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new DocumentNotFoundException("Category with id " + categoryId + " was not found"));

        post.setCategory(category);
        return new SuccessDataResult<>(postRepository.save(post));

    }
}
