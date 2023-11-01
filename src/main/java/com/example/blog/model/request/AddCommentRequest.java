package com.example.blog.model.request;

import com.example.blog.model.User;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class AddCommentRequest {

    private String description;

    @Id
    private String postId;
    private User user;
}
