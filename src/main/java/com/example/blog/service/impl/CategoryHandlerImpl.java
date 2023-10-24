package com.example.blog.service.impl;

import com.example.blog.model.Category;
import com.example.blog.model.request.AddCategoryRequest;
import com.example.blog.repository.CategoryRepository;
import com.example.blog.service.handler.CategoryHandler;
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
    private final ModelMapper modelMapper;

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category addCategory(AddCategoryRequest addCategoryRequest) {
        Category category = modelMapper.map(addCategoryRequest, Category.class);
        return categoryRepository.save(category);
    }
}
