package com.example.blog.service.handler;

import com.example.blog.model.Post;
import com.example.blog.model.enums.PostSortMethod;
import com.example.blog.model.request.AddPostRequest;
import com.example.blog.model.request.UpdatePostRequest;
import com.example.blog.utilities.result.SuccessDataResult;

import java.util.List;

public interface PostHandler {
    SuccessDataResult<List<Post>> getPosts();

    SuccessDataResult<Post> getPostById(String id);

    SuccessDataResult<Post> addPost(AddPostRequest addPostRequest);

    SuccessDataResult<Boolean> deletePost(String id);

    SuccessDataResult<Post> updatePost(UpdatePostRequest updatePostRequest);

    SuccessDataResult<List<Post>> lastPosts(PostSortMethod postSortMethod);

    SuccessDataResult<Post> changeCategory(String postId, String categoryId);

    SuccessDataResult<List<Post>> getPostsByCategoryId(String categoryId);

}
