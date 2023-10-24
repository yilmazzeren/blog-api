package com.example.blog.controller;

import com.example.blog.model.Category;
import com.example.blog.model.request.AddCategoryRequest;
import com.example.blog.model.request.UpdateCategoryRequest;
import com.example.blog.service.handler.CategoryHandler;
import com.example.blog.utilities.result.SuccessDataResult;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping(value = "/api/category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryHandler categoryHandler;

    @Operation(summary = "Get all categories", description = "asd")
    @GetMapping
    public SuccessDataResult<List<Category>> getCategories() {
        return categoryHandler.getAllCategories();
    }

    @Operation(summary = "Get category by id", description = "asd")
    @RequestMapping(method = RequestMethod.GET, value = "{id}")
    public SuccessDataResult<Category> getCategoryById(@PathVariable("id") String id) {
        return categoryHandler.getCategoryById(id);
    }

    @Operation(summary = "Add category", description = "asd")
    @RequestMapping(method = RequestMethod.POST)
    public SuccessDataResult<Category> addCategory(@RequestBody @Valid() AddCategoryRequest addCategoryRequest) {
        log.info("Add post request => {}", addCategoryRequest);
        return categoryHandler.addCategory(addCategoryRequest);
    }

    @Operation(summary = "Delete category", description = "asd")
    @RequestMapping(method = RequestMethod.DELETE, value = "{id}")
    public SuccessDataResult<Boolean> deleteCategory(@PathVariable("id") String id) {
        log.info("Delete post request => {}", id);
        return categoryHandler.deleteCategory(id);
    }

    @Operation(summary = "Update category", description = "asd")
    @PatchMapping
    public SuccessDataResult<Category> updatePost(@Valid() @RequestBody UpdateCategoryRequest updateCategoryRequest) {
        return categoryHandler.updateCategory(updateCategoryRequest);
    }
}
