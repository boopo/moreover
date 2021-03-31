package com.lv.spring.service.impl;

import com.lv.spring.context.UserContext;
import com.lv.spring.entity.Post;
import com.lv.spring.enums.ResultVOEnum;
import com.lv.spring.exceptioin.ApiException;
import com.lv.spring.repository.PostRepository;
import com.lv.spring.service.PostService;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    PostRepository postRepository;

    @Override
    public void save(Post post) {
        post.setStar(0);
        post.setCreateTime(new Date());
        post.setUpdateTime(new Date());
        post.setIsDeleted(0);
        post.setPublisher(UserContext.getCurrentUserName());
        Post p1 = postRepository.save(post);
    }

    @Override
    public void update(String id, Post post) {
        Post oldPost = postRepository.findById(id).get();
        if (oldPost.getIsDeleted().equals(1)) throw new ApiException(ResultVOEnum.NOT_FOUND);
        oldPost.setTitle(post.getTitle());
        oldPost.setStartTime(post.getStartTime());
        oldPost.setCutoffTime(post.getCutoffTime());
        oldPost.setLocation(post.getLocation());
        oldPost.setOutline(post.getOutline());
        oldPost.setDetail(post.getDetail());
        oldPost.setOriginalImage(post.getOriginalImage());
        oldPost.setThumbnailImage(post.getThumbnailImage());

        oldPost.setUpdateTime(new Date());

        postRepository.save(oldPost);
    }

    @Override
    public void deleted(String id) {
        Post post = postRepository.findById(id).get();
        post.setIsDeleted(1);
        postRepository.save(post);
    }

    @Override
    public List<Post> findAll() {
        List<Post> list;
        list = postRepository.findAll();
        return list;
    }

    @Override
    public Page<Post> findByPage(Integer page, Integer limit) {
        Sort sort = Sort.by(Sort.Direction.DESC, "updateTime");
        Pageable pageable = PageRequest.of(page - 1 , limit, sort);
        Post post = new Post();
        post.setIsDeleted(0);

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
                .withIgnoreCase(true);

        Example <Post> example = Example.of(post ,matcher);
        Page<Post> posts = postRepository.findAll(example,pageable);
        return posts;
    }
    @Override
    public Post getOnePost(String id){
        return postRepository.findById(id).get();
    }
}
