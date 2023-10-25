package com.example.blog.repository;

import com.example.blog.model.Post;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PostRepository extends MongoRepository<Post, String> {
    Boolean existsPostByCategory_Id(String categoryId);

    List<Post> getPostByCategory_Id(String categoryId);

   /* // categorisi a veya b olanları bul
    @Query("{$or: [{category: ?0}, {category: ?1}]}")
    List<Post> getAllByCategoryIds(String id1, String id2);*/
}
