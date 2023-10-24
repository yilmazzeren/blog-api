package com.example.blog.service.handler;

import com.example.blog.model.Category;
import com.example.blog.model.request.AddCategoryRequest;
import com.example.blog.model.request.UpdateCategoryRequest;
import com.example.blog.utilities.result.SuccessDataResult;

import java.util.List;

public interface CategoryHandler {
    SuccessDataResult<List<Category>> getAllCategories();

    SuccessDataResult<Category> getCategoryById(String id);

    SuccessDataResult<Category> addCategory(AddCategoryRequest addCategoryRequest);

    SuccessDataResult<Boolean> deleteCategory(String id);

    SuccessDataResult<Category> updateCategory(UpdateCategoryRequest updateCategoryRequest);
}
