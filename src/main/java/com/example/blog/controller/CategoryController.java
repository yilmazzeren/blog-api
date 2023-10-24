package com.example.blog.controller;

import com.example.blog.model.Category;
import com.example.blog.model.request.AddCategoryRequest;
import com.example.blog.service.handler.CategoryHandler;
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
    public List<Category> getCategories() {
        return categoryHandler.getAllCategories();
    }

    @Operation(summary = "Add category", description = "asd")
    @RequestMapping(method = RequestMethod.POST)
    public Category addCategory(@RequestBody @Valid() AddCategoryRequest addCategoryRequest) {
        log.info("Add post request => {}", addCategoryRequest);
        return categoryHandler.addCategory(addCategoryRequest);
    }
}
