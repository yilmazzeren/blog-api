package com.example.blog.service.impl;

import com.example.blog.exceptions.DocumentNotFoundException;
import com.example.blog.exceptions.PostFoundWithCategoryException;
import com.example.blog.model.Category;
import com.example.blog.model.request.AddCategoryRequest;
import com.example.blog.model.request.UpdateCategoryRequest;
import com.example.blog.repository.CategoryRepository;
import com.example.blog.repository.PostRepository;
import com.example.blog.service.handler.CategoryHandler;
import com.example.blog.utilities.result.SuccessDataResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryHandlerImpl implements CategoryHandler {
    private final CategoryRepository categoryRepository;
    private final PostRepository postRepository;
    private final ModelMapper modelMapper;

    @Override
    public SuccessDataResult<List<Category>> getAllCategories() {
        return new SuccessDataResult<>(categoryRepository.findAll());
    }

    @Override
    public SuccessDataResult<Category> getCategoryById(String id) {
        return new SuccessDataResult<>(categoryRepository.findById(id).orElseThrow(() -> new DocumentNotFoundException("Category with id " + id + " was not found")));
    }

    @Override
    public SuccessDataResult<Category> addCategory(AddCategoryRequest addCategoryRequest) {
        Category category = modelMapper.map(addCategoryRequest, Category.class);
        return new SuccessDataResult<>(categoryRepository.save(category));
    }

    @Override
    public SuccessDataResult<Boolean> deleteCategory(String id) {
        if (postRepository.existsPostByCategory_Id(id))
            throw new PostFoundWithCategoryException(String.format("You cannot delete category with id %s. Cause: category is used for at least one post.", id));
        categoryRepository.findById(id).orElseThrow(() -> new DocumentNotFoundException("Category with id " + id + " was not found"));
        categoryRepository.deleteById(id);
        return new SuccessDataResult<>(true);
    }

    @Override
    public SuccessDataResult<Category> updateCategory(UpdateCategoryRequest updateCategoryRequest) {
        Category category = categoryRepository.findById(updateCategoryRequest.getId()).orElseThrow(() -> new DocumentNotFoundException("Category with id " + updateCategoryRequest.getId() + " was not found"));
        modelMapper.map(updateCategoryRequest, category);
        return new SuccessDataResult<>(categoryRepository.save(category));
    }
}
