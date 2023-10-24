package com.example.blog.utilities.result;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Result { // super type
    private final boolean success;
    private String message;

    public Result(boolean success) {
        this.success = success;
    }

    public Result(boolean success, String message) {
        this(success);
        this.message = message;
    }
}
