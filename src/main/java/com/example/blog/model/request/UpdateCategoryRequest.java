package com.example.blog.model.request;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class UpdateCategoryRequest {
    @Id
    private String id;

    private String title;
}
