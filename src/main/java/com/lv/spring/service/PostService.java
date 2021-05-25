package com.lv.spring.service;

import com.lv.spring.entity.Post;
import org.springframework.data.domain.Page;

import javax.net.ssl.SSLContext;
import java.text.ParseException;
import java.util.List;

public interface PostService {
    void save(Post post) throws ParseException;
    void update(String id, Post post);
    void deleted(String id);
    List<Post> findAll();
    Page<Post> findByPage(Integer page, Integer limit);
    Post getOnePost(String id);
    void starPost(String id);
    void collectPost(String id);
}
