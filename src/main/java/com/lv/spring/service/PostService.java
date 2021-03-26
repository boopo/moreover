package com.lv.spring.service;

import com.lv.spring.entity.Post;
import org.springframework.data.domain.Page;

import javax.net.ssl.SSLContext;
import java.util.List;

public interface PostService {
    void save(Post post);
    void update(String id, Post post);
    void deleted(String id);
    List<Post> findAll();
    Page<Post> findByPage(Integer page, Integer limit);
    Post getOnePost(String id);

}
