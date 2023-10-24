package com.example.blog.exceptions;


import lombok.Getter;

@Getter
public class DocumentNotFoundException extends RuntimeException {
    private final String field;

    public DocumentNotFoundException(String field) {
        super();
        this.field = field;
    }
}
