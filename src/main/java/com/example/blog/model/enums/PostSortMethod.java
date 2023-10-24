package com.example.blog.model.enums;

import lombok.Getter;

@Getter
public enum PostSortMethod {
    DATE_ASC("createdAt"), DATE_DESC("createdAt");

    private final String value;

    PostSortMethod(String value) {
        this.value = value;
    }
}
