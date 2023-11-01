package com.example.blog.model.request;

import com.example.blog.model.User;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class UpdatePostRequest {
    @Id
    private String id;

    private String title;

    private String description;

    private User user;

}
