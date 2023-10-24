package com.example.blog.exceptions;


import lombok.Getter;

@Getter
public class DuplicateDocumentException extends RuntimeException {
    private final String field;

    public DuplicateDocumentException(String field) {
        super();
        this.field = field;
    }
}
