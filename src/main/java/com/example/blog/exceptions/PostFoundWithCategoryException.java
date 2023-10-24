package com.example.blog.exceptions;

import lombok.Getter;

@Getter
public class PostFoundWithCategoryException extends RuntimeException {
    public PostFoundWithCategoryException(String message) {
        super(message);
    }
}
