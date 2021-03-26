package com.lv.spring.repository;

import com.lv.spring.entity.Post;
import com.lv.spring.entity.UserInfo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {

}
