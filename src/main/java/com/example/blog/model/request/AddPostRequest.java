package com.example.blog.model.request;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class AddPostRequest {
    private String title;

    private String description;

    @Id
    private String categoryId;

}
