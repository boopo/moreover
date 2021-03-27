package com.lv.spring.repository;

import com.lv.spring.entity.CommentPost;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface  CommentRepository extends MongoRepository<CommentPost,String> {
}
