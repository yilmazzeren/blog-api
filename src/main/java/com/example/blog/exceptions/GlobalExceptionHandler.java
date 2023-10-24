package com.example.blog.exceptions;

import com.example.blog.utilities.result.ErrorResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(DocumentNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResult> handleDocumentNotFoundException(DocumentNotFoundException exception) {
        String error = String.format("%s", exception.getField());
        return ResponseEntity.badRequest().body(new ErrorResult(error));
    }
}
