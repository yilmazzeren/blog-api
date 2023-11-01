package com.example.blog.model.request;

import com.example.blog.model.User;
import lombok.Data;

@Data
public class AddCategoryRequest {
    private String title;
    private User user;
}
