package com.example.blog.service.handler;

import com.example.blog.model.Category;
import com.example.blog.model.request.AddCategoryRequest;

import java.util.List;

public interface CategoryHandler {
    List<Category> getAllCategories();

    Category addCategory(AddCategoryRequest addCategoryRequest);
}
