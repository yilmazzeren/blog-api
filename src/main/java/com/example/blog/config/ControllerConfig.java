package com.example.blog.config;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@RestController
@Validated
// @CrossOrigin(originPatterns = {"https://*.com", "http://*.com"}, maxAge = 3600)
@CrossOrigin // TODO - switch with url when domain is enabled
public @interface ControllerConfig {
}
